package com.truckload.core.services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.truckload.core.abstracts.exceptions.BussinesException;
import com.truckload.core.abstracts.exceptions.CsvProcessorException;
import com.truckload.core.abstracts.exceptions.FileUtilException;
import com.truckload.core.abstracts.impl.CsvProcessorImpl;
import com.truckload.core.abstracts.interfaces.IECsv;
import com.truckload.core.abstracts.interfaces.IECsv.GetFile;
import com.truckload.core.abstracts.interfaces.IECsv.ReadCsv;
import com.truckload.core.abstracts.interfaces.IECsv.SaveCsv;
import com.truckload.core.abstracts.interfaces.IECsv.SendMail;
import com.truckload.core.entity.Trucks;
import com.truckload.core.entity.TrucksCsv;
import com.truckload.core.models.Truck;
import com.truckload.core.repository.TruckRepository;
import com.truckload.core.services.LoadService;
import com.truckload.core.utils.FileUtils;

@Service
public class LoadServiceImpl implements LoadService {

	@Autowired
	TruckRepository repo;
	
	 @Autowired
	 private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	//this the main process and it use the strategy pattern 
	@Override
	public String loadCSV(Trucks t) throws BussinesException {
		TrucksCsv csv = new TrucksCsv(t);
		try {
			//the implementation the interface is an void class because it needs an initialization
			IECsv<TrucksCsv> cvsProcessor = new CsvProcessorImpl<>(csv);

			cvsProcessor.convertToFile((GetFile<TrucksCsv>) (TrucksCsv x) -> {
				try {
					x.setPath(FileUtils.convertStringtoFile(x.getT().getFile(), x.getT().getFilename()));
				} catch (FileUtilException e) {
					throw new CsvProcessorException(e.getMessage(), e);
				}
			}).csvReader((ReadCsv<TrucksCsv>) (TrucksCsv x) -> {
				CSVReader csvReader=null;
				Reader reader =null;
				try{
					reader = Files.newBufferedReader(Paths.get(x.getPath()));
					if(x.getT().isTitles()) {
						csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
					}else {
						csvReader = new CSVReader(reader);
					}
					x.setCsvContents(csvReader.readAll());
				} catch (FileNotFoundException e) {
					throw new CsvProcessorException("Fallo al iniciar csvReader csv", e);
				} catch (IOException e) {
					throw new CsvProcessorException("Fallo al ejecutar reader.readAll() csv", e);
				}finally {
					try {
						reader.close();
						csvReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}).persistCsv((SaveCsv<TrucksCsv>) (TrucksCsv x) -> 
					x.getCsvContents().forEach((String[] y) -> {
						boolean fail =false;
						String row = y[0];
						String[] vec = row.split(";");
					if((vec[1] == null) || (vec[0] == "")) {
						x.getErrors().put(vec[0], "the idtruck can not be null or black space");
						x.setError(x.getError()+1);
						fail = true;
					}
						Truck model = new Truck();
						model.setIdtruck(vec[1]);
						model.setOwner(vec[2]);
						model.setMark(vec[3]);
						model.setNumtires(Long.parseLong(vec[4]));
						model.setWeight(Long.parseLong(vec[5]));
						try {
						DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
						String f =vec[5];
						f = f.replaceAll("\"", "");
						Date fecha = (Date) fechaHora.parse(f);
						model.setDatebuy(new java.sql.Date(fecha.getTime()));
					}catch(Exception e){
						x.getErrors().put(vec[0], vec[5]+ "this not a valid date");
						x.setError(x.getError()+1);
						fail =true;
					}
						
						if(!fail) {
						repo.saveAndFlush(model);
						x.setSusses(x.getSusses()+1);
						}
						
						
					
					})).sender((SendMail<TrucksCsv>) (TrucksCsv x) ->{

				        SimpleMailMessage email = new SimpleMailMessage();

				        email.setTo(x.getT().getEmail());
				        email.setSubject("Registry of trucks");
				        

				        StringBuilder content = new StringBuilder("Grettings mr the results of your csv file was. /n Count errors:");
				    	content.append(System.getProperty("line.separator"));
				        content.append(String.valueOf(x.getError()));
				    	content.append(System.getProperty("line.separator"));
				        content.append(" Count of Success: ");
				    	content.append(System.getProperty("line.separator"));
				        content.append(String.valueOf(x.getSusses()));
				    	content.append(System.getProperty("line.separator"));
				        content.append(" Resume Errors: ");
				    	content.append(System.getProperty("line.separator"));
				        for(String key: x.getErrors().keySet()){
				        	content.append(key+" Error --> "+ x.getErrors().get(key));
				        	content.append(System.getProperty("line.separator"));
				        }
				        
				        email.setText(content.toString());
				       
				        mailSender.send(email);
					}).run();

		} catch (CsvProcessorException e) {
		throw new BussinesException("Fallo al procesar csv" + e.getMessage(),e);
		}

		return null;
	}

	public TruckRepository getRepo() {
		return repo;
	}

	public void setRepo(TruckRepository repo) {
		this.repo = repo;
	}

}

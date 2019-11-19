package com.truckload.core.abstracts;





import com.truckload.core.abstracts.exceptions.CsvProcessorException;
import com.truckload.core.abstracts.interfaces.IECsv;
import com.truckload.core.abstracts.interfaces.IECsv.SendMail;


//this is the core of main process and in this place run the strategy
public abstract class AbstractCsvProcessor<T extends Object> implements IECsv<T> {
	// the generic type its implemented for apply the soli principle of open extension close modify
	private T data;
	private GetFile<T> getfile;
	private ReadCsv<T> csvreader;
	private SaveCsv<T> cvsPerst;
	private SendMail<T> senderMail;
	
	@Override
	public IECsv<T> convertToFile(GetFile<T> getfile) throws CsvProcessorException {
		this.getfile =getfile;
		return this;
	}
	   
	@Override
	public IECsv<T> csvReader(ReadCsv<T> csvreader) throws CsvProcessorException{
		this.csvreader = csvreader;
		return this;
	}

	@Override
	public IECsv<T> persistCsv(SaveCsv<T> cvsPerst) throws CsvProcessorException{
		this.cvsPerst = cvsPerst;
		return this;
	}
	
	@Override
	public IECsv<T> sender(SendMail<T> senderMail) throws CsvProcessorException{
		this.senderMail = senderMail;
		return this;
	}
	
	public AbstractCsvProcessor(T data) {
		super();
		this.data = data;
	}


//that is the context of main processes every method that run in the main process in this place is called
	@Override
	 public void run() throws CsvProcessorException{
		if(getfile == null) {
			throw new  CsvProcessorException("No hay getFile");
		} 
		if(csvreader == null) {
			throw new  CsvProcessorException("No hay csvReaser");
		} 		
		if(cvsPerst == null) {
			throw new  CsvProcessorException("No hay cvsPersis");
		} 
		getfile.convertToFile(data);
		csvreader.readCsv(data);
		cvsPerst.save(data);
		senderMail.send(data);
	 }
}

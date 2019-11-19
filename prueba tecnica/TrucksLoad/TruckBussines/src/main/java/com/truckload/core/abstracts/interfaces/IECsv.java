package com.truckload.core.abstracts.interfaces;


import com.truckload.core.abstracts.exceptions.CsvProcessorException;

//that interface group the functions that run the main proccess if want add another function for the main process, in this place must be registred as functional interface 
public interface IECsv <T extends Object> {
	


	
	
	//this is the interface that run the convertion the base64 to file is an functional interface because the convertions can run as lambda expression
	 @FunctionalInterface
	    public interface GetFile<T extends Object> {
	        public void convertToFile(T data) throws CsvProcessorException;
	    }
	//this is the interface that run the read csv file and get the data is an functional interface because the convertions can run as lambda expression
		
	 @FunctionalInterface
	    public interface ReadCsv<T extends Object> {
	        public void readCsv(T data) throws CsvProcessorException;
	    }
	//this is the interface that run the persistent data the database to file is an functional interface because the convertions can run as lambda expression
		
	 @FunctionalInterface
	    public interface SaveCsv<T extends Object> {
	        public void save(T data) throws CsvProcessorException;
	    }
	//this is the interface that run the send mail process is an functional interface because the convertions can run as lambda expression
		
	 @FunctionalInterface
	    public interface SendMail<T extends Object> {
	        public void send(T data) throws CsvProcessorException;
	    }
	 
	
	 public void run() throws CsvProcessorException;
	public IECsv<T> convertToFile(GetFile<T> getfile) throws CsvProcessorException;
	public IECsv<T> csvReader(ReadCsv<T> csvreader) throws CsvProcessorException;
	public IECsv<T> persistCsv(SaveCsv<T> cvsPerst) throws CsvProcessorException;
	public IECsv<T> sender(SendMail<T> senderMail) throws CsvProcessorException;
	
	 
	 
}

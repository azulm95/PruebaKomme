package com.truckload.core.abstracts.exceptions;

public class CsvProcessorException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int CODEFAIL =2;

	    public CsvProcessorException( String message) {
	        this( message, null);
	    }

	    public CsvProcessorException( String message, Throwable cause) {
	        super(message, cause);
	    }

	
	    public int getCode() {
	        return CODEFAIL;
	    }

	   
	
}

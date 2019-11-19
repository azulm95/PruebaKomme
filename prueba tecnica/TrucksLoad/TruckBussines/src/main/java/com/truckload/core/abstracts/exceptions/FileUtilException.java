package com.truckload.core.abstracts.exceptions;

public class FileUtilException extends Exception {

	private static final long serialVersionUID = 1L;
	protected static final int CODEFAIL=2;
	
	  public FileUtilException(String message) {
	        this(message, null);
	    }

	    public FileUtilException(String message, Throwable cause) {
	        super(message, cause);
	       
	    }
	
	

	    public int getCode() {
	        return CODEFAIL;
	    }

	  
	

}

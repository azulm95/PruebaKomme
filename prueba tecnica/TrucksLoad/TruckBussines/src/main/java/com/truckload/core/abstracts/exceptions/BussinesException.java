package com.truckload.core.abstracts.exceptions;

public class BussinesException extends Exception {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	protected static final int CODEFAIL =3;
	
	  public BussinesException(String message) {
	        this(message, null);
	    }

	    public BussinesException(String message, Throwable cause) {
	        super(message, cause);
	       
	    }
	

	    public int getCode() {
	        return CODEFAIL;
	    }

	 

}

package com.bharath.exception;

public class CustomerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CustomerException(String errorMessage) {
		super(errorMessage);
	}

}

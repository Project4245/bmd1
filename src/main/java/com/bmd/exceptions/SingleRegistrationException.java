package com.bmd.exceptions;

public class SingleRegistrationException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public SingleRegistrationException(String message) {
        super(message);
    }
}

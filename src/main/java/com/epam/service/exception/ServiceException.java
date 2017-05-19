package com.epam.service.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = -8713370935977613577L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	
}

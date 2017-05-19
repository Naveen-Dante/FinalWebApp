package com.epam.dao.exception;

public class DAOException extends Exception{

	private static final long serialVersionUID = -5537931687763748307L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	
}

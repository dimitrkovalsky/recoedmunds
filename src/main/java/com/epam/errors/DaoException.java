package com.epam.errors;

import java.lang.String;

public class DaoException extends ApplicationException {
	public DaoException(String message){
		super(message);
	}

	public DaoException(){
		super();
	}

	public DaoException(Exception cause){
		super(cause);
	}

	public DaoException(String message, Exception cause){
		super(cause);
	}
}
package com.epam.errors;

import java.lang.String;

public class ApplicationException extends Exception {
	public ApplicationException(String message){
		super(message);
	}

	public ApplicationException(){
		super();
	}

	public ApplicationException(Exception cause){
		super(cause);
	}

	public ApplicationException(String message, Exception cause){
		super(cause);
	}
}
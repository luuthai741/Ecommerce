package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorExeption extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ServerErrorExeption(String message) {
		super(message);
	}
	
	
}

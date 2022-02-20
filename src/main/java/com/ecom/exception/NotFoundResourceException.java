package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundResourceException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public NotFoundResourceException(String message) {
		super(message);
	}	
}

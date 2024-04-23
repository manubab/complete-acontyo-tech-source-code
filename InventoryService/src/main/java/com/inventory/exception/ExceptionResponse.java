package com.inventory.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ExceptionResponse 
{
	private String message;
	
	private HttpStatus status;

	public ExceptionResponse(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public ExceptionResponse() {
	}
	
	
	
	

}

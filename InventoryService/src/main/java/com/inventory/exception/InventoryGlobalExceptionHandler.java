package com.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RestControllerAdvice
public class InventoryGlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	
	@ExceptionHandler(value = InventoryNotFound.class)
	public ResponseEntity<Object>  handleExceptions(InventoryNotFound exception, WebRequest webRequest) {
		ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exception.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND);
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;   
	}
	
	
	
	

}

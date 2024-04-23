package com.inventory.exception;

public class TemplateNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String message;

	public TemplateNotFound(String message) {
		this.message = message;
	}
	
	
	
}

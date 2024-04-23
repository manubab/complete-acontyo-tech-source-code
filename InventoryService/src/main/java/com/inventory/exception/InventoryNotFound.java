package com.inventory.exception;

public class InventoryNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public InventoryNotFound(String message) {
		super(message);
		this.message = message;
	}

	public InventoryNotFound() {
		super();
	}

	
	
	

}

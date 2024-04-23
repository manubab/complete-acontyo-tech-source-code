package com.inventory.service;

import org.springframework.http.ResponseEntity;

import com.inventory.entity.InventoryChoice;
import com.inventory.exception.InventoryNotFound;

public interface InventoryService
{
	
	public ResponseEntity<Object> saveInventory(InventoryChoice choice);
	
	public ResponseEntity<Object> updateInventory(InventoryChoice choice);
	
	public ResponseEntity<Object> deleteInventory(Integer columnId)throws InventoryNotFound;
	
	public ResponseEntity<Object> findInventory(Integer columnId)throws InventoryNotFound;
	
	public ResponseEntity<Object> findAllInventories();

}

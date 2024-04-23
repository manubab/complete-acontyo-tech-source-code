package com.inventory.service;

import org.springframework.http.ResponseEntity;

import com.inventory.entity.ColumnCatagiry;

public interface ColumnService
{
	
	public ResponseEntity<?> saveColumn(ColumnCatagiry column);
	
	public ResponseEntity<?> updateColumn(ColumnCatagiry column);
	
	public ResponseEntity<?> deleteColumn(Integer columnId);
	
	public ResponseEntity<?> findById(Integer columnId);
	
	public ResponseEntity<?> findAll();
	
	

}

package com.inventory.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventory.entity.InventoryChoice;
import com.inventory.exception.InventoryNotFound;
import com.inventory.repository.InventoryRepository;
import com.inventory.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
	private final InventoryRepository repository;

	private static final Logger logger=LoggerFactory.getLogger(InventoryServiceImpl.class);
	
	@Autowired
	public InventoryServiceImpl(InventoryRepository repository) {
		this.repository = repository;
	}

	@Override
	public ResponseEntity<Object> saveInventory(InventoryChoice choice)
	{
		logger.info("save inventory service started ");
		Integer columnId=repository.save(choice).getColumnId();
		logger.info("based on inventory choice is saving is completed");
		ResponseEntity<Object> response=new ResponseEntity<Object>(columnId+" saved",HttpStatus.ACCEPTED);	
		return response;
	}

	@Override
	public ResponseEntity<Object> updateInventory(InventoryChoice choice) 
	{
		logger.info("update inventory business logic started ");
		Integer columnId=repository.save(choice).getColumnId();
		logger.info("based on inventory "+columnId+" isupdated in database");
		ResponseEntity<Object> response=new ResponseEntity<Object>(columnId+" updated",HttpStatus.ACCEPTED);	
		return response; 
	}
	
	

	@Override
	public ResponseEntity<Object> deleteInventory(Integer columnId)  {
		try {
			logger.info("delete Inventory is started");
			repository.deleteById(columnId);
			logger.info("delete sucessfully in database ");
			ResponseEntity<Object> response=new ResponseEntity<Object>(columnId+" deleted succesfully",HttpStatus.ACCEPTED);	
			return response; 
		}catch(InventoryNotFound inventoryNotFound) {
			logger.error("inventory not found in table");
			new InventoryNotFound(columnId+" Record not found in database");
		}
		
		logger.error("wrong inventory id passed please give currect Id");
		ResponseEntity<Object> response=new ResponseEntity<Object>(columnId+" not found ",HttpStatus.NOT_FOUND);	
		return response; 
		
		
	}

	@Override
	public ResponseEntity<Object> findInventory(Integer columnId) throws InventoryNotFound{
		try {
			logger.info("find inventory specific logic started");
			Optional<InventoryChoice> inventory=repository.findById(columnId);
			ResponseEntity<Object> response=new ResponseEntity<Object>(inventory.get(),HttpStatus.ACCEPTED);
			logger.info("find inventory specific logic completed");
			return response; 
		}catch(InventoryNotFound inventoryNotFound) {
			new InventoryNotFound(columnId+" Record not found in database");
		}
		logger.info("find inventory id not found please check once and pass currect invenroty id");
		ResponseEntity<Object> response=new ResponseEntity<Object>(columnId+" not found ",HttpStatus.NOT_FOUND);	
		return response; 
	}

	
	@Override
	public ResponseEntity<Object> findAllInventories()
	{	
		logger.info("finding all inventories started from database");
		ResponseEntity<Object> response=new ResponseEntity<Object>(repository.findAll(),HttpStatus.OK);	
		logger.info("finding all inventories completed from database");
		return response; 
	}

}

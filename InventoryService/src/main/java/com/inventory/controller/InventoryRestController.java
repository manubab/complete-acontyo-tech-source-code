package com.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.entity.InventoryChoice;
import com.inventory.exception.InventoryNotFound;
import com.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory/api")
public class InventoryRestController {
	
	private static final Logger logger=LoggerFactory.getLogger(InventoryRestController.class);

	private final InventoryService service;
	
	@Autowired
	public InventoryRestController(InventoryService service) {
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<Object> saveInventory(@RequestBody InventoryChoice choice){
		logger.info("save inventory started");
		ResponseEntity<Object> reponse=service.saveInventory(choice);   
		logger.info("save inventory completed to databse");
		return reponse;
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateInventory(@RequestBody InventoryChoice choice){
		logger.info("update inventory stared");
		ResponseEntity<Object> reponse=	service.updateInventory(choice);
		logger.info("update inventory compelete");
		return reponse;
	}

	@DeleteMapping("/delete/{columnId}")
	public ResponseEntity<Object> deleteInventory(@PathVariable("columnId") Integer columnId) throws InventoryNotFound{
		logger.info("delete inventory started");
		ResponseEntity<Object> reponse=	service.deleteInventory(columnId);
		logger.info("delete inventory completed");
		return reponse;
	}

	@GetMapping("/find/{columnId}")
	public ResponseEntity<Object> findInventory(@PathVariable("columnId") Integer columnId)throws InventoryNotFound{
		logger.info("search inventory started");
		ResponseEntity<Object> reponse=	service.findInventory(columnId);
		logger.info("completed search inventory completed");
		return reponse;
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<Object> findAllInventories(){
		logger.info("finding all inventoryies form database starting");
		ResponseEntity<Object> reponse=	service.findAllInventories();
		logger.info("completed finding all records in database");
		return reponse;
		
		
	}

}

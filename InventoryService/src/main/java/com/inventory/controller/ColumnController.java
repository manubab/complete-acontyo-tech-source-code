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

import com.inventory.entity.ColumnCatagiry;
import com.inventory.entity.ColumnCatagiry;
import com.inventory.service.ColumnService;

@RestController
@RequestMapping("/column")
public class ColumnController {

	
	private final static Logger logger=LoggerFactory.getLogger(ColumnController.class);
	
	private final ColumnService columnService;
	public ColumnController(ColumnService columnService) 
	{	
		this.columnService = columnService;	
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveColumn(@RequestBody ColumnCatagiry column) {
		logger.info("save endpoint statrted here only");
		ResponseEntity response = columnService.saveColumn(column);
		logger.info(response.toString()+"completed the save end point");
		return response;
	}

	@PutMapping(value="/update")
	public ResponseEntity<?> updateColumn(@RequestBody ColumnCatagiry column) {
		logger.info("update api is started");
		ResponseEntity response = columnService.updateColumn(column);
		logger.info("update api is completed to as expected");
		return response;
	}

	
	@DeleteMapping(value="/delete/{columnId}")
	public ResponseEntity<?> deleteColumn(@PathVariable("columnId") Integer columnId){
		logger.info("delete api on Column controller started");
		ResponseEntity response=columnService.deleteColumn(columnId);
		logger.info("delete api is working as expetced ");
		return response;
	}
	

	
	@GetMapping(value= "/findBy/{columnId}")
	public ResponseEntity<?> findById(@PathVariable("columnId") Integer columnId){
		logger.info("find api from column controller started");
		ResponseEntity response= columnService.findById(columnId);
		logger.info("find api based on id working as expexted");
		return response;
	}

	@GetMapping(value="/findAll",produces= {"application/json"})
	public ResponseEntity<?> findAll(){
		logger.info("complete records are retriving from db using api started");
		ResponseEntity<?> response=columnService.findAll();
		logger.info("find all api working as expected");
	return  response;
	}

}

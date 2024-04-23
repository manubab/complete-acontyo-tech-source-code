package com.inventory.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventory.entity.ColumnCatagiry;
import com.inventory.repository.ColumnRespository;
import com.inventory.service.ColumnService;

@Service
public class ColumnServiceImpl implements ColumnService {

	private final ColumnRespository columnRespository;
	
	private static final Logger logger=LoggerFactory.getLogger(ColumnServiceImpl.class);

	@Autowired
	public ColumnServiceImpl(ColumnRespository columnRespository) {
		this.columnRespository = columnRespository;
	}

	@Override
	public ResponseEntity<?> saveColumn(ColumnCatagiry column) {
		logger.info("save column business logic started");
		Integer columnId = columnRespository.save(column).getColumnId();
		logger.info("save column business logic completed");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(columnId + "column stored to db column table");
	}

	@Override
	public ResponseEntity<?> updateColumn(ColumnCatagiry column) {

		logger.info("update column business logic started");
		ColumnCatagiry c = columnRespository.findById(column.getColumnId()).get();
		if (c != null) {
			columnRespository.save(c);
			logger.info("update column business logic complted");
			return ResponseEntity.status(HttpStatus.OK).body(c.toString() + " updated a record");
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" no record found please provide currect details");
		}
	}

	@Override
	public ResponseEntity<?> deleteColumn(Integer columnId) {

		logger.info("delete column business logic started");
		ColumnCatagiry c = columnRespository.findById(columnId).get();
		if (c != null) {
			columnRespository.deleteById(columnId);
			logger.info("delete column business logic completed");
			return ResponseEntity.status(HttpStatus.OK).body(c.getColumnId() + " column deleted sucessfully");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No record found ,please provide currect id ");
	}

	@Override
	public ResponseEntity<?> findById(Integer columnId) {

		logger.info("find by id column business logic started");
		
		ColumnCatagiry c = columnRespository.findById(columnId).get();
		if (c != null) {
			logger.info("save column business logic completed");
			return ResponseEntity.status(HttpStatus.OK).body(c);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" no record prasent please provide currect id");
		}
	}

	@Override
	public ResponseEntity<?> findAll() {
		
		logger.info("find all columns business logic started");
		List<ColumnCatagiry> list = columnRespository.findAll();
		if (list != null && (!list.isEmpty())) {
			logger.info("finaAll column business logic completed");
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no records found in a table");
	}

}

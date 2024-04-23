package com.inventory.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventory.entity.Template;
import com.inventory.exception.TemplateNotFound;
import com.inventory.repository.TemplateRepository;
import com.inventory.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	private static final Logger logger=LoggerFactory.getLogger(TemplateServiceImpl.class);
	
	private final TemplateRepository templateRepository;
	public TemplateServiceImpl(TemplateRepository templateRepository) {
		this.templateRepository = templateRepository;
	}
	
	
	@Override
	public ResponseEntity<Object> updateTemplate(Template template) {
		logger.info("templete update api busniess logic is started");
		Template tem = templateRepository.findById(template.getTemplateId()).get();
		if (tem != null) {
			templateRepository.save(template);
			logger.info("templete update api busniess logic is completed");
			return ResponseEntity.status(HttpStatus.OK).body(tem.getTemplateId()+"updated sucessfully");
		} else {
			logger.info("templete update api busniess logic is failed because no data found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new TemplateNotFound(template.getTemplateId() + "not found in database tables"));
		}
	}

	
	@Override
	public ResponseEntity<Object> deleteTemplate(Integer templateId) {
		logger.info("templete delete api busniess logic is started");
		Template tem=	templateRepository.findById(templateId).get();
		if (tem !=null) {
			logger.info("templete delete api busniess logic is completed");
			templateRepository.deleteById(templateId);
			return ResponseEntity.status(HttpStatus.OK).body(tem.getTemplateId()+"deleted sucess fully");
		}else {
			logger.info("templete delete api busniess logic is failed because no data procent on that id");
		return	ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TemplateNotFound(templateId+" not found in your table"));
		}
		
	}

	@Override
	public ResponseEntity<Object> findById(Integer templateId) {

		logger.info("templete findby id api busniess logic is started");
		Template template = templateRepository.findById(templateId).get();
		if(template!=null) {
			logger.info("templete find by id api busniess logic is comopleted");
		  return ResponseEntity.status(HttpStatus.OK).body(template);
		}else {
			logger.info("templete find by id api busniess logic is failed because no id prasent");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("plese provide currect id");
		}
	}

	@Override
	public ResponseEntity<Object> findAll(){
		logger.info("templete findAll api busniess logic is started");
		List<Template> list=templateRepository.findAll();
		if(list.isEmpty()) {
			
			logger.info("templete findAll api busniess logic is failed no records" );
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no records found");
		}else {
			logger.info("templete fai api busniess logic is completed ");
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}

	@Override
	public ResponseEntity<Object> saveTemplate(Template template) {
		logger.info("templete save api busniess logic is started");
		templateRepository.save(template);
		logger.info("templete save api busniess logic is completed");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(template.getTemplateId()+" saved in database");
	}

}

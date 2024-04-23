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

import com.inventory.entity.Template;
import com.inventory.service.TemplateService;

@RestController
@RequestMapping("/template/api")
public class TemplateController {

	private final static Logger logger =LoggerFactory.getLogger(TemplateController.class);
	
	
	private final TemplateService templateService;
	@Autowired
	public TemplateController(TemplateService templateService) {
		this.templateService = templateService;
	}

	
	@PostMapping(value="/save",consumes= {"application/json"},produces= {"application/json"})
	public ResponseEntity<Object> saveTemplate(@RequestBody Template template) {
		logger.info(" template save api started ");
		
		return templateService.saveTemplate(template);
	}

	@PutMapping(value="/update",consumes= {"application/json"},produces= {"application/json"})
	public ResponseEntity<Object> updateTemplate( @RequestBody Template template) {
		logger.info(" template update api started ");
		return templateService.updateTemplate(template);
	}

	@DeleteMapping(value="/delete/{templateId}")
	public ResponseEntity<Object> deleteTemplate(@PathVariable("templateId") Integer templateId){
		logger.info(" template delete api started ");
		return templateService.deleteTemplate(templateId);
		
	}

	@GetMapping(value="/find/{templateId}")
	public ResponseEntity<Object> findById(@PathVariable("templateId") Integer templateId){
		logger.info(" template find by id api started ");
		return templateService.findById(templateId);
	}

	@GetMapping(value="/findAll")
	public ResponseEntity<Object> findAll(){
		logger.info(" template find All api started ");
		return templateService.findAll();
	}

}

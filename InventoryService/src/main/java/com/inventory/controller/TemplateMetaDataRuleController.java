package com.inventory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.entity.TemplateMetaDataRule;
import com.inventory.service.TemplateMetaDataRuleService;

@RestController
@RequestMapping(value = "/rule")
public class TemplateMetaDataRuleController {

	private static final Logger logger =LoggerFactory.getLogger(TemplateMetaDataRuleController.class);
	private final TemplateMetaDataRuleService templateMetaDataRuleService;

	public TemplateMetaDataRuleController(TemplateMetaDataRuleService templateMetaDataRuleService) {
		this.templateMetaDataRuleService = templateMetaDataRuleService;
	}

	@PostMapping(value="/save",consumes= {"application/json"},produces= {"application/json"})
	public ResponseEntity<Object> saveTemplateMetaDataRule(@RequestBody TemplateMetaDataRule templateMetaDataRule) {
		logger.info("save TemplateMetaDataRule api is started");
		ResponseEntity<Object> response = templateMetaDataRuleService.saveTemplateMetaDataRule(templateMetaDataRule);
		logger.info("save TemplateMetaDataRule api is completed");
		return response;
	}

	@PutMapping(value="/update",consumes= {"application/json"},produces= {"application/json"})
	public ResponseEntity<Object> updateTemplateMetaDataRule( @RequestBody TemplateMetaDataRule templateMetaDataRule) {
		logger.info("update TemplateMetaDataRule api is started");
		ResponseEntity<Object> response = templateMetaDataRuleService.updateTemplateMetaDataRule(templateMetaDataRule);
		logger.info("update TemplateMetaDataRule api is completed");
		return response;
	}
	

	@DeleteMapping(value="/delete/{columnId}",produces= {"application/json"})
	public ResponseEntity<Object> deleteTemplateMetaDataRule(@PathVariable("columnId") Integer columnId) {
		logger.info("delete TemplateMetaDataRule api is started");
		ResponseEntity<Object> response = templateMetaDataRuleService.deleteTemplateMetaDataRule(columnId);
		logger.info("delete TemplateMetaDataRule api is completed");
		return response;
	}

	@GetMapping(value="/find/{columnId}",produces= {"application/json"})
	public ResponseEntity<Object> findTemplateMetaDataRuleById(@PathVariable("columnId") Integer columnId){
		logger.info("findby api TemplateMetaDataRule api is started");
		ResponseEntity<Object> response=templateMetaDataRuleService.findTemplateMetaDataRuleById(columnId);
		logger.info("find by id api TemplateMetaDataRule api is completed");
		return response;
	}

	@GetMapping(value="/findAll",produces= {"application/json"})
	public ResponseEntity<Object> findAllTemplateMetaDataRules(){
		logger.info("findall api TemplateMetaDataRule api is started");
		ResponseEntity<Object> response= templateMetaDataRuleService.findAllTemplateMetaDataRules();
		logger.info("find all TemplateMetaDataRule api is started");
		return response;
	}

}

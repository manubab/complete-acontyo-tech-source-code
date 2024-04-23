package com.inventory.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inventory.entity.TemplateMetaDataRule;
import com.inventory.repository.TemplateMetaDataRulesRepository;
import com.inventory.service.TemplateMetaDataRuleService;

@Service
public class TemplateMetaDataRuleServiceImpl implements TemplateMetaDataRuleService {

	private final TemplateMetaDataRulesRepository templateMetaDataRulesRepository;
	
	private static final Logger logger=LoggerFactory.getLogger(TemplateMetaDataRuleServiceImpl.class);

	public TemplateMetaDataRuleServiceImpl(TemplateMetaDataRulesRepository templateMetaDataRulesRepository) {
		this.templateMetaDataRulesRepository = templateMetaDataRulesRepository;
	}

	@Override
	public ResponseEntity<Object> saveTemplateMetaDataRule(TemplateMetaDataRule templateMetaDataRule) {
		logger.info("save TemplateMetaDataRule business logic started ");
		templateMetaDataRulesRepository.save(templateMetaDataRule);
		logger.info("save TemplateMetaDataRule business logic completed ");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(templateMetaDataRule+ "saved this id");
	}

	@Override
	public ResponseEntity<Object> updateTemplateMetaDataRule(TemplateMetaDataRule templateMetaDataRule) {

		logger.info("update TemplateMetaDataRule business logic started ");

		TemplateMetaDataRule template = templateMetaDataRulesRepository.findById(templateMetaDataRule.getColumnId())
				.get();
		if (template != null) {
			logger.info("update TemplateMetaDataRule business logic compled ");

			templateMetaDataRulesRepository.save(templateMetaDataRule);
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(template + "  based on this id  updated account ");
		} else {
			logger.info("update TemplateMetaDataRule business logic failed ");

			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
					.body(templateMetaDataRule + " not found in the database");
		}
	}
	@Override
	public ResponseEntity<Object> deleteTemplateMetaDataRule(Integer columnId) {

		logger.info("delete TemplateMetaDataRule business logic started ");

		TemplateMetaDataRule rule = templateMetaDataRulesRepository.findById(columnId).get();
		if (rule != null) {
			templateMetaDataRulesRepository.deleteById(columnId);
			logger.info("delete TemplateMetaDataRule business logic completed ");

			return ResponseEntity.status(HttpStatus.OK).body(rule + " record deleted sucess fully");
			
		}else {
			logger.info("delete TemplateMetaDataRule business logic failed  ");

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no record prasent please provide currect integer value ");
		}	
	}

	@Override
	public ResponseEntity<Object> findTemplateMetaDataRuleById(Integer columnId) {

		logger.info("find TemplateMetaDataRule business logic started ");

		TemplateMetaDataRule rule = templateMetaDataRulesRepository.findById(columnId).get();
		if (rule != null) {
			logger.info("find TemplateMetaDataRule business logic started ");
			return ResponseEntity.status(HttpStatus.OK).body(rule);
		}else {	
			logger.info("find TemplateMetaDataRule business logic started ");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no record prasent please provide currect integer value ");
		}	
	}

	
	@Override
	public ResponseEntity<Object> findAllTemplateMetaDataRules() {
	
		logger.info("findAll TemplateMetaDataRule business logic started ");

		List<TemplateMetaDataRule> list=templateMetaDataRulesRepository.findAll();

		if(list.isEmpty()) {
			logger.info("findAll TemplateMetaDataRule business logic completed but no records");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no record prasent in a table ");
		}
		logger.info("findAll TemplateMetaDataRule business logic completed ");
		return ResponseEntity.status(HttpStatus.OK).body(list);

		
	}

}

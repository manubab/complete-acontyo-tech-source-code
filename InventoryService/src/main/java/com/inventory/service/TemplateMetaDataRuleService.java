package com.inventory.service;

import org.springframework.http.ResponseEntity;

import com.inventory.entity.TemplateMetaDataRule;

public interface TemplateMetaDataRuleService 
{
	
	public ResponseEntity<Object> saveTemplateMetaDataRule(TemplateMetaDataRule templateMetaDataRule);
	
	public ResponseEntity<Object> updateTemplateMetaDataRule(TemplateMetaDataRule templateMetaDataRule);
	
	public ResponseEntity<Object> deleteTemplateMetaDataRule(Integer columnId);
	
	public ResponseEntity<Object> findTemplateMetaDataRuleById(Integer columnId);
	
	public ResponseEntity<Object> findAllTemplateMetaDataRules();
	
	

}

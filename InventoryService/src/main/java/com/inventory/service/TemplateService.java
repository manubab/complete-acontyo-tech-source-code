package com.inventory.service;

import org.springframework.http.ResponseEntity;

import com.inventory.entity.Template;

public interface TemplateService
{
	public ResponseEntity<Object> saveTemplate(Template template);
	
	public ResponseEntity<Object> updateTemplate(Template template);
	
	public ResponseEntity<Object> deleteTemplate(Integer templateId);
	
	public ResponseEntity<Object> findById(Integer templateId);
	
	public ResponseEntity<Object> findAll();

}

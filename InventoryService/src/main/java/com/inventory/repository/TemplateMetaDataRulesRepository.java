package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.TemplateMetaDataRule;

public interface TemplateMetaDataRulesRepository extends JpaRepository<TemplateMetaDataRule, Integer>
{
	
}

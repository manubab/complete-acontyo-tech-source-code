package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Integer>
{

	

}

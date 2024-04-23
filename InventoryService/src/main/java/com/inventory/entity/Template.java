package com.inventory.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Template {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer templateId;
	private String templateName;
	private Integer noOfColumns;
	
	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL)
	private List<TemplateMetaDataRule> rules;

	
}




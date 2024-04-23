package com.inventory.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ColumnCatagiry {
	
	@Id
	private Integer columnId;

	private Integer roleId;

	private String roleDiscription;

	private String conditionDiscription;

	@ManyToOne
	@JoinColumn(name = "columns")
	private TemplateMetaDataRule columns;
	
	
}


	

package com.inventory.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TemplateMetaDataRule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer columnId;

	private String columnName;

	private String columnDataType;

	private Integer columnLength;

	@ManyToOne
	@JoinColumn(name = "template")
	private Template template;

	@OneToMany(mappedBy = "columns", cascade = CascadeType.ALL)
	private List<ColumnCatagiry> columns;

}

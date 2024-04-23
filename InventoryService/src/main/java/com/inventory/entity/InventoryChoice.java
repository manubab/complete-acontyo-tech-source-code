package com.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity 
@Table(name="Inventory")
public class InventoryChoice 
{
	@Id
	private Integer columnId;
	private String columnName;
	private String columnDistription;
}

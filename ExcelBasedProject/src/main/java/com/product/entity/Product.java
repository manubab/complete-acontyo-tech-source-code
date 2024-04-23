package com.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product 
{
	@Id
	private String productId;
	
	private String productName;
	
	private Double productPrice;
	
	

	
}

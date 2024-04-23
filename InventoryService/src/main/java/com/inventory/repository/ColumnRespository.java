package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.ColumnCatagiry;

public interface ColumnRespository extends JpaRepository<ColumnCatagiry, Integer>
{
	
	

}

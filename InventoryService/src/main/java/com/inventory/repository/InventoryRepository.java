package com.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.inventory.entity.InventoryChoice;

public interface InventoryRepository extends JpaRepository<InventoryChoice, Integer>
{

	public Optional<InventoryChoice> findById(Integer columnId);
	
}

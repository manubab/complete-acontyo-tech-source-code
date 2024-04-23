package com.acintyo.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class AdminServiceCenter 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String centerName;
	
	private String centerLocation;
	
	private String contact;
	
	private String email;
	
	private LocalTime centerOpenTime;
	
	private LocalTime centerCloseTime;
	
	private LocalDate registrationDate;
	
	@CreationTimestamp
	private LocalDateTime serviceOptedOn;
	
	private String insertedBy;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedOn;
	
	private String updatedBy;

}

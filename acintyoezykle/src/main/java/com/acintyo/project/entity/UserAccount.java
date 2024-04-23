package com.acintyo.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserAccount
{
	
	@Id
	@Column( unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String bankName;
	
	private String accountNumber;
	
	private String ifscCode;
	
	private String branch;
	
	private LocalDate registrationDate;
	
	@CreationTimestamp
	private LocalDateTime serviceOptedOn;

	private String insertedBy;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedOn;

	private String updatedBy;
	

}

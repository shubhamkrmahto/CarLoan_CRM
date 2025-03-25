package com.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String customerName;
	private LocalDate dateOfBirth;
	private Integer age;
	private String gender;
	private Long mobileNumber;
	private Long alternateNumber;
	private String email;
	private String city;
	private String state;
	
	
	
	

}

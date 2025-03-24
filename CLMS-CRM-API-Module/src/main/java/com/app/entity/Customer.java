package com.app.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Customer {
	
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

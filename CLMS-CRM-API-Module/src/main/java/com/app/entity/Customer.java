package com.app.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String customerName;
	@DateTimeFormat
	private LocalDate dateOfBirth;
	private Integer age;
	private String gender;
	private String state;
	private Long customerContactNumber;
	private Long customerAlternateNumber;
	private String customerEmailId;
	private String customerPermanentAddress;
	private String customerCity;
	private Integer customerPincode;
	private Long aadharNo;
	private String panCardNo;
	
	@CreationTimestamp
	private LocalDate enquiryDateTime;
	
	@Lob
	@Column(length = 999999999)
	private byte[] profilePicture;

}

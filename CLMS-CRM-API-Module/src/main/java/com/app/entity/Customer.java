package com.app.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.app.enums.CustomerEmailStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
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
	@Column(unique = true)
	private String userName;
	@Column(unique = true)
	private String password;
	@DateTimeFormat
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private Integer age;
	private String gender;
	private String state;
	private Long customerContactNumber;
	private Long customerAlternateNumber;
	private String customerEmailId;
	@Enumerated(EnumType.STRING)
	private CustomerEmailStatus emailStatus;
	private String customerPermanentAddress;
	private String customerCity;
	private Integer customerPincode;
	private Long aadharNo;
	private String panCardNo;
	
	@Lob
	@Column(length = 999999999)
	private byte[] profilePicture;
	
	@OneToOne(cascade = CascadeType.ALL)
	private LoanEnquiry le;
}
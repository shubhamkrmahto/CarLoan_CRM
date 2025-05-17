package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class LoanGuarantor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loanGuarantorId;

	private String guarantorName;
	private String guarantorDateOfBirth;
	private String guarantorRelationShipWithCustomer;
	private Long guarantorMobileNumber;
	private Long guarantorAdharCardNumber;
	private String guarantorMortgageDetails;
	private String guarantorJobDetails;
	private String guarantorLocalAddress;
	private String guarantorPermanentAddress;
	
	
}

package com.app.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class LoanGuarantor {

	private Integer guarantorId;
	private String guarantorName;
	private String guarantorDateOfBirth;
	private String guarantorRelationShipWithCustomer;
	private long guarantorMobileNumber;
	private long guarantorAdharCardNumber;
	private String guarantorMorgageDetails;
	private String guarantorJobDetails;
	private String guarantorLocalAddress;
	private String guarantorPermanentAddress;
	
}

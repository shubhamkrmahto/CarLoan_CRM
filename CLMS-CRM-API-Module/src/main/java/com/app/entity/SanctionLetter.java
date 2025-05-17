package com.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class SanctionLetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sanctionLetterId;
	private LocalDate sanctionDate;
	private String applicantName;
	private Long contactDetails;
	private String applicantEmail;
	private Double loanAmtountSanctioned;
	private String interestType;
	private Double rateOfInterest;
	private Integer loanTenureInMonth;
	private Double monthlyEMIAmount;
	private Double loanAmount;
	private String modeOfPayment;
	private String remarks;
	private String termsAndCondition;
	private String status;
	private Integer cibilScore;
	
	@Lob
	@Column(length = 9000000)
	private byte[] sanctionLetter;

}

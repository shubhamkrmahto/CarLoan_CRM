package com.app.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDisbursement {
	
	private Integer	agreementId;
	private Integer loanNo;
	private LocalDate agreementDate;
	private String amountPayType;
	private Double totalAmount;
	private String bankName;
	private Long accountNumber;
	private String ifscCode;
	private String accountType;
	private Double transferAmount;
	private String paymentStatus;
	private LocalDate amountPaidDate;

}
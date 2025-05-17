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
public class Ledger {
	
	private Integer ledgerId;
	private LocalDate ledgerCreatedDate;
	private Double totalLoanAmount;
	private Double payableAmountWithInterest;
	private Integer tenure;
	private Double monthlyEMI;
	private Double amountPaidTillDate;
	private Double remainingAmount;
	private LocalDate nextEmiDateStart;
	private LocalDate nextEmiDateEnd;
	private Integer defaulterCount;
	private String previousEMIStatus;
	private String currentMonthEMIStatus;
	private LocalDate loanEndDate;
	private String loanStatus;
}
package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class LoanApplication {
	@Id
	@GeneratedValue
	 private Integer applicationId;
	 private Customer customer;
	 private Double loanAmount;
	 private String loanStatus;
	 private PersonalDocuments documents;
	 private DependentInfo dependent;
	 private customerAddress address;
	 private MedicalInfo medicalInfo;
	 private Cibil cibil;
	 private CurrentLoanDetails currentLoanDetails;
	 private PreviousLoanDetails previousLoanDetails;
	 private BankAccountDetails bankDetails;
	 private LoanGuarantor loanGuarantor;
	 private LoanDisbursement loanDisbursement;
	 private Ledger ledger;
	 private SanctionLetter sanctionLetter;
	 private CustomerVerification verification;
	 
	 

}

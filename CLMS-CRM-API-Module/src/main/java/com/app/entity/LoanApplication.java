package com.app.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class LoanApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer applicationId;
	
	@OneToOne(cascade = CascadeType.ALL)
	 private Customer customer;
	
	 private Double loanAmount;
	 
	 private String loanStatus;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private PersonalDocuments documents;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private DependentInfo dependent;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private CustomerAddress address;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private MedicalInfo medicalInfo;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private Cibil cibil;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private CurrentLoanDetails currentLoanDetails;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private PreviousLoanDetails previousLoanDetails;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private BankAccountDetails bankDetails;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private LoanGuarantor loanGuarantor;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private LoanDisbursement loanDisbursement;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private Ledger ledger;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private SanctionLetter sanctionLetter;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private CustomerVerification verification;
	 
	 

}

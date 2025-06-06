package com.app.entity;

import com.app.enums.LoanApplicationStatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	 @OneToOne
	 private Customer customer;
	
	 private Double loanAmount;
	 
	 @Enumerated(EnumType.STRING)
	 private LoanApplicationStatusEnum loanApplicationStatus;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private PersonalDocuments documents;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private DependentInfo dependent;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private CustomerAddress address;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private MedicalInfo medicalInfo;
	 
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
	 private SanctionLetter sanctionLetter;
	 
	 @OneToOne(cascade = CascadeType.ALL)
	 private CustomerVerification verification;
	 
	 

}

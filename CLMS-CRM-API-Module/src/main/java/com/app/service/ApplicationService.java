package com.app.service;

<<<<<<< HEAD
import java.io.IOException;
=======
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git

import org.springframework.web.multipart.MultipartFile;
<<<<<<< HEAD

import com.app.entity.BankAccountDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanGuarantor;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
=======
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git

public interface ApplicationService {


	public String saveLoanApplication(LoanEnquiry loanenquiry, MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data);
	
	public LoanApplication getById(Integer id);
	
	public Double getLoanAmount(Integer id);

}

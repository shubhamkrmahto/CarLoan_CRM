package com.app.service;

import java.util.List;
import java.util.Optional;
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.enums.PersonalDocumentStatusEnum;
import com.app.entity.BankAccountDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanApplication;
import com.app.entity.LoanGuarantor;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ApplicationService {


	public String saveLoanApplication(LoanEnquiry loanenquiry, MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data);
	
	public LoanApplication getById(Integer id);
	
	public Double getLoanAmount(Integer id);


	public void statusUpdates(Integer id, PersonalDocuments status);

	public List<LoanApplication> getLoanAppsSentToOE();

	public void statusUpdatesToVerified(Integer id, PersonalDocumentStatusEnum verified);

}

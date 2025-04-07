package com.app.service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;

public interface ApplicationService {


	public String saveLoanApplication(LoanEnquiry loanenquiry, MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data);
	
	public LoanApplication getById(Integer id);
	
	public Double getLoanAmount(Integer id);
	

}

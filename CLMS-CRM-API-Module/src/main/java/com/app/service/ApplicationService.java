package com.app.service;

import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.LoanApplication;
import org.springframework.web.multipart.MultipartFile;

public interface ApplicationService {


	public String saveLoanApplication(Customer customer,Cibil cibil, MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data);
	
	public LoanApplication getById(Integer id);
	
	public Double getLoanAmount(Integer id);
}

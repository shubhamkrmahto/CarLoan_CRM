package com.app.service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.BankAccountDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
import com.app.entity.LoanGuarantor;
import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;

import java.util.List;

public interface ApplicationService {


	public String saveLoanApplication(LoanEnquiry loanenquiry, MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data);
	
	public LoanApplication getById(Integer id);
	
	public Double getLoanAmount(Integer id);
	

	public String updateLocalAddress(LocalAddress local, Integer id);

	public String updateMedicalInfoDocs(Integer id, MedicalInfo medicalInfor);

	public String updatePersonalDocm(Integer id, PersonalDocuments pdocuments);

	public String updatePermanentAddress(Integer id, PermanentAddress permanentAddress);
	
	public String updateBankDetails(Integer id, BankAccountDetails bad);
	
	public String updateCustomerVerification(Integer id, CustomerVerification cv);
	
	public String updateCustomerDetails(Integer id, Customer c);

	public String updateGuarantorDetails(Integer id, LoanGuarantor lg);
	
	public String updateStatusToDocumentVerified(Integer id);
	
	public String updateStatusToDocumentRejected(Integer id);
	
	public List<LoanApplication> getLoanStatusToForwardToOE();

}

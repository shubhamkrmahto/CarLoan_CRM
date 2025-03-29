package com.app.service;

import java.util.Optional;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.entity.BankAccountDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanGuarantor;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ApplicationService {


	public String updatePersonalDocm(Integer id, PersonalDocuments pdocuments);

	public String updatePermanentAddress(Integer id, PermanentAddress permanentAddress);
	
	public String updateBankDetails(Integer id, BankAccountDetails bad);
	
	public String updateCustomerVerification(Integer id, CustomerVerification cv);
	
	public String updateCustomerDetails(Integer id, Customer c);

	public String updateGuarantorDetails(Integer id, LoanGuarantor lg);

	public String saveLoanApplication(Customer customer,MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data) throws IOException,JsonMappingException, JsonProcessingException;

}

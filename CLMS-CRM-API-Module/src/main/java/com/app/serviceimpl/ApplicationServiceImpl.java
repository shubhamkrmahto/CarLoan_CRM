package com.app.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.BankAccountDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanApplication;
import com.app.entity.LoanGuarantor;
import com.app.repo.ApplicationRepository;
import com.app.service.ApplicationService;
@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;

	@Override
	public String updateBankDetails(Integer id, BankAccountDetails bad) {
		// TODO Auto-generated method stub
		
		Optional<LoanApplication> byId = appRepo.findById(id);
		
		
		
		if(byId.isPresent())
		{
			LoanApplication loanApplication = byId.get();
			
			loanApplication.getBankDetails().setAccountHolderName(bad.getAccountHolderName());
			loanApplication.getBankDetails().setAccountNumber(bad.getAccountNumber());
			loanApplication.getBankDetails().setAccountStatus(bad.getAccountStatus());
			loanApplication.getBankDetails().setAccountType(bad.getAccountType());
			loanApplication.getBankDetails().setAccountBalance(bad.getAccountBalance());
			
			appRepo.save(loanApplication);
			
			return "Bank Account Details updated Successfully.";
			
		}
		
		return "Bank Account Details not present for this Loan Application.";
	}

	@Override
	public String updateCustomerVerification(Integer id, CustomerVerification cv) {
		// TODO Auto-generated method stub
		
			Optional<LoanApplication> byId = appRepo.findById(id);
		
		if(byId.isPresent())
		{
			LoanApplication loanApplication = byId.get();
			
			loanApplication.getVerification().setStatus(cv.getStatus());
			loanApplication.getVerification().setRemarks(cv.getRemarks());
			loanApplication.getVerification().setVerificationDate(cv.getVerificationDate());
			
			appRepo.save(loanApplication);
			
			return "Customer Verification has been updated Successfully.";
			
		}
		
		return "Customer Verification not present for this Loan Application.";
	}
	
	@Override
	public String updateCustomerDetails(Integer id, Customer c) {
		// TODO Auto-generated method stub
		
			Optional<LoanApplication> byId = appRepo.findById(id);
		
		if(byId.isPresent())
		{
			LoanApplication loanApplication = byId.get();
			
			loanApplication.getCustomer().setCustomerName(c.getCustomerName());
			loanApplication.getCustomer().setDateOfBirth(c.getDateOfBirth());
			loanApplication.getCustomer().setAge(c.getAge());
			loanApplication.getCustomer().setGender(c.getGender());
			loanApplication.getCustomer().setState(c.getState());
			loanApplication.getCustomer().setCustomerContactNumber(c.getCustomerContactNumber());
			loanApplication.getCustomer().setCustomerAlternateNumber(c.getCustomerAlternateNumber());
			loanApplication.getCustomer().setCustomerEmailId(c.getCustomerEmailId());
			loanApplication.getCustomer().setCustomerPermanentAddress(c.getCustomerPermanentAddress());
			loanApplication.getCustomer().setCustomerCity(c.getCustomerCity());
			loanApplication.getCustomer().setCustomerPincode(c.getCustomerPincode());
			loanApplication.getCustomer().setAadharNo(c.getAadharNo());
			loanApplication.getCustomer().setPanCardNo(c.getPanCardNo());
			
			appRepo.save(loanApplication);
			
			return "Customer Details has been updated Successfully.";
			
		}
		
		return "Customer Details are not present for this Loan Application.";
	}

	@Override
	public String updateGuarantorDetails(Integer id, LoanGuarantor lg) {
		// TODO Auto-generated method stub
		
		Optional<LoanApplication> byId = appRepo.findById(id);
	
	if(byId.isPresent())
	{
		LoanApplication loanApplication = byId.get();
		
		loanApplication.getLoanGuarantor().setGuarantorName(lg.getGuarantorName());
		loanApplication.getLoanGuarantor().setGuarantorDateOfBirth(lg.getGuarantorDateOfBirth());
		loanApplication.getLoanGuarantor().setGuarantorRelationShipWithCustomer(lg.getGuarantorRelationShipWithCustomer());
		loanApplication.getLoanGuarantor().setGuarantorMobileNumber(lg.getGuarantorMobileNumber());
		loanApplication.getLoanGuarantor().setGuarantorAdharCardNumber(lg.getGuarantorAdharCardNumber());
		loanApplication.getLoanGuarantor().setGuarantorMortgageDetails(lg.getGuarantorMortgageDetails());
		loanApplication.getLoanGuarantor().setGuarantorJobDetails(lg.getGuarantorJobDetails());
		loanApplication.getLoanGuarantor().setGuarantorLocalAddress(lg.getGuarantorLocalAddress());
		loanApplication.getLoanGuarantor().setGuarantorPermanentAddress(lg.getGuarantorPermanentAddress());
		
		appRepo.save(loanApplication);
		
		return "Loan Guarantor Details has been updated Successfully.";
		
	}
	
	return "Loan Guarantor Details are not present for this Loan Application.";

	}
	
	

}

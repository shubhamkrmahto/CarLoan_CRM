package com.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
import com.app.entity.LoanGuarantor;
import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.enums.LoanApplicationStatusEnum;
import com.app.repository.ApplicationRepository;
import com.app.entity.BankAccountDetails;
import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;

	@Autowired
	JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;  

	
	@Override
	public String saveLoanApplication(LoanEnquiry loanEnquiry,MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,MultipartFile bankCheque, MultipartFile salarySlips,String data){
				

		try {		
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			
			LoanApplication value = mapper.readValue(data, LoanApplication.class);
			
			System.out.println(data);
			System.out.println(value);
			
			
			Customer cc = new Customer();
			
			cc.setCustomerName(loanEnquiry.getCustomerName());//loan
			cc.setDateOfBirth(loanEnquiry.getDateOfBirth());//loan
			cc.setAge(value.getCustomer().getAge());
			cc.setGender(loanEnquiry.getGender());//loan
			cc.setState(value.getCustomer().getState());
			cc.setCustomerContactNumber(loanEnquiry.getCustomerContactNumber());//loan
			cc.setCustomerAlternateNumber(loanEnquiry.getCustomerAlternateNumber());//loan
			cc.setCustomerEmailId(loanEnquiry.getCustomerEmailId());//loan
			cc.setCustomerPermanentAddress(value.getCustomer().getCustomerPermanentAddress());
			cc.setCustomerCity(value.getCustomer().getCustomerCity());
			cc.setCustomerPincode(value.getCustomer().getCustomerPincode());
			cc.setAadharNo(loanEnquiry.getAadharNo());//loan
			cc.setPanCardNo(loanEnquiry.getPanCardNo());//loan
			
			
			value.setCustomer(cc);
			
			
			Cibil cb = new Cibil();
			cb.setCibilScore(loanEnquiry.getCibil().getCibilScore());
			cb.setCibilStatus(loanEnquiry.getCibil().getCibilStatus());
			
			value.setCibil(cb);
			
			PersonalDocuments docs = new PersonalDocuments();
			
			docs.setAddressProof(addressProof.getBytes());
			docs.setPanCard(panCard.getBytes());
			docs.setIncomeTax(incomeTax.getBytes());
			docs.setAadharCard(aadharCard.getBytes());
			docs.setPhoto(photo.getBytes());
			docs.setSignature(signature.getBytes());
			docs.setBankCheque(bankCheque.getBytes());
			docs.setSalarySlips(salarySlips.getBytes());
				
			value.setDocuments(docs);
			
			value.setLoanApplicationStatus(LoanApplicationStatusEnum.LOAN_APPLICATION_SUBMITTED);
			
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom(from);
			mail.setTo(loanEnquiry.getCustomerEmailId());
			mail.setSubject("Loan Application");
	        mail.setText("Dear Customer, Your Car loan Application has been submitted successfully.\n"+value.getCustomer().getCustomerName());
			System.out.println(value);
			
	        appRepo.save(value);
	       
	        
	        sender.send(mail);
	        
			return "Loan Application Submitted successfully";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Please Enter the details Correctly";
		}
		
	}
	
	

	public LoanApplication getById(Integer id)
	{
		Optional<LoanApplication> byId = appRepo.findById(id);
		
		return byId.get();
	}



	@Override
	public Double getLoanAmount(Integer id) {
		// TODO Auto-generated method stub
		
		LoanApplication la = getById(id);
		
		return la.getLoanAmount();
	}

	@Override
	public String updatePermanentAddress(Integer id, PermanentAddress permanentAddress) {
		
		  Optional<LoanApplication> byId = appRepo.findById(id);
		
		if(byId.isPresent())
		{
		 LoanApplication pAddress=byId.get();
		 
		 pAddress.getAddress().getLaddr().setAreaName(permanentAddress.getAreaName());
	     pAddress.getAddress().getLaddr().setCityName(permanentAddress.getCityName());
		 pAddress.getAddress().getLaddr().setDistrict(permanentAddress.getDistrict());
		 pAddress.getAddress().getLaddr().setHouseNumber(permanentAddress.getHouseNumber());
		 pAddress.getAddress().getLaddr().setPincode(permanentAddress.getPincode());	
		 pAddress.getAddress().getLaddr().setStreetName(permanentAddress.getStreetName());
	     pAddress.getAddress().getLaddr().setState(permanentAddress.getState());
	
	     appRepo.save(pAddress);
		
		return "Permanent Address has been Updated....!";
		}
		return "Record is not available";
	}

	@Override
	public String updatePersonalDocm(Integer id, PersonalDocuments pdocuments) {

		Optional<LoanApplication> byId = appRepo.findById(id);
		if(byId.isPresent())
		{
		LoanApplication application=byId.get();
		
		application.getDocuments().setAadharCard(pdocuments.getAadharCard());
		application.getDocuments().setAddressProof(pdocuments.getAddressProof());
		application.getDocuments().setBankCheque(pdocuments.getBankCheque());
		application.getDocuments().setIncomeTax(pdocuments.getIncomeTax());
		application.getDocuments().setSalarySlips(pdocuments.getSalarySlips());
		application.getDocuments().setSignature(pdocuments.getSignature());
		application.getDocuments().setPanCard(pdocuments.getPanCard());
         appRepo.save(application);
		
		
		return "Personal Doc has been Updated....! ";
		}
		return "Record is not available";
	}
	
	
	@Override
	public String updateBankDetails(Integer id, BankAccountDetails bad) {
				
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
	
	@Override
	public String updateLocalAddress(LocalAddress local, Integer id) {
		
		Optional<LoanApplication> byId = appRepo.findById(id);
		LoanApplication loanApplication = byId.get();
	
		
			if(byId.isPresent()) {
				
		loanApplication.getAddress().getLaddr().setAreaName(local.getAreaName());
		loanApplication.getAddress().getLaddr().setCityName(local.getCityName());
		loanApplication.getAddress().getLaddr().setDistrict(local.getDistrict());
		loanApplication.getAddress().getLaddr().setState(local.getState());
		loanApplication.getAddress().getLaddr().setPincode(local.getPincode());
		loanApplication.getAddress().getLaddr().setHouseNumber(local.getHouseNumber());
		loanApplication.getAddress().getLaddr().setStreetName(local.getStreetName());
		
		appRepo.save(loanApplication);
//		log.info("Local Address is Updated...");
		return "Data Updated Successfully";
	}		
		return "Address not present for this id";
	}

	@Override
	public String updateMedicalInfoDocs(Integer id, MedicalInfo medicalInfor) {

		Optional<LoanApplication> byId = appRepo.findById(id);
		LoanApplication loanApplication = byId.get();
	
			if(byId.isPresent()) {
			loanApplication.getMedicalInfo().setPatientId(medicalInfor.getPatientId());
			loanApplication.getMedicalInfo().setProfessionalPatientName(medicalInfor.getProfessionalPatientName());
			loanApplication.getMedicalInfo().setBillingDate(medicalInfor.getBillingDate());
			loanApplication.getMedicalInfo().setLoanAmount(medicalInfor.getLoanAmount());
			loanApplication.getMedicalInfo().setTreatment(medicalInfor.getTreatment());
			 
			appRepo.save(loanApplication);
			
//			log.info("Medical Information is Updated...");
			return "Data updated Successfully";
			}
		
		return "MedicalInfo not present for this id";

	}



	@Override
	public String updateStatusToDocumentVerified(Integer id) {
		// TODO Auto-generated method stub
		
	  LoanApplication byId = getById(id);
	  byId.setLoanApplicationStatus(LoanApplicationStatusEnum.DOCUMENTS_VERIFIED);
	  
	  appRepo.save(byId);
		
		return "Documents has been verified for Loan Application id : "+id;
	}



	@Override
	public String updateStatusToDocumentRejected(Integer id) {
		// TODO Auto-generated method stub
		
		  LoanApplication byId = getById(id);
		  byId.setLoanApplicationStatus(LoanApplicationStatusEnum.DOCUMENTS_REJECTED);
		  
		  appRepo.save(byId);
			
			return "Documents has been Rejected for Loan Application id : "+id;
	}



	@Override
	public List<LoanApplication> getLoanStatusToForwardToOE() {

		return appRepo.findAllByLoanApplicationStatus(LoanApplicationStatusEnum.FORWARDED_TO_OE);
	}



	@Override
	public List<Integer> getLoanApplicationsID() {
		// TODO Auto-generated method stub
		List<LoanApplication> applications = appRepo.findAll();
		
		List<Integer> applied = new ArrayList<>();
		
		for(LoanApplication l : applications) {
			applied.add(l.getApplicationId());		
			}
		
		return applied;
	}
	
	
	//DependentInfo Patch mapping
	//Previous Loan Details

}

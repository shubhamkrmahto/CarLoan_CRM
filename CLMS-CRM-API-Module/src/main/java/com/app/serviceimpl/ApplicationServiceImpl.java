package com.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.SanctionDetailDTO;
import com.app.entity.BankAccountDetails;
import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
import com.app.entity.LoanGuarantor;
import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.enums.LoanApplicationStatusEnum;
import com.app.repository.ApplicationRepository;
import com.app.repository.CustomerRepository;
import com.app.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;
	
	
	@Autowired
	CustomerRepository customerRepo;


	@Autowired
	JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	
	@Override
	public String saveLoanApplication(Integer id, Customer customer,MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,MultipartFile bankCheque, MultipartFile salarySlips,String data){
				

		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			
			LoanApplication value = mapper.readValue(data, LoanApplication.class);
			
			Optional<Customer> existingCustomer = customerRepo.findByCustomerId(id);
			
			if (existingCustomer.isPresent()) {
			    value.setCustomer(existingCustomer.get());
			} else {
			    // Customer not found — return error or handle accordingly
			    log.error("Customer with ID " + id + " not found.");
			    return "Customer not found. Please register the customer before applying for loan.";
			}
			
			log.info("LoanApplication Json Data : "+data);
			System.out.println(value);
			
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
			mail.setTo(customer.getCustomerEmailId());
			mail.setSubject("Loan Application");
	        mail.setText("Dear Customer, Your Car loan Application has been submitted successfully.\n"+value.getCustomer().getCustomerName());
			System.out.println(value);
			
	        appRepo.save(value);
	       
	        
	        sender.send(mail);
	        
	        log.info("Loan Application Submitted and Mail Sent successfully...! :- "+value.getApplicationId());
			return "Loan Application Submitted successfully";
			
		} catch (Exception e) {
			log.error("LoanApplication does not save "+e.getMessage());
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
		log.info("Permanent Address has been Updated....! :- "+pAddress.getApplicationId());
		return "Permanent Address has been Updated....!";
		}
		log.warn("Record is not available");
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
		
		log.info("Personal Doc has been Updated....! :- " +application.getApplicationId());
		return "Personal Doc has been Updated....! ";
		}
		log.warn("Record is not available");
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
			log.info("Bank Account Details updated Successfully...! :- "+loanApplication.getApplicationId());
			return "Bank Account Details updated Successfully...!";
			
		}
		log.warn("Bank Account Details not present for this Loan Application...!");
		return "Bank Account Details not present for this Loan Application...!";
	}

	@Override
	public String updateCustomerVerification(Integer id, CustomerVerification cv) {
		
			Optional<LoanApplication> byId = appRepo.findById(id);
		
		if(byId.isPresent())
		{
			LoanApplication loanApplication = byId.get();
			
			loanApplication.getVerification().setStatus(cv.getStatus());
			loanApplication.getVerification().setRemarks(cv.getRemarks());
			loanApplication.getVerification().setVerificationDate(cv.getVerificationDate());
			
			appRepo.save(loanApplication);
			log.info("Customer Verification has been updated Successfully...! :- "+loanApplication.getApplicationId());
			return "Customer Verification has been updated Successfully...!";
			
		}
		log.warn("Customer Verification not present for this Loan Application...!");
		return "Customer Verification not present for this Loan Application...!";
	}
	
	@Override
	public String updateCustomerDetails(Integer id, Customer c) {
		
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
			
			log.info("Customer Details has been updated Successfully...! :- "+loanApplication.getApplicationId());
			return "Customer Details has been updated Successfully...!";
			
		}
		log.warn("Customer Details are not present for this Loan Application...!");
		return "Customer Details are not present for this Loan Application...!";
	}

	@Override
	public String updateGuarantorDetails(Integer id, LoanGuarantor lg) {
		
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
		log.info("Loan Guarantor Details has been updated Successfully...! :- "+loanApplication.getApplicationId());
		return "Loan Guarantor Details has been updated Successfully...!";
		
	}
	log.warn("Loan Guarantor Details are not present for this Loan Application...!");
	return "Loan Guarantor Details are not present for this Loan Application...!";

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
		log.info("Local Address is Updated...! :- "+loanApplication.getApplicationId());
		return "Data Updated Successfully";
	}	
		log.warn("Address not present for this id");	
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
			
			log.info("Medical Information is Updated..."+loanApplication.getApplicationId());
			return "Data updated Successfully";
			}
		log.warn("MedicalInfo not present for this id");
		return "MedicalInfo not present for this id";

	}



	@Override
	public String updateStatusToDocumentVerified(Integer id) {
		
	  LoanApplication byId = getById(id);
	  byId.setLoanApplicationStatus(LoanApplicationStatusEnum.DOCUMENTS_VERIFIED);
	  
	  appRepo.save(byId);
	  log.info("Documents has been verified for Loan Application id : "+id);	
		return "Documents has been verified for Loan Application id : "+id;
	}



	@Override
	public String updateStatusToDocumentRejected(Integer id) {
		
		  LoanApplication byId = getById(id);
		  byId.setLoanApplicationStatus(LoanApplicationStatusEnum.DOCUMENTS_REJECTED);
		  
		  appRepo.save(byId);
		  log.info("Documents has been Rejected for Loan Application id : "+id);	
			return "Documents has been Rejected for Loan Application id : "+id;
	}



	@Override
	public List<LoanApplication> getLoanStatusToForwardToOE() {

		return appRepo.findAllByLoanApplicationStatus(LoanApplicationStatusEnum.FORWARDED_TO_OE);
	}



	@Override
	public List<LoanApplication> getAllLoanApplications() {
		// TODO Auto-generated method stub
		
		
		return appRepo.findAll();
	}



	@Override
	public SanctionDetailDTO getSanctionById(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<LoanApplication> byId = appRepo.findById(id);
		
		if(byId.isPresent()) {
			
			LoanApplication loanApplication = byId.get();
			
			SanctionDetailDTO loanApplicationToSend = new SanctionDetailDTO();
			
			loanApplicationToSend.setLoanAmount(loanApplication.getLoanAmount());
			loanApplicationToSend.getCustomer().setCustomerName(loanApplication.getCustomer().getCustomerName());
			loanApplicationToSend.getCustomer().setCustomerEmailId(loanApplication.getCustomer().getCustomerEmailId());
			loanApplicationToSend.getCustomer().setCustomerContactNumber(loanApplication.getCustomer().getCustomerContactNumber());
			loanApplicationToSend.getCustomer().getLe().getCibil().setCibilScore(loanApplication.getCustomer().getLe().getCibil().getCibilScore());
			
			return loanApplicationToSend;
		}
		
		return null;
	}
	
	
	
	
	//DependentInfo Patch mapping
	//Previous Loan Details

}

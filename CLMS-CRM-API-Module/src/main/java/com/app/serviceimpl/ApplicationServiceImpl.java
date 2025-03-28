package com.app.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import com.app.entity.BankAccountDetails;
import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanApplication;
import com.app.entity.LoanGuarantor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;
import com.app.entity.CurrentLoanDetails;
import com.app.entity.CustomerAddress;
import com.app.entity.DependentInfo;
import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.entity.PreviousLoanDetails;
import com.app.repo.ApplicationRepository;
import com.app.service.ApplicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
	
	@Autowired
	JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;  


	@Override
	public String saveLoanApplication(Customer customer ,Cibil cibil,MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data){

		try {		
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			
			LoanApplication value = mapper.readValue(data, LoanApplication.class);
			
			System.out.println(data);
			System.out.println(value);
			
//			customer2.setAge(customer.getAge());
//			customer2.setState(customer.getState());
//			customer2.setCustomerPermanentAddress(customer.getCustomerPermanentAddress());
//			customer2.setCustomerCity(customer.getCustomerCity());
//			customer2.setCustomerPincode(customer.getCustomerPincode());
			
			Customer cc = new Customer();
			
			cc.setCustomerName(customer.getCustomerName());//loan
			cc.setDateOfBirth(customer.getDateOfBirth());//loan
			cc.setAge(value.getCustomer().getAge());
			cc.setGender(customer.getGender());//loan
			cc.setState(value.getCustomer().getState());
			cc.setCustomerContactNumber(customer.getCustomerContactNumber());//loan
			cc.setCustomerAlternateNumber(customer.getCustomerAlternateNumber());//loan
			cc.setCustomerEmailId(customer.getCustomerEmailId());//loan
			cc.setCustomerPermanentAddress(value.getCustomer().getCustomerPermanentAddress());
			cc.setCustomerCity(value.getCustomer().getCustomerCity());
			cc.setCustomerPincode(value.getCustomer().getCustomerPincode());
			cc.setAadharNo(customer.getAadharNo());//loan
			cc.setPanCardNo(customer.getPanCardNo());//loan
			
			value.setCustomer(cc);
			
			Cibil cb = new Cibil();
			cb.setCibilRemark(cibil.getCibilRemark());
			cb.setCibilScore(cibil.getCibilScore());
			cb.setCibilScoreDateTime(cibil.getCibilScoreDateTime());
			cb.setStatus(cibil.getStatus());
			
			value.setCibil(cb);
			
			PersonalDocuments docs = new PersonalDocuments();
			
			docs.setAadharCard(aadharCard.getBytes());
			docs.setPanCard(panCard.getBytes());
			docs.setIncomeTax(incomeTax.getBytes());
			docs.setAadharCard(aadharCard.getBytes());
			docs.setPhoto(photo.getBytes());
			docs.setSignature(signature.getBytes());
			docs.setBankCheque(bankCheque.getBytes());
			docs.setSalarySlips(salarySlips.getBytes());
				
			value.setDocuments(docs);
			
			/*
			value = mapper.readValue(data,LoanApplication.class);

			DependentInfo dependent = new DependentInfo();
			
			dependent.setNoOfFamilyMembers(value.getDependent().getNoOfFamilyMembers());
			dependent.setMaritalStatus(value.getDependent().getMaritalStatus());
			dependent.setNoOfChild(value.getDependent().getNoOfChild());
			dependent.setDependentMember(value.getDependent().getDependentMember());
			dependent.setFamilyIncome(value.getDependent().getFamilyIncome());
			app.setDependent(dependent);
			
			CustomerAddress address = new CustomerAddress();
			LocalAddress laddr = new LocalAddress();
			laddr.setAreaName(value.getAddress().getPaddr().getAreaName());
			laddr.setCityName(value.getAddress().getPaddr().getCityName());
			laddr.setDistrict(value.getAddress().getPaddr().getDistrict());
			laddr.setHouseNumber(value.getAddress().getPaddr().getHouseNumber());
			laddr.setPincode(value.getAddress().getPaddr().getPincode());
			laddr.setState(value.getAddress().getPaddr().getState());
			laddr.setStreetName(value.getAddress().getPaddr().getStreetName());
			
			PermanentAddress paddr = new PermanentAddress();
			paddr.setAreaName(value.getAddress().getLaddr().getAreaName());
			paddr.setCityName(value.getAddress().getLaddr().getCityName());
			paddr.setDistrict(value.getAddress().getLaddr().getDistrict());
			paddr.setHouseNumber(value.getAddress().getLaddr().getHouseNumber());
			paddr.setPincode(value.getAddress().getLaddr().getPincode());
			paddr.setState(value.getAddress().getLaddr().getState());
			paddr.setStreetName(value.getAddress().getLaddr().getStreetName());
			
			address.setPaddr(paddr);
			address.setLaddr(laddr);
			
			app.setAddress(address);
			
			MedicalInfo medicalInfo = new MedicalInfo();
			
			medicalInfo.setPatientId(value.getMedicalInfo().getPatientId());
			medicalInfo.setProfessionalPatientName(value.getMedicalInfo().getProfessionalPatientName());
			medicalInfo.setLoanAmount(value.getMedicalInfo().getLoanAmount());
			medicalInfo.setBillingDate(value.getMedicalInfo().getBillingDate());
			medicalInfo.setTreatment(value.getMedicalInfo().getTreatment());
			
			app.setMedicalInfo(medicalInfo);
			
			PreviousLoanDetails prevLoan = new PreviousLoanDetails();
			
			prevLoan.setBankAddress(value.getPreviousLoanDetails().getBankAddress());
			prevLoan.setBranchCode(value.getPreviousLoanDetails().getBranchCode());
			prevLoan.setBranchId(value.getPreviousLoanDetails().getBranchId());
			prevLoan.setBranchName(value.getPreviousLoanDetails().getBranchName());
			prevLoan.setBranchMICR(value.getPreviousLoanDetails().getBranchMICR());
			prevLoan.setBranchIFSC(value.getPreviousLoanDetails().getBranchIFSC());
			prevLoan.setBranchType(value.getPreviousLoanDetails().getBranchType());
			prevLoan.setContact(value.getPreviousLoanDetails().getContact());
			prevLoan.setEmail(value.getPreviousLoanDetails().getEmail());
			prevLoan.setStatus(value.getPreviousLoanDetails().getStatus());
			
			app.setPreviousLoanDetails(prevLoan);
			
			CurrentLoanDetails currentLaon = new CurrentLoanDetails();
			
			BankAccountDetails bankDetails= new BankAccountDetails();
			bankDetails.setAccountHolderName(value.getBankDetails().getAccountHolderName());
			bankDetails.setAccountNumber(value.getBankDetails().getAccountNumber());
			bankDetails.setAccountBalance(value.getBankDetails().getAccountBalance());
			bankDetails.setAccountStatus(value.getBankDetails().getAccountStatus());
			bankDetails.setAccountType(value.getBankDetails().getAccountType());
			
			app.setBankDetails(bankDetails);
			*/
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom(from);
			mail.setTo(customer.getCustomerEmailId());
			mail.setSubject("Loan Application");
	        mail.setText("Dear Customer, Your Car loan Application has been submitted successfully.\n"+value.getCustomer().getCustomerName());
			System.out.println(value);
			
	        appRepo.save(value);
	       
	        
	        sender.send(mail);
	        
			return "Loan Application Submitted successfully";
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Please Enter the details Correctly";
		
	}

	public LoanApplication getById(Integer id)
	{
		Optional<LoanApplication> byId = appRepo.findById(id);
		
		LoanApplication loanApplication = byId.get();
		
		return loanApplication;
	}
	

}

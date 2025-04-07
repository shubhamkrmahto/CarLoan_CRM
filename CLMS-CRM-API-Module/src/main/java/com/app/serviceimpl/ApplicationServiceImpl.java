package com.app.serviceimpl;

<<<<<<< HEAD
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
=======
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
import com.app.entity.PersonalDocuments;
import com.app.repository.ApplicationRepository;
import com.app.entity.Cibil;
import com.app.entity.Customer;
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import com.app.entity.BankAccountDetails;
import com.app.entity.CurrentLoanDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerAddress;
import com.app.entity.CustomerVerification;
import com.app.entity.DependentInfo;
import com.app.entity.LoanApplication;
import com.app.entity.LoanGuarantor;
import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.entity.PreviousLoanDetails;
import com.app.repo.ApplicationRepository;
=======
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git
import com.app.service.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;
<<<<<<< HEAD
	
=======

>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git
	@Autowired
	JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String from;  

<<<<<<< HEAD
=======
	
	@Override
	public String saveLoanApplication(LoanEnquiry loanEnquiry,MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,MultipartFile bankCheque, MultipartFile salarySlips,String data){
				

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
		
		LoanApplication loanApplication = byId.get();
		
		return loanApplication;
	}


>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git

	@Override
<<<<<<< HEAD
	public String saveLoanApplication(Customer customer ,MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature,
			MultipartFile bankCheque, MultipartFile salarySlips,String data) throws IOException,JsonMappingException, JsonProcessingException {
=======
	public Double getLoanAmount(Integer id) {
		// TODO Auto-generated method stub
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git
		
<<<<<<< HEAD
		LoanApplication app = new LoanApplication();
		
=======
		LoanApplication la = getById(id);
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git
		
<<<<<<< HEAD
		PersonalDocuments docs = new PersonalDocuments();
		docs.setAddressProof(addressProof.getBytes());
		docs.setPanCard(panCard.getBytes());
		docs.setIncomeTax(incomeTax.getBytes());
		docs.setAadharCard(aadharCard.getBytes());
		docs.setPhoto(photo.getBytes());
		docs.setSignature(signature.getBytes());
		docs.setBankCheque(bankCheque.getBytes());
		docs.setSalarySlips(salarySlips.getBytes());
			
		app.setDocuments(docs);
		
		ObjectMapper mapper = new ObjectMapper();
		LoanApplication value = mapper.readValue(data,LoanApplication.class);
		
		customer.setAge(value.getCustomer().getAge());
		customer.setDateOfBirth(value.getCustomer().getDateOfBirth());
		customer.setState(value.getCustomer().getState());
		customer.setGender(value.getCustomer().getGender());
		customer.setAadharNo(value.getCustomer().getAadharNo());
		customer.setPanCardNo(value.getCustomer().getPanCardNo());
		
		app.setCustomer(customer);
		
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
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(customer.getCustomerEmailId());
		mail.setSubject("Loan Application");
        mail.setText("Dear Customer, Your Car loan Application has been submitted successfully.");
		
        appRepo.save(app);
        
        
        sender.send(mail);
        
		return "Loan Application Submitted successfully";
=======
		return la.getLoanAmount();
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git
	}

<<<<<<< HEAD

	@Override
	public String updatePersonalDocm(Integer id, PersonalDocuments pdocuments) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String updatePermanentAddress(Integer id, PermanentAddress permanentAddress) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String updateBankDetails(Integer id, BankAccountDetails bad) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String updateCustomerVerification(Integer id, CustomerVerification cv) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String updateCustomerDetails(Integer id, Customer c) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String updateGuarantorDetails(Integer id, LoanGuarantor lg) {
		// TODO Auto-generated method stub
		return null;
	}

=======
>>>>>>> branch 'master' of https://github.com/shubhamkrmahto/CarLoan_CRM.git
	

}

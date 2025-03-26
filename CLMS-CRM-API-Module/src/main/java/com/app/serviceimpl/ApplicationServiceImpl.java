package com.app.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.BankAccountDetails;
import com.app.entity.CurrentLoanDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerAddress;
import com.app.entity.DependentInfo;
import com.app.entity.LoanApplication;
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
@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;

	@Override
	public void saveCustomer(Customer customer) {
		LoanApplication app = new LoanApplication();
		app.setCustomer(customer);
		appRepo.save(app);
		
	}

	@Override
	public void savePersonalDocuments(MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature, MultipartFile bankCheque, MultipartFile salarySlips) throws IOException {
		byte[] addr = addressProof.getBytes();
		byte[] pan = panCard.getBytes();
		byte[] income = incomeTax.getBytes();
		byte[] aadhar = aadharCard.getBytes();
		byte[] photo1 = photo.getBytes();
		byte[] sign = signature.getBytes();
		byte[] cheque = bankCheque.getBytes();
		byte[] slips = salarySlips.getBytes();
		
		PersonalDocuments docs = new PersonalDocuments();
		docs.setAddressProof(addr);
		docs.setPanCard(pan);
		docs.setIncomeTax(income);
		docs.setAadharCard(aadhar);
		docs.setPhoto(photo1);
		docs.setSignature(sign);
		docs.setBankCheque(cheque);
		docs.setSalarySlips(slips);
		
		LoanApplication app = new LoanApplication();
		app.setDocuments(docs);
		
		appRepo.save(app);
		
	}

	@Override
	public void saveData(String data) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		LoanApplication app = new LoanApplication();
		LoanApplication value = mapper.readValue(data,LoanApplication.class);
		
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
		
        appRepo.save(app)	;	
	}
	
	

}

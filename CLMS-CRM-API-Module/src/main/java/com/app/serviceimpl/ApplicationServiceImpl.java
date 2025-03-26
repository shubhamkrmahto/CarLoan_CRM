package com.app.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Customer;
import com.app.entity.LoanApplication;
import com.app.entity.PersonalDocuments;
import com.app.repo.ApplicationRepository;
import com.app.service.ApplicationService;
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
	
	

}

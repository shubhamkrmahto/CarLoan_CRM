package com.app.serviceimpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.LoanApplication;

import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.repo.ApplicationRepository;
import com.app.service.ApplicationService;
@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;

	
	

	
	@Override
	public PermanentAddress updatePermanentAddress(Integer id, PermanentAddress permanentAddress) {
		
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
		
		return null;
		}
		return null;
	}





	@Override
	public PersonalDocuments updatePersonalDocm(Integer id, PersonalDocuments pdocuments) {

	
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
		
		
		return null;
		}
		return null;
	}
	
	
	
	
	
	

}

package com.app.serviceimpl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.LoanApplication;
import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;
import com.app.repo.ApplicationRepository;
import com.app.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;

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
			return "Data updated Successfully";
			}
		
		return "MedicalInfo not present for this id";

	}

	}


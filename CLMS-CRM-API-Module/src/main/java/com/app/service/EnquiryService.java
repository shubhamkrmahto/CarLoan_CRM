package com.app.service;

import java.time.LocalDate;
import java.util.List;
import com.app.entity.LoanEnquiry;
import com.app.enums.EnquiryStatusEnum;

public interface EnquiryService {

	public void deleteEnquiryField(Integer id);
	
	public String saveEnquiry(LoanEnquiry enquiry);
	
	public String updateFullName(Integer id, String cname);
	
	public String updateDateOfBirth(Integer id, LocalDate dob);

	public String updateGender(Integer id, String gender);
	
	public String updateEmail(Integer id, String email);
	
	public String updateContact(Integer id, Long contact);

	public String updateAlternate(Integer id, Long alternate);

	public String updateAadharNo(Integer id, Long aadharNo);
	
	public String updatePanNo(Integer id, String panNo);
	
	public String updateEnquiry(Integer enquiryId , LoanEnquiry loanEnquiry);

	public List<LoanEnquiry> getAllEnquiry();

	public LoanEnquiry getSingleEnquiry(Integer enquiryId);

	public String setenquiryStatus(Integer id);

	public List<LoanEnquiry> getEnquiryForwardToOe();
	
	public EnquiryStatusEnum updateCibilScore(Integer enqId,Integer cs);
}

package com.app.service;

import java.util.List;

import com.app.entity.LoanEnquiry;
import com.app.enums.EnquiryStatusEnum;
import com.app.exception.EnquiryNotFoundException;

public interface EnquiryService {

	
	
	public String saveEnquiry(LoanEnquiry enquiry);
	
	public List<LoanEnquiry> getAllEnquiry();

	public LoanEnquiry getSingleEnquiry(Integer enquiryId) throws EnquiryNotFoundException;

	public String setenquiryStatus(Integer id);

	public List<LoanEnquiry> getEnquiryForwardToOe();
	
	public List<LoanEnquiry> getApprovedEnquiry();
	
	public EnquiryStatusEnum updateCibilScore(Integer enqId,Integer cs) throws EnquiryNotFoundException;
	

}

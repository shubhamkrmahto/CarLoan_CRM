 package com.app.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.entity.LoanEnquiry;
import com.app.enums.CibilStatusEnum;
import com.app.enums.EnquiryStatusEnum;
import com.app.exception.EnquiryNotFoundException;
import com.app.repository.EnquiryRepository;
import com.app.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService{


	private static final Logger log= LoggerFactory.getLogger(EnquiryServiceImpl.class);

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;  
	

	@Override
	public String saveEnquiry(LoanEnquiry enquiry) {
		
		enquiry.setEnquiryStatus(EnquiryStatusEnum.PENDING);
		enquiry.getCibil().setCibilScore(0);
		enquiry.getCibil().setCibilStatus(CibilStatusEnum.PENDING);
		
		enquiryRepository.save(enquiry);
		
		log.info("Enquiry has been saved successfully and Enquiry id is : " + enquiry.getEnquiryId());
		
		SimpleMailMessage mail= new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(enquiry.getCustomerEmailId());
		mail.setSubject("Regarding Loan Enquiry");
		mail.setText("Your Loan enquiry request has been Registed.Our Customer executive will reach you out soon. Stay connected ");
		
		mailSender.send(mail);
		
		log.info("Mail has been sent to registered Email id : " + enquiry.getCustomerEmailId());
		return "Enquiry registered";
				
	}


	
	@Override
	public LoanEnquiry getSingleEnquiry(Integer id) throws EnquiryNotFoundException {
		Optional<LoanEnquiry> byId = enquiryRepository.findById(id);	
		if (byId.isPresent()) {
			log.info(" Get single enquiry successfully for enquiry id = " + id);
			return byId.get();
		}else {
			log.info("Enquiry Details Not Found for Inquiry Id : "+id);
			throw new EnquiryNotFoundException("Enquiry Details Not Found for Inquiry Id : "+id +". Please enter correct enquiry Id");
			
		}
		
		
	}
	
	

	@Override
	public List<LoanEnquiry> getAllEnquiry() {
		
		log.info(" Get all Enquirys successfully ");
		
		return	enquiryRepository.findAll();

	}


	@Override
	public String setenquiryStatus(Integer id) {
		Optional<LoanEnquiry> byId = enquiryRepository.findById(id);
		if (byId.isPresent()) {
			LoanEnquiry loanEnquiry = byId.get();
			loanEnquiry.setEnquiryStatus(EnquiryStatusEnum.FORWARD_TO_OE);	
			enquiryRepository.save(loanEnquiry);
		}
		return "status changed to FORWARD_To_OE";
	}


	@Override
	public List<LoanEnquiry> getEnquiryForwardToOe() {
	List<LoanEnquiry> list = enquiryRepository.findByEnquiryStatus(EnquiryStatusEnum.FORWARD_TO_OE);
		return list;
	}
	
	@Override
	public List<LoanEnquiry> getApprovedEnquiry() {
		List<LoanEnquiry> list = enquiryRepository.findByEnquiryStatus(EnquiryStatusEnum.APPROVED_FOR_LOAN_APPLICATION);
		return list;
	}


	@Override
	public EnquiryStatusEnum updateCibilScore(Integer enqId, Integer cs) throws EnquiryNotFoundException {
		
		 LoanEnquiry le = getSingleEnquiry(enqId);
		
		le.getCibil().setCibilScore(cs);
		
		if(cs>650) {
			le.getCibil().setCibilStatus(CibilStatusEnum.GOOD);
 			le.setEnquiryStatus(EnquiryStatusEnum.APPROVED_FOR_LOAN_APPLICATION);

 			SimpleMailMessage mail = new SimpleMailMessage();
 			mail.setFrom(from);
 			mail.setTo(le.getCustomerEmailId());
 			mail.setSubject("CLMS Enquiry Status");
 			mail.setText("Dear Customer    \n Your Cibil Score is :"+le.getCibil().getCibilScore()+" : "+cs+ "\n Your Status has been"+le.getCibil().getCibilStatus());
 			mailSender.send(mail);
 		}
 		else {
 			le.getCibil().setCibilStatus(CibilStatusEnum.POOR);
 			le.setEnquiryStatus(EnquiryStatusEnum.REJECTED);
 			SimpleMailMessage mail = new SimpleMailMessage();
 			mail.setFrom(from);
 			mail.setTo(le.getCustomerEmailId());
 			mail.setSubject("CLMS Enquiry Status");
 			mail.setText("Dear Customer    \n Your Cibil Score is :"+le.getCibil().getCibilScore()+" : "+cs+ "\n Your Status has been"+le.getCibil().getCibilStatus());
 			mailSender.send(mail);
 		}
		
		enquiryRepository.save(le);
		
		return le.getEnquiryStatus();
	}
	
	
	

}
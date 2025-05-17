package com.app.serviceimpl;


import java.time.LocalDate;
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
	public void deleteEnquiryField(Integer id) { 
		
	
		enquiryRepository.deleteById(id);	
		
		log.info("Enquiry has been Deleted for Enquiry id : " + id);
	}
	

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
	public String updateFullName(Integer id, String cname) {
		// TODO Auto-generated method stub
		
		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		if(CById.isPresent()) {
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setCustomerName(cname);
		
		enquiryRepository.save(loanEnquiry);


		log.info("Enquiry Name has been updated successfully , for this id = " + id);
		return "Customer Name has been updated Successfully.";
		}
		return "No valid result found for this id.";
	}

	
	@Override
	public String updateDateOfBirth(Integer id, LocalDate dob) {
		// TODO Auto-generated method stub
		
		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		if(CById.isPresent()) {
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setDateOfBirth(dob);
		
		enquiryRepository.save(loanEnquiry);
		
		return "Customer Date of Birth has been updated Successfully.";
		}
		return "No valid result found for this id.";
	}
	
	@Override
	public String updateGender(Integer id, String gender) {
		// TODO Auto-generated method stub

		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		if(CById.isPresent()) {
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setGender(gender);
		
		enquiryRepository.save(loanEnquiry);
		
		return "Customer Gender has been updated Successfully.";
		}
		return "No valid result found for this id.";
		
	}
	
	@Override
	public String updateEmail(Integer id, String email) {
		// TODO Auto-generated method stub

		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		
		if(CById.isPresent()) {
		
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setCustomerEmailId(email);
		
		enquiryRepository.save(loanEnquiry);
		
		log.info("Enquiry Email has been updated successfully , for this id = " + id);
		
		return "Customer Email has been updated Successfully.";
		}
		return "No valid result found for this id.";
	}

	@Override
	public String updateContact(Integer id, Long contact) {
		// TODO Auto-generated method stub

		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		
		if(CById.isPresent()) {
		
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setCustomerContactNumber(contact);
		
		
		enquiryRepository.save(loanEnquiry);
		
		log.info("Enquiry Contact has been updated successfully , for this id = " + id);
		
		return "Customer Contact Number has been updated Successfully.";
		
		}
		return "No valid result found for this id.";
	}


	@Override
	public String updateAlternate(Integer id, Long alternate) {
		// TODO Auto-generated method stub

		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		
		if(CById.isPresent()) {
		
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setCustomerAlternateNumber(alternate);
		
		enquiryRepository.save(loanEnquiry);
		
		log.info("Enquiry AlterNate contact number has been updated successfully , for this id = " + id);
		
		return "Customer Alternate Contact has been updated Successfully.";
	}
		return "No valid result found for this id.";
	}

	
	@Override
	public String updateAadharNo(Integer id, Long aadharNo) {
		// TODO Auto-generated method stub

		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		
		if(CById.isPresent()) {
		
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setAadharNo(aadharNo);
		
		enquiryRepository.save(loanEnquiry);
		
		return "Customer Aadhar No has been updated Successfully.";
		}
		return "No valid result found for this id.";
	}


	@Override
	public String updatePanNo(Integer id, String panNo) {
		// TODO Auto-generated method stub

		Optional<LoanEnquiry> CById = enquiryRepository.findById(id);
		
		if(CById.isPresent()) {
		
		LoanEnquiry loanEnquiry = CById.get();
		
		loanEnquiry.setPanCardNo(panNo);
		
		enquiryRepository.save(loanEnquiry);
		
		return "Customer PanCard No No has been updated Successfully.";
		}
		return "No valid result found for this id.";
	}

	public String updateEnquiry(Integer enquiryId, LoanEnquiry loanEnquiry) {
		
		Optional<LoanEnquiry> loan = enquiryRepository.findById(enquiryId);
		
		LoanEnquiry loanData = loan.get();
		
		if(loan.isPresent())
		{
			
			loanData.setCustomerName(loanEnquiry.getCustomerName());
			loanData.setDateOfBirth(loanEnquiry.getDateOfBirth());
			loanData.setGender(loanEnquiry.getGender());
			loanData.setCustomerEmailId(loanEnquiry.getCustomerEmailId());
			loanData.setCustomerContactNumber(loanEnquiry.getCustomerContactNumber());
			loanData.setCustomerAlternateNumber(loanEnquiry.getCustomerAlternateNumber());
			loanData.setAadharNo(loanEnquiry.getAadharNo());
			loanData.setPanCardNo(loanEnquiry.getPanCardNo());
			
			enquiryRepository.save(loanData);

			log.info("Loan Enquiry has been updated successfully for enquiry id = " + enquiryId);
			return "Enquiry updated successfull...!";
		}
		
		return "invalid Id";
		
	}

	@Override
	public LoanEnquiry getSingleEnquiry(Integer id) {
		Optional<LoanEnquiry> byId = enquiryRepository.findById(id);	
		
		log.info(" GET SINGLE DATA successfully for enquiry id = " + id);
		return byId.get();
	}

	@Override
	public List<LoanEnquiry> getAllEnquiry() {
		
		log.info(" GET All DATA successfully ");
		
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
	public EnquiryStatusEnum updateCibilScore(Integer enqId, Integer cs) {
		// TODO Auto-generated method stub
		
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
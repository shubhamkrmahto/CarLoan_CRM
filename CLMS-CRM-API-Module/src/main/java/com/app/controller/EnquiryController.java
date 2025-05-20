package com.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.app.entity.LoanEnquiry;
import com.app.enums.EnquiryStatusEnum;
import com.app.exception.EnquiryNotFoundException;
import com.app.service.EnquiryService;

@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

	private static final Logger log = LoggerFactory.getLogger(EnquiryController.class);

	@Autowired
	RestTemplate rt;

	@Autowired
	private EnquiryService enquiryService;

	// POST MAPPING

	@GetMapping("/test")
	public String testApiGateway() {
		return "API GateWay Testing";
	}

	@PostMapping("/saveEnquiry")
	public ResponseEntity<String> saveEnquiry(@RequestBody LoanEnquiry enquiry) {

		log.info("Enquiry Controller post mapping called...!");
		System.out.println(enquiry);

		String msg = enquiryService.saveEnquiry(enquiry);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	// method to change enquiry status from crm

	@PatchMapping("/enquirystatus/{id}")
	public ResponseEntity<String> sendEnquiryStatusToOE(@PathVariable Integer id) {

		String status = enquiryService.setenquiryStatus(id);
		return new ResponseEntity<String>(status, HttpStatus.OK);

	}

	// method to get all enquries having status sentToOE

	@GetMapping("/getEnquiryForward_To_Oe")
	public ResponseEntity<List<LoanEnquiry>> getEnquirySentToOE() {
		List<LoanEnquiry> list = enquiryService.getEnquiryForwardToOe();
		System.out.println(list);
		return new ResponseEntity<List<LoanEnquiry>>(list, HttpStatus.OK);
	}

	@GetMapping("/getApprovedEnquiry")
	public ResponseEntity<List<LoanEnquiry>> getApprovedEnquiry() {
		List<LoanEnquiry> list = enquiryService.getApprovedEnquiry();
		System.out.println(list);
		return new ResponseEntity<List<LoanEnquiry>>(list, HttpStatus.OK);
	}

	// GET MAPPING

	@GetMapping("/getSingleEnquiry/{enquiryId}")
	public ResponseEntity<LoanEnquiry> getSingleEnquiry(@PathVariable("enquiryId") Integer enquiryId) throws EnquiryNotFoundException {
		log.info("Customer GETSINGLE METHOD called");

		LoanEnquiry loanEnquiry = enquiryService.getSingleEnquiry(enquiryId);
		return new ResponseEntity<LoanEnquiry>(loanEnquiry, HttpStatus.OK);
	}

	@GetMapping("/getAllEnquiry")
	public ResponseEntity<List<LoanEnquiry>> getAllEnquiry() {
		log.info("Customer GET METHOD called");
		List<LoanEnquiry> loanEnquiry = enquiryService.getAllEnquiry();

		return new ResponseEntity<List<LoanEnquiry>>(loanEnquiry, HttpStatus.OK);
	}

	
	
	
	// CIBIL METHODS BELOW THIS COMMENT

	@GetMapping("/updateStatus/{enqid}/{cibil}")
	public ResponseEntity<String> updateStatus(@PathVariable("enqid") Integer eid,
			@PathVariable("cibil") Integer cibil) throws EnquiryNotFoundException {

		EnquiryStatusEnum status = enquiryService.updateCibilScore(eid, cibil);

		return new ResponseEntity<String>("Cibil has been updated to" + status, HttpStatus.ACCEPTED);

	}
	

	
}

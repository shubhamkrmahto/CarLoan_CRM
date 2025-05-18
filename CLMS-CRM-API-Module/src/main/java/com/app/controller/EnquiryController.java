package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.entity.LoanEnquiry;
import com.app.enums.EnquiryStatusEnum;
import com.app.service.EnquiryService;

@CrossOrigin("*")
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
	public ResponseEntity<LoanEnquiry> getSingleEnquiry(@PathVariable("enquiryId") Integer enquiryId) {
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

	// PUT MAPPING

	@PutMapping("/updateEnquiry/{enquiryId}")
	public ResponseEntity<String> updateEnquiry(@PathVariable("enquiryId") int enquiryId,
			@RequestBody LoanEnquiry loanEnquiry) {
		log.info("put mapping called");

		String msg = enquiryService.updateEnquiry(enquiryId, loanEnquiry);

		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);

	}

	// PATCH MAPPING

	@PatchMapping("/updateCustomerName/{id}/{cname}")
	public ResponseEntity<String> updateCustomerName(@PathVariable("id") Integer id,
			@PathVariable("cname") String cname) {

		log.info("customer name patch method mapping called...!");
		String msg = enquiryService.updateFullName(id, cname);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PatchMapping("/updateCustomerDOB/{id}/{dob}")
	public ResponseEntity<String> updateCustomerName(@PathVariable("id") Integer id,
			@PathVariable("dob") LocalDate dob) {

		log.info("customer DOB patch method mapping called...!");
		String msg = enquiryService.updateDateOfBirth(id, dob);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PatchMapping("/updateCustomerGender/{id}/{gender}")
	public ResponseEntity<String> updateCustomerGender(@PathVariable("id") Integer id,
			@PathVariable("gender") String gender) {

		log.info("customer Gender patch method mapping called...!");
		String msg = enquiryService.updateGender(id, gender);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PatchMapping("/updateEmail/{id}/{email}")
	public ResponseEntity<String> updateCustomerEmail(@PathVariable("id") Integer id,
			@PathVariable("email") String email) {
		log.info(" Customer Email PATCH method mapping called...!");

		String msg = enquiryService.updateEmail(id, email);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PatchMapping("/updateContact/{id}/{contact}")
	public ResponseEntity<String> updateCustomerContact(@PathVariable("id") Integer id,
			@PathVariable("contact") long con) {
		log.info("customer contact PATCH method mapping called...!");

		String msg = enquiryService.updateContact(id, con);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PatchMapping("/updateAlternateContact/{id}/{alter}")
	public ResponseEntity<String> updateCustomerAlternate(@PathVariable("id") Integer id,
			@PathVariable("alter") long alter) {
		log.info(" Customer Alternate Contact PATCH method mapping called...!");

		String msg = enquiryService.updateAlternate(id, alter);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PatchMapping("/updateAadhaarNo/{id}/{aadharNo}")
	public ResponseEntity<String> updateAadhaarNo(@PathVariable("id") Integer id,
			@PathVariable("aadharNo") long aadharNo) {
		log.info("Customer AadharNo PATCH method mapping called...!");

		String msg = enquiryService.updateAadharNo(id, aadharNo);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PatchMapping("/updatePanNo/{id}/{panNo}")
	public ResponseEntity<String> updatePanNo(@PathVariable("id") Integer id, @PathVariable("panNo") String panNo) {
		log.info(" Customer PanNo PATCH method mapping called...!");

		String msg = enquiryService.updatePanNo(id, panNo);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/updateEnquiryStatus/{id}")
	public ResponseEntity<String> updateEnquiryStatus(@PathVariable("id") Integer id) {
		log.info(" Customer PanNo PATCH method mapping called...!");

		String msg = enquiryService.setenquiryStatus(id);

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	// DELETE MAPPING

	@DeleteMapping("/deleteEnquiryField/{id}")
	public ResponseEntity<String> deleteEnquiryField(@PathVariable("id") Integer id) {
		log.info("Enquiry DELETE method called...!");
		enquiryService.deleteEnquiryField(id);
		return new ResponseEntity<String>("Delete Your Enquiry Field Successfully...!", HttpStatus.OK);

	}

	// CIBIL METHODS BELOW THIS COMMENT

	@GetMapping("/updateStatus/{enqid}/{cibil}")
	public ResponseEntity<String> updateStatus(@PathVariable("enqid") Integer eid,
			@PathVariable("cibil") Integer cibil) {

		EnquiryStatusEnum status = enquiryService.updateCibilScore(eid, cibil);

		return new ResponseEntity<String>("Cibil has been updated to" + status, HttpStatus.ACCEPTED);

	}
}

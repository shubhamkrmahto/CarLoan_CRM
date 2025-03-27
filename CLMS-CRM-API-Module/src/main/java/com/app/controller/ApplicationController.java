package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.entity.BankAccountDetails;
import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanGuarantor;
import com.app.service.ApplicationService;

@RestController
public class ApplicationController {
	
	@Autowired
	ApplicationService appService;
	
	@Autowired
	RestTemplate rt;
	
	@GetMapping("/getcustomer/{id}")
	public ResponseEntity<Customer> getCustomerInfo(@PathVariable("id") Integer id){
		
		String url ="http://localhost:7000/enquiry/getSingleEnquiry/"+id;
		Customer customer = rt.getForObject(url, Customer.class);
		System.out.println(customer);
		return new ResponseEntity<Customer>(customer , HttpStatus.OK);
	}
	
	@GetMapping("/getCibil/{id}")
	public ResponseEntity<Cibil> getCibilInfo(@PathVariable("id") Integer id){
		String url ="http://localhost:9001/cibil/getCibilSingleData/"+id;
		
		Cibil cibil = rt.getForObject(url, Cibil.class);
		System.out.println(cibil);
		return new ResponseEntity<Cibil>(cibil , HttpStatus.OK);
		
	}
	
	@PutMapping("/updateBankDetails/{id}")
	public ResponseEntity<String> updateBankDetails(@PathVariable("id") Integer id, @RequestBody BankAccountDetails bad)
	{
		
		String msg = appService.updateBankDetails(id,bad);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/updateCustVerification/{id}")
	public ResponseEntity<String> updateCustomerVerification(@PathVariable("id") Integer id, @RequestBody CustomerVerification cv)
	{
		
		String msg = appService.updateCustomerVerification(id,cv);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/updateLoanGuarantor/{id}")
	public ResponseEntity<String> updateLoanGuarantor(@PathVariable("id") Integer id, @RequestBody LoanGuarantor lg)
	{
		
		String msg = appService.updateGuarantorDetails(id,lg);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	

}

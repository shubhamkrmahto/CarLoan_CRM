package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
import com.app.service.ApplicationService;

@RestController
@RequestMapping("/loanApplication")
public class ApplicationController {
	
	@Autowired
	ApplicationService appService;
	
	@Autowired
	RestTemplate rt;
	
	@PostMapping("/saveapplication/{id}")
	public ResponseEntity<String> getCustomerInfo(@PathVariable("id") Integer id, 
			@RequestPart("addressProof") MultipartFile addressProof  ,
	        @RequestPart("panCard") MultipartFile panCard ,
	        @RequestPart("incomeTax") MultipartFile incomeTax,
	        @RequestPart("aadharCard") MultipartFile aadharCard,
	        @RequestPart("photo") MultipartFile photo,
	        @RequestPart("signature") MultipartFile signature,
	        @RequestPart("bankCheque") MultipartFile bankCheque,
	        @RequestPart("salarySlips") MultipartFile salarySlips,
	        
			@RequestPart("data") String data
			){
		
		String url ="http://localhost:7002/enquiry/getSingleEnquiry/"+id;
		LoanEnquiry enquiry = rt.getForObject(url, LoanEnquiry.class);
	
		System.out.println(enquiry);
		
			String msg = appService.saveLoanApplication(enquiry,addressProof,panCard,incomeTax,aadharCard,
					                        photo,signature,bankCheque,salarySlips,data);
			
			return new ResponseEntity<String>(msg , HttpStatus.OK);
			}

	@GetMapping("/getLoanApplicationDetailById/{id}")
	public ResponseEntity<LoanApplication> getById(@PathVariable("id") Integer id)
	{
		
		return new ResponseEntity<LoanApplication>(appService.getById(id), HttpStatus.OK);
	}
	
	@GetMapping("/getLoanamount/{id}")
	public ResponseEntity<Double> getLoanAmount(@PathVariable("id") Integer id)
	{
		
		return new ResponseEntity<Double>(appService.getLoanAmount(id), HttpStatus.OK);
	}
	

	
	
	
}
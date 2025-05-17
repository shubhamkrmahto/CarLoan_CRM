package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.app.entity.LoanApplication;
import com.app.entity.LoanEnquiry;
import com.app.service.ApplicationService;

@CrossOrigin
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
	
	@GetMapping("/updateStatusToDocumentVerified/{id}")
	public ResponseEntity<String> updateStatusToDocumentVerified(@PathVariable("id") Integer id)
	{
		
		return new ResponseEntity<String>(appService.updateStatusToDocumentVerified(id), HttpStatus.OK);
	}
	
	@GetMapping("/updateStatusToDocumentRejected/{id}")
	public ResponseEntity<String> updateStatusToDocumentRejected(@PathVariable("id") Integer id)
	{
		
		return new ResponseEntity<String>(appService.updateStatusToDocumentRejected(id), HttpStatus.OK);
	}
	
	@GetMapping("/getAllByForwardToOE/{id}")
	public ResponseEntity<List<LoanApplication>> getAllByForwardToOE()
	{
		
		return new ResponseEntity<List<LoanApplication>>(appService.getLoanStatusToForwardToOE(), HttpStatus.OK);
	}
	
	@GetMapping("/getAllApplicationID")
	public ResponseEntity<List<Integer>> getAllApplicationID()
	{
		
		return new ResponseEntity<List<Integer>>(appService.getLoanApplicationsID(), HttpStatus.OK);
	}
	
}
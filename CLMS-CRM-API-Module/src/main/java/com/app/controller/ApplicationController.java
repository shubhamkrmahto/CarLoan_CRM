package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		String url ="http://localhost:7000/enquiry/getSingleEnquiry/"+id;
		Customer customer = rt.getForObject(url, Customer.class);
	
		System.out.println(customer);
		
		String url1 ="http://localhost:9001/cibil/getCibilSingleData/"+id;
		Cibil cibil = rt.getForObject(url1, Cibil.class);
		
		System.out.println(cibil);
		
			String msg = appService.saveLoanApplication(customer,cibil,addressProof,panCard,incomeTax,aadharCard,
					                        photo,signature,bankCheque,salarySlips,data);
			
			return new ResponseEntity<String>(msg , HttpStatus.OK);
			}

	@GetMapping("/getLoanApplicationDetailById/{id}")
	public ResponseEntity<LoanApplication> getById(@PathVariable("id") Integer id)
	{
		
		return new ResponseEntity<LoanApplication>(appService.getById(id), HttpStatus.OK);
	}
	
}
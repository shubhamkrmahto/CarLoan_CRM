package com.app.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.app.entity.BankAccountDetails;
import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.PermanentAddress;
import com.app.entity.PersonalDocuments;
import com.app.enums.PersonalDocumentStatusEnum;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanApplication;
import com.app.entity.LoanGuarantor;
import com.app.service.ApplicationService;

@RestController
public class ApplicationController {
	
	@Autowired
	ApplicationService  appService;
	
	@Autowired
	RestTemplate rt;
	
	@PostMapping("/saveapplication/{id}")
	public ResponseEntity<String> getCustomerInfo( @PathVariable Integer id, 
			@RequestPart("addressProof") MultipartFile addressProof  ,
	        @RequestPart("panCard") MultipartFile panCard ,
	        @RequestPart("incomeTax") MultipartFile incomeTax,
	        @RequestPart("aadharCard") MultipartFile aadharCard,
	        @RequestPart("photo") MultipartFile photo,
	        @RequestPart("signature") MultipartFile signature,
	        @RequestPart("bankCheque") MultipartFile bankCheque,
	        @RequestPart("salarySlips") MultipartFile salarySlips,
	        
			@RequestPart("data") String data
			) throws IOException{
		
		String url ="http://localhost:7000/enquiry/getSingleEnquiry/"+id;
		Customer customer = rt.getForObject(url, Customer.class);
	
		System.out.println(customer);
		
		String msg =  appService.saveLoanApplication(customer,addressProof,panCard,incomeTax,aadharCard,
				                        photo,signature,bankCheque,salarySlips,data);
		return new ResponseEntity<String>(msg , HttpStatus.OK);
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
		
		String msg =  appService.updateBankDetails(id,bad);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/updateCustVerification/{id}")
	public ResponseEntity<String> updateCustomerVerification(@PathVariable("id") Integer id, @RequestBody CustomerVerification cv)
	{
		
		String msg =  appService.updateCustomerVerification(id,cv);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/updateLoanGuarantor/{id}")
	public ResponseEntity<String> updateLoanGuarantor(@PathVariable("id") Integer id, @RequestBody LoanGuarantor lg)
	{
		
		String msg =  appService.updateGuarantorDetails(id,lg);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
	

	@PutMapping("/updatePermanentAddress/{id}")
	public ResponseEntity<String> chengeAllDataforPermanentAddress(@PathVariable("id")Integer id,
			                                                                        @RequestBody PermanentAddress permanentAddress)
	{
	String address= appService.updatePermanentAddress(id,permanentAddress);
	
	return new ResponseEntity<String>(address,HttpStatus.OK);
  }
	
	
	@PutMapping("/updatePersonalDocument/{id}")
	public ResponseEntity<String> updatePersonalDoc(@PathVariable("id")Integer id,
			                          @RequestBody PersonalDocuments pdocuments)
	{
		String personalDocuments= appService.updatePersonalDocm(id,pdocuments);
		return new ResponseEntity<String>(personalDocuments,HttpStatus.OK);
	}
	
	@PatchMapping("StatusUpdate/{id}/{status}")
	public ResponseEntity<String> statusUpdate(@PathVariable("id")Integer id,
			                                               @PathVariable("status")PersonalDocumentStatusEnum status)
	{	                                             
	  appService.statusUpdates(id,status);
	return new ResponseEntity<String>("Document Send to CM",HttpStatus.OK);
	}
	
     @GetMapping("/getloanappssenttooe")
	 public ResponseEntity <List<LoanApplication>> getPresonalDocument()
	 {
		 List<LoanApplication> loanApps=appService.getLoanAppsSentToOE();
		
		 return new ResponseEntity<List<LoanApplication>>(loanApps,HttpStatus.OK);
	 }
			 
			 
			
	 
	
	
}
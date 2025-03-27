package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.entity.Cibil;
import com.app.entity.Customer;
import com.app.entity.LocalAddress;
import com.app.entity.MedicalInfo;
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
	
	@PutMapping("/updateLocalAddress/{id}")
	public ResponseEntity<String> updateLocalAddressDetails(@PathVariable("id") Integer id,@RequestBody LocalAddress local)
	{
		
		String msg=appService.updateLocalAddress(local,id);
		  return new ResponseEntity<String>(msg,HttpStatus.OK);
		
	}

	@PutMapping("/updateMedicalInfoDocs/{id}")
	public ResponseEntity<String> updateMedicalInfoDocs(@PathVariable("id")Integer id,
			                          @RequestBody MedicalInfo medicalInfor)
	{
		String medicalI=appService.updateMedicalInfoDocs(id,medicalInfor);
		return new ResponseEntity<String>(medicalI,HttpStatus.OK);
	}

	
	
	
	


	
	

}

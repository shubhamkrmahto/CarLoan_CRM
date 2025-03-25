package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.entity.Customer;
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

}

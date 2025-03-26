package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ApplicationService {

	void saveCustomer(Customer customer);

	void savePersonalDocuments(MultipartFile addressProof, MultipartFile panCard, MultipartFile incomeTax,
			MultipartFile aadharCard, MultipartFile photo,MultipartFile signature, MultipartFile bankCheque, MultipartFile salarySlips) throws IOException;

	void saveData(String data) throws JsonMappingException, JsonProcessingException;

}

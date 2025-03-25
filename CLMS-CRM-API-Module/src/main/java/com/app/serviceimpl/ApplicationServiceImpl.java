package com.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repo.ApplicationRepository;
import com.app.service.ApplicationService;
@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;
	
	

}

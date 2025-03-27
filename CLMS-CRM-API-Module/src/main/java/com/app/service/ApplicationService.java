package com.app.service;

import com.app.entity.BankAccountDetails;
import com.app.entity.Customer;
import com.app.entity.CustomerVerification;
import com.app.entity.LoanGuarantor;

public interface ApplicationService {

	public String updateBankDetails(Integer id, BankAccountDetails bad);
	
	public String updateCustomerVerification(Integer id, CustomerVerification cv);
	
	public String updateCustomerDetails(Integer id, Customer c);

	public String updateGuarantorDetails(Integer id, LoanGuarantor lg);

}

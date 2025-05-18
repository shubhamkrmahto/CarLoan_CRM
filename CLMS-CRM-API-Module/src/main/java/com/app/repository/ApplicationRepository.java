package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.LoanApplication;
import com.app.enums.LoanApplicationStatusEnum;

public interface ApplicationRepository extends JpaRepository<LoanApplication, Integer> {

	public List<LoanApplication> findAllByLoanApplicationStatus(LoanApplicationStatusEnum loanStatus); 
	
	
}

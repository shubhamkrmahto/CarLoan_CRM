package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.LoanApplication;

public interface ApplicationRepository extends JpaRepository<LoanApplication, Integer> {

}

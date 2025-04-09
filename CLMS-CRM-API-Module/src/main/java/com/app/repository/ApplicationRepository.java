package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.LoanApplication;

public interface ApplicationRepository extends JpaRepository<LoanApplication, Integer> {

}

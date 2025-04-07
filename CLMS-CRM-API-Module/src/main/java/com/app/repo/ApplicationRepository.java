package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.LoanApplication;
import com.app.enums.PersonalDocumentStatusEnum;

public interface ApplicationRepository extends JpaRepository<LoanApplication, Integer> {


}

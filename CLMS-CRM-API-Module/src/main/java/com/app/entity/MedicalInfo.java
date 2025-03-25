package com.app.entity;

import java.time.LocalDateTime;

import com.app.enums.CibilStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billId;
	private Integer patientId;
    private String professionalPatientName;
	private String billingDate;
	private Double loanAmount;
	private String treatment;
	
	
}

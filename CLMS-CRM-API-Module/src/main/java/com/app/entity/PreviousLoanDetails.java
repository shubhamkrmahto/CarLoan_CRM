package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PreviousLoanDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer previousLoanId;
	private Integer branchId;	
	private String branchName;
	private Double branchCode;
	private String branchType;
	private String branchIFSC;
	private String branchMICR;
	private Long contact;
	private String bankAddress;
	private String email;
	private String Status;
	
	

}

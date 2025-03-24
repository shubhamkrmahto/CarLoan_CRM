package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class DependentInfo {
	@Id
	@GeneratedValue
	private Integer dependentInfoId;
	private Integer noOfFamilyMembers;
	private Integer noOfChild;
	private String maritalStatus;
	private String dependentMember;
	private Double familyIncome;
	

}

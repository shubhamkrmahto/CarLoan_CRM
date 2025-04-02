package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dependentInfoId;
	private Integer noOfFamilyMembers;
	private Integer noOfChild;
	private String maritalStatus;
	private String dependentMember;
	private Double familyIncome;
	

}
//dependent :{
//noOfFamilyMembers:4,
//noOfChild:2,
//maritalStatus:"married",
//dependentMember:"person",
//familyIncome:50000
//}

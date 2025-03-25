package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonalDocuments {
	@Id
	@GeneratedValue
	private Integer documentId;
	private byte[] addressProof;
	private byte[] panCard;
	private byte[] incomeTax;
	private byte[] aadharCard;
	private byte[] photo;
	private byte[] signature;
	private byte[] bankCheque;
	private byte[] salarySlips;

}

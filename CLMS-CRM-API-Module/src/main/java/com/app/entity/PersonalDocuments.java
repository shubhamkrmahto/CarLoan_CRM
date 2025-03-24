package com.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDocuments {
	
	private byte[] documentId;
	private byte[] addressProof;
	private byte[] panCard;
	private byte[] incomeTax;
	private byte[] aadharCard;
	private byte[] photo;
	private byte[] signature;
	private byte[] bankCheque;
	private byte[] salarySlips;

}

package com.app.entity;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int documentId;
	
	@Lob
	@Column(length = 999999999)
	private byte[] addressProof;
	
	@Lob
	@Column(length = 999999999)
	private byte[] panCard;
	
	@Lob
	@Column(length = 999999999)
	private byte[] incomeTax;
	
	@Lob
	@Column(length = 999999999)
	private byte[] aadharCard;
	
	@Lob
	@Column(length = 999999999)
	private byte[] photo;
	
	@Lob
	@Column(length = 999999999)
	private byte[] signature;
	
	@Lob
	@Column(length = 999999999)
	private byte[] bankCheque;
	
	@Lob
	@Column(length = 999999999)
	private byte[] salarySlips;

}

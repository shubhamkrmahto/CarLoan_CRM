package com.app.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.app.enums.EnquiryStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class LoanEnquiry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String customerName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String gender;
	private String customerEmailId;
	private Long customerContactNumber;
	private Long customerAlternateNumber;
	private Long aadharNo;
	private String panCardNo;
	@Enumerated(EnumType.STRING)
	private EnquiryStatusEnum enquiryStatus;
	@OneToOne(cascade = CascadeType.ALL)
	private Cibil cibil;
	
	@CreationTimestamp
	private LocalDate enquiryDateTime;

//	{
//	"customerName":"raj",
//	"dateOfBirth":"02/10/2000",
//	"gender":"Male",
//	"customerEmailId":"krushnachandane05@gmail.com",
//	"customerContactNumber":9096868834,
//	"customerAlternateNumber":9119439397,
//	"aadharNo":345656474632,
//	"panCardNo":"PAN3535CARD"
//	}
}
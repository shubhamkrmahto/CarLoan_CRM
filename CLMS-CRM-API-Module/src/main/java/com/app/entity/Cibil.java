package com.app.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.enums.CibilStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Cibil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cibilId;
	private Integer cibilScore;
	@DateTimeFormat
	private LocalDateTime cibilScoreDateTime;
	@Enumerated(EnumType.STRING)
	private CibilStatusEnum status;
	private String cibilRemark;



}

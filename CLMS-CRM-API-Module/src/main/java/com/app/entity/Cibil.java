package com.app.entity;

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
	@Enumerated(EnumType.STRING)
	private CibilStatusEnum cibilStatus;

}

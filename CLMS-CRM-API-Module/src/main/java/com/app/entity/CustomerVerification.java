package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class CustomerVerification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cusVerificationId;
	

}

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocalAddress {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer localAddressId;
	private String areaName;
	private String cityName;
	private String district;
	private String state;
	private Long pincode;
    private Integer houseNumber;
	private String streetName;
	
}

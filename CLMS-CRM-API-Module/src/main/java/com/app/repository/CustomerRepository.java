package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByCustomerId(Integer Id);
	
}

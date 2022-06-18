package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Customers;

public interface CustomerRepo extends JpaRepository<Customers, Long> {

	Customers findByEmail(String email);

}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AuthenticationProvider;
import com.example.demo.model.Customers;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.utils.Constants;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	public Customers getCustomerByEmail(String email) {
		Customers cust = customerRepo.findByEmail(email);
		return cust;
	}

	public void createNewCustomerAfterOAuthLoginSuccess(String email,String name,AuthenticationProvider provider) {
		Customers custData =  new Customers();
		custData.setName(name);
		custData.setEmail(email);
		custData.setLoginStatus(true);
		custData.setCreatedAt(Constants.getDateAndTime());
		custData.setAuthProvider(provider);
		
		customerRepo.save(custData);
		
	}

	public void updateCustomerAfterOAuthLoginSuccess(Customers customer, String name, AuthenticationProvider provider) {
		customer.setName(name);
		customer.setAuthProvider(provider);
		customer.setCreatedAt(Constants.getDateAndTime());
		customer.setLoginStatus(true);
		customerRepo.save(customer);
	}

}

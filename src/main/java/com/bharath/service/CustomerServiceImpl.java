package com.bharath.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bharath.dto.CustomerDTO;
import com.bharath.entity.Customer;
import com.bharath.exception.CustomerException;
import com.bharath.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public CustomerDTO addCustomer(CustomerDTO customerDTO) throws CustomerException {
		Customer customer = Customer.builder().dob(customerDTO.getDob()).firstName(customerDTO.getFirstName())
				.lastName(customerDTO.getLastName()).email(customerDTO.getEmail()).build();
		customer = customerRepository.save(customer);

		return customerDTO;
	}

}

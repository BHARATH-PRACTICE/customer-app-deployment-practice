package com.bharath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.dto.CustomerDTO;
import com.bharath.exception.CustomerException;
import com.bharath.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/add-customer")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerException {
		return new ResponseEntity<CustomerDTO>(customerService.addCustomer(customerDTO), HttpStatus.CREATED);
	}

}

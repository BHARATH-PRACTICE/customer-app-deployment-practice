package com.bharath.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping(value = "/view-customer/{cid}")
	public ResponseEntity<CustomerDTO> viewCustomer(@PathVariable(required=true,name="cid")Long customerId ) throws CustomerException {
		
		CustomerDTO dto= new CustomerDTO();
		dto.setDob(LocalDate.now());
		dto.setEmail("email@gmail.com");
		dto.setFirstName("firstName");
		dto.setLastName("lastName");
		return new ResponseEntity<CustomerDTO>(dto,HttpStatus.OK);

	}
	

}

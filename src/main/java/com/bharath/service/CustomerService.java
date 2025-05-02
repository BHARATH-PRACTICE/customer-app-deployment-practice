package com.bharath.service;

import com.bharath.dto.CustomerDTO;
import com.bharath.exception.CustomerException;

public interface CustomerService {
CustomerDTO addCustomer(CustomerDTO customerDTO) throws CustomerException;
}

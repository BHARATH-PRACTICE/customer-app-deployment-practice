package com.bharath.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

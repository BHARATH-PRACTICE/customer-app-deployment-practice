package com.bharath.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomerDTO {
private String firstName;
private String lastName;
private LocalDate dob;
private String email;
private String phoneNumber;

}

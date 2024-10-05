package com.sundar.pojo;

import lombok.Data;

@Data
public class EmployeeResponce {

	private Long id;
	private String name;
	private String email;
	private String age;
    // Add AddressResponse Here
	private AddressResponse addressResponse;
}

package com.sundar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.pojo.AddressResponse;
import com.sundar.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	 @GetMapping("/address/{employeeId}")
	    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable long employeeId) {
	        AddressResponse addressResponse = addressService.getAddressByEmployeeById(employeeId);
	        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	    }

}

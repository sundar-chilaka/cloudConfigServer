package com.sundar.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sundar.pojo.AddressResponse;

@FeignClient(name = "address-service", url = "http://localhost:8082/address")
public interface AddressClient {
	
	@GetMapping("/address/{id}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeById(@PathVariable Long id);
	
}

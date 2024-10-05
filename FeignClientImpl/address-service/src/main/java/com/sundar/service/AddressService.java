package com.sundar.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundar.entity.Address;
import com.sundar.pojo.AddressResponse;
import com.sundar.repository.AddressRepo;

@Service
public class AddressService {
	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ModelMapper mapper;

	public AddressResponse getAddressByEmployeeById(Long employeeId) {
		Optional<Address> addressByEmployeeId = addressRepo.findById(employeeId);
		AddressResponse addressResponse = mapper.map(addressByEmployeeId, AddressResponse.class);
		return addressResponse;

	}
}

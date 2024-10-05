package com.sundar.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sundar.entity.Employee;
import com.sundar.feignclient.AddressClient;
import com.sundar.pojo.AddressResponse;
import com.sundar.pojo.EmployeeResponce;
import com.sundar.repository.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ModelMapper mapper;

	// Spring will create the implementation
	// for this class
	// and will inject the bean here (proxy)
	@Autowired
	private AddressClient addressClient;

	public EmployeeResponce getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		EmployeeResponce employeeResponce = mapper.map(employee, EmployeeResponce.class);

		//// Using FeignClient
		ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByEmployeeById(id);
		employeeResponce.setAddressResponse(addressResponse.getBody());

		return employeeResponce;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

}

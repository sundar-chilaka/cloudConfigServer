package com.sundar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.model.Department;
import com.sundar.service.DepartmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@PostMapping("/save")
	public Department saveDepartment(@RequestBody Department department) {
		return departmentService.saveDepartment(department);
	}

	@GetMapping("/{departmentId}")
	@CircuitBreaker(name = "DepartmentService",fallbackMethod = "fallbackMethod")
	public Department getDepartmentById(@PathVariable Long departmentId) {
		return departmentService.getDepatmentById(departmentId);
	}

	@GetMapping("/get-all")
	public ResponseEntity<List<Department>> getAllDepartment() {
		List<Department> listOfDepartment = departmentService.getAllDeparment();
		return new ResponseEntity<>(listOfDepartment, HttpStatus.OK);
	}
	@ResponseStatus(HttpStatus.CREATED)
	public String fallbackMethod(@PathVariable long id, RuntimeException runEx) {
		return "Service is down. Please try after some time";
	}
}

package com.sundar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundar.model.Department;
import com.sundar.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department getDepatmentById(Long id) {
		return departmentRepository.findById(id).get();
	}
	
	public List<Department>  getAllDeparment() {
		return departmentRepository.findAll();
	}
}

//package com.sundar.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.sundar.model.Department;
//import com.sundar.repository.DepartmentRepository;
//
//public class DepartmentServiceImpl implements DepartmentService {
//
//	@Autowired
//	DepartmentRepository departmentRepository;
//
//	@Override
//	public Department saveDepartment(Department department) {
//		return departmentRepository.save(department);
//	}
//
//	@Override
//	public Department getDepatmentById(Long id) {
//		return departmentRepository.getById(id);
//	}
//
//}

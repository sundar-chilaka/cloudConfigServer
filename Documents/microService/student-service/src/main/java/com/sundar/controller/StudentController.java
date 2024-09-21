package com.sundar.controller;

import java.util.concurrent.CompletableFuture;

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

import com.sundar.model.Student;
import com.sundar.pojo.StudentDepartmentPojo;
import com.sundar.service.StudentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/save")
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}

	// Change StudentDepartmentPojo to String for mail sending
	@GetMapping("/get-stu-with-dept/{departmentId}")
	@CircuitBreaker(name = "StudentService", fallbackMethod = "fallbackMethod")
	@Retry(name = "studentservice")
	@TimeLimiter(name = "studentservice")
	public CompletableFuture<StudentDepartmentPojo> getStudentWithDepartment(@PathVariable Long departmentId) {
		return CompletableFuture.supplyAsync(() -> studentService.getStudentWithDepartment(departmentId));
	}
	// Change StudentDepartmentPojo to String for mail sending

//	@GetMapping("/mail-sent/{id}")
//	public String getStudentWithDepartment(@PathVariable Long id) {
//		studentService.getStudentWithDepartment(id);
//		return "Student with Department Details send to email.";
//	}

	@ResponseStatus(HttpStatus.CREATED)
	public String fallbackMethod(@PathVariable long id, RuntimeException runEx) {
		return "Service is down. Please try after some time";
	}
}

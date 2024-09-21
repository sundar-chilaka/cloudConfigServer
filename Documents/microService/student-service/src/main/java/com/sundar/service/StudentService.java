package com.sundar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sundar.model.Student;
import com.sundar.pojo.Depatrment;
import com.sundar.pojo.StudentDepartmentPojo;
import com.sundar.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MailService mailService;

	private String baseUrl = "http://DEPARTMENTSERVICE/department/";

	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public StudentDepartmentPojo getStudentWithDepartment(Long departmentId) {
		Student student = studentRepository.findById(departmentId).get();
		Depatrment depatrment = restTemplate.getForObject(baseUrl + student.getDepartmentId(), Depatrment.class);

		StudentDepartmentPojo studentDepartmentPojo = new StudentDepartmentPojo();
		studentDepartmentPojo.setStudent(student);
		studentDepartmentPojo.setDepatrment(depatrment);

		return studentDepartmentPojo;
	}

//	public String  getStudentWithDepartment(Long departmentId) {
//		Student student = studentRepository.findById(departmentId).get();
//		Depatrment depatrment = restTemplate.getForObject(baseUrl + student.getDepartmentId(), Depatrment.class);
////
////		StudentDepartmentPojo studentDepartmentPojo = new StudentDepartmentPojo();
////		studentDepartmentPojo.setStudent(student);
////		studentDepartmentPojo.setDepatrment(depatrment);
//		
//		StringBuffer buffer = new StringBuffer();
//		
//		buffer.append("Please find below ");
//		buffer.append("\n");
//		buffer.append(student.getStudentId()+" "+student.getFirstName()+" "+student.getLastName()+" "+student.getEmail()+" "+student.getDepartmentId()
//		);
//		buffer.append("\n");
//		buffer.append(depatrment.getDepartmentNmae()+" "+depatrment.getDepartmentCode()+" "+depatrment.getDepartmentAddress());
//		mailService.sendMail("sundar3.ch@gmail.com", "Student With Department info", buffer.toString());
//		return "Student With Department Details set to mail.";
//	}
}

package com.sundar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long studentId;

	private String firstName;

	private String lastName;

	private String email;

	private Long departmentId;

}

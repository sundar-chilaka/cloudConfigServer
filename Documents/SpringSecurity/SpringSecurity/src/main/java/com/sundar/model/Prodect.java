package com.sundar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_prodect")
public class Prodect {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long prodectId;

	private String prodectName;

	private Double price;

	private String dept;

}

package com.sundar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl2_prodect")
public class Prodect {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long prodectId;
	@NotNull(message = "Prodect name Should not be null")
	@NotEmpty(message = "Prodect name should not be empty")
	private String prodectName;
	@NotNull(message = "Price name Should not be null")
	@NotEmpty(message = "Price name should not be empty")
	@Min(value = 10)
	@Max(value = 100000)
	private Double price;
	@NotNull(message = "Department name Should not be null")
	@NotEmpty(message = "Department name should not be empty")
	private String dept;

}

package com.deekol.pocketbookrepair.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String maker;
	private String name;
	private String specification;
	
	@Column(name = "full_specification")
	private String fullSpecification;
	private String state;
	private String description;
	private BigDecimal buy;
	private BigDecimal sale;
	
	@Column(name = "creation_date")
	private LocalDate creationDate;
}

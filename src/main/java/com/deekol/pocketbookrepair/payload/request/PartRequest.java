package com.deekol.pocketbookrepair.payload.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PartRequest {
	private String maker;
	
	@NotBlank
	private String name;
	private String specification;
	private String description;
	private BigDecimal buy;
	private BigDecimal sale;
	private Long deviceId;
}

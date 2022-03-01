package com.deekol.pocketbookrepair.payload.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponse {
	private Long id;
	private String name;
	private String specification;
	private String fullSpecification;
	private String state;
	private String description;
	private BigDecimal buy;
	private BigDecimal sale;

	private String cpu;
	private String gpu;
	private int ram;
	private int hdd;
	private boolean battery;
	private String defect;
	private String made;
}

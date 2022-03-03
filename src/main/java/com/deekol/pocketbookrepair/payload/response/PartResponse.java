package com.deekol.pocketbookrepair.payload.response;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class PartResponse {
	private Long id;
	private String maker;
	private String name;
	private String specification;
	private String description;
	private BigDecimal buy;
	private BigDecimal sale;
	private LocalDate creationDate;
	private Long deviceId;
}

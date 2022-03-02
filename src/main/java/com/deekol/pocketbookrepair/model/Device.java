package com.deekol.pocketbookrepair.model;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Device extends Basic {
	private String cpu;
	private String gpu;
	private int ram;
	private int hdd;
	private boolean battery;
	private String defect;
	private String made;

	public Device(Long id, String maker, String name, String specification, String fullSpecification, String state,
			String description, BigDecimal buy, BigDecimal sale, String cpu, String gpu, int ram, int hdd,
			boolean battery, String defect, String made) {
		super(id, maker, name, specification, fullSpecification, state, description, buy, sale);
		this.cpu = cpu;
		this.gpu = gpu;
		this.ram = ram;
		this.hdd = hdd;
		this.battery = battery;
		this.defect = defect;
		this.made = made;
	}
}

package com.deekol.pocketbookrepair.model;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Device extends Basic {
	private String cpu;
	private String gpu;
	private int ram;
	private int hdd;
	private boolean battery;
	private String defect;
	private String made;
}

package com.deekol.pocketbookrepair.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "device")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity extends BasicEntity {
	private String cpu;
	private String gpu;
	private int ram;
	private int hdd;
	private boolean battery;
	private String defect;
	
	@OneToMany(mappedBy = "deviceEntity")
	private Set<PartEntity> partEntity;

	@Builder
	public DeviceEntity(Long id, String name, String specification, String description, BigDecimal buy, BigDecimal sale,
			String cpu, String gpu, int ram, int hdd, boolean battery, String defect) {
		super(id, name, specification, description, buy, sale);
		this.cpu = cpu;
		this.gpu = gpu;
		this.ram = ram;
		this.hdd = hdd;
		this.battery = battery;
		this.defect = defect;
	}
}

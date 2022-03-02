package com.deekol.pocketbookrepair.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "device")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DeviceEntity extends Device {
	
	@OneToMany(mappedBy = "deviceEntity")
	private Set<PartEntity> partEntity;

	@Builder
	public DeviceEntity(Long id, String maker, String name, String specification, String fullSpecification,
			String state, String description, BigDecimal buy, BigDecimal sale, String cpu, String gpu, int ram, int hdd,
			boolean battery, String defect, String made, Set<PartEntity> partEntity) {
		super(id, maker, name, specification, fullSpecification, state, description, buy, sale, cpu, gpu, ram, hdd,
				battery, defect, made);
		this.partEntity = partEntity;
	}
}

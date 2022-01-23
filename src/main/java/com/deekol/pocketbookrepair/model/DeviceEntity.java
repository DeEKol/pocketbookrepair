package com.deekol.pocketbookrepair.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "device")
@EqualsAndHashCode(callSuper = true)
public class DeviceEntity extends BasicEntity {
	private String defect;
	
	@OneToMany(mappedBy = "deviceEntity")
	private Set<PartEntity> partEntity;

	@Builder
	public DeviceEntity(Long id, String name, String specification, String description, BigDecimal buy,
			BigDecimal sale, String defect) {
		super(id, name, specification, description, buy, sale);
		defect = this.defect;
	}
	
	
}

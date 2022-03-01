package com.deekol.pocketbookrepair.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "part")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PartEntity extends Basic {

	private String suitable;
	
	@ManyToOne
	@JoinColumn(name = "device_id")
	private DeviceEntity deviceEntity;

	@Builder
	public PartEntity(Long id, String name, String specification, String fullSpecification, String state, String description, BigDecimal buy, BigDecimal sale, DeviceEntity deviceEntity, String suitable) {
		super(id, name, specification, fullSpecification, state, description, buy, sale);
		this.deviceEntity = deviceEntity;
		this.suitable = suitable;
	}
}

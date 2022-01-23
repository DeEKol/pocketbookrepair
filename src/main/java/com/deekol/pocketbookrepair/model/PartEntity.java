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
public class PartEntity extends BasicEntity {

	@ManyToOne
	@JoinColumn(name = "device_id")
	private DeviceEntity deviceEntity;

	public PartEntity(Long id, String name, String specification, String description, BigDecimal buy, BigDecimal sale, DeviceEntity deviceEntity) {
		super(id, name, specification, description, buy, sale);
		deviceEntity = this.deviceEntity;
	}

	@Builder
	public PartEntity(Long id, String name, String specification, String description, BigDecimal buy, BigDecimal sale) {
		super(id, name, specification, description, buy, sale);
	}
	
	
}

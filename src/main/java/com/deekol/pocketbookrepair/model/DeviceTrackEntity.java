package com.deekol.pocketbookrepair.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "device_track")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DeviceTrackEntity extends Device {
	@Column(name = "track_date")
	private String trackDate;

	@Builder
	public DeviceTrackEntity(Long id, String maker, String name, String specification, String fullSpecification,
			String state, String description, BigDecimal buy, BigDecimal sale, String cpu, String gpu, int ram, int hdd,
			boolean battery, String defect, String made, String trackDate) {
		super(id, maker, name, specification, fullSpecification, state, description, buy, sale, cpu, gpu, ram, hdd,
				battery, defect, made);
		this.trackDate = trackDate;
	}
}

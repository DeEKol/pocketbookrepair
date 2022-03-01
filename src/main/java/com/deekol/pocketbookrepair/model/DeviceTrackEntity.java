package com.deekol.pocketbookrepair.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

	public DeviceTrackEntity(String cpu, String gpu, int ram, int hdd, boolean battery, String defect, String made, String trackDate) {
		super(cpu, gpu, ram, hdd, battery, defect, made);
		this.trackDate = trackDate;
	}
}

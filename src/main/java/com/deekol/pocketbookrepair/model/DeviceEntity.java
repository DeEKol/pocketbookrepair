package com.deekol.pocketbookrepair.model;

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
	public DeviceEntity(String cpu, String gpu, int ram, int hdd, boolean battery, String defect, String made) {
		super(cpu, gpu, ram, hdd, battery, defect, made);
	}	
}

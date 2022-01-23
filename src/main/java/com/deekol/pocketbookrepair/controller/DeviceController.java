package com.deekol.pocketbookrepair.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deekol.pocketbookrepair.model.DeviceEntity;
import com.deekol.pocketbookrepair.repository.DeviceRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/device")
@AllArgsConstructor
public class DeviceController {
private final DeviceRepository deviceRepository;
	
	@GetMapping
	public List<DeviceEntity> getAll() {
		return deviceRepository.findAll();
	}
	
	@GetMapping("{id}")
	public DeviceEntity getOne(@PathVariable("id") Long id) {
		return deviceRepository.findById(id).get();
	}
	
	@PostMapping
	public DeviceEntity create(@RequestBody DeviceEntity deviceEntity) {
		return deviceRepository.save(deviceEntity);
	}
	
	@PutMapping("{id}")
	public DeviceEntity update(@PathVariable("id") DeviceEntity deviceFromDb, @RequestBody DeviceEntity deviceEntity) {
		BeanUtils.copyProperties(deviceEntity, deviceFromDb, "id");
		return deviceRepository.save(deviceFromDb);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		deviceRepository.deleteById(id);
	}
}
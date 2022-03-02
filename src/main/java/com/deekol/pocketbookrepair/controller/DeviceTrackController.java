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

import com.deekol.pocketbookrepair.model.DeviceTrackEntity;
import com.deekol.pocketbookrepair.repository.DeviceTrackRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/deviceTrack")
@AllArgsConstructor
public class DeviceTrackController {
	private final DeviceTrackRepository deviceTrackRepository;
	
	@GetMapping
	public List<DeviceTrackEntity> getAll() {
		return deviceTrackRepository.findAll();
	}
	
	@GetMapping("{id}")
	public DeviceTrackEntity getOne(@PathVariable("id") Long id) {
		return deviceTrackRepository.findById(id).get();
	}
	
	@PostMapping
	public DeviceTrackEntity create(@RequestBody DeviceTrackEntity deviceTrackEntity) {
		return deviceTrackRepository.save(deviceTrackEntity);
	}
	
	@PutMapping("{id}")
	public DeviceTrackEntity update(@PathVariable("id") DeviceTrackEntity deviceFromDb, @RequestBody DeviceTrackEntity deviceTrackEntity) {
		BeanUtils.copyProperties(deviceTrackEntity, deviceFromDb, "id");
		return deviceTrackRepository.save(deviceFromDb);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		deviceTrackRepository.deleteById(id);
	}
}

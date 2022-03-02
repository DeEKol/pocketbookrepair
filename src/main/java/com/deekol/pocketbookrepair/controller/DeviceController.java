package com.deekol.pocketbookrepair.controller;

import java.util.ArrayList;
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
import com.deekol.pocketbookrepair.payload.response.DeviceResponse;
import com.deekol.pocketbookrepair.repository.DeviceRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/device")
@AllArgsConstructor
public class DeviceController {
	private final DeviceRepository deviceRepository;
	
	@GetMapping
	public List<DeviceResponse> getAll() {
		List<DeviceEntity> deviceEntityList = deviceRepository.findAll();
		List<DeviceResponse> deviceResponseList = new ArrayList<>();
		
		for(DeviceEntity deviceEntity : deviceEntityList) {
			DeviceResponse deviceResponse = DeviceResponse.builder()
					.id(deviceEntity.getId())
					.maker(deviceEntity.getMaker())
					.name(deviceEntity.getName())
					.specification(deviceEntity.getSpecification())
					.fullSpecification(deviceEntity.getFullSpecification())
					.description(deviceEntity.getDescription())
					.buy(deviceEntity.getBuy())
					.sale(deviceEntity.getSale())

					.cpu(deviceEntity.getCpu())
					.gpu(deviceEntity.getGpu())
					.ram(deviceEntity.getRam())
					.hdd(deviceEntity.getHdd())
					.battery(deviceEntity.isBattery())
					.defect(deviceEntity.getDefect())
					.made(deviceEntity.getMade())
					.build();
			
			deviceResponseList.add(deviceResponse);
		}
		
		return deviceResponseList;
	}
	
	@GetMapping("{id}")
	public DeviceResponse getOne(@PathVariable("id") Long id) {
		DeviceEntity deviceEntity = deviceRepository.findById(id).get();
		
		DeviceResponse deviceResponse = DeviceResponse.builder()
				.id(deviceEntity.getId())
				.maker(deviceEntity.getMaker())
				.name(deviceEntity.getName())
				.specification(deviceEntity.getSpecification())
				.description(deviceEntity.getDescription())
				.buy(deviceEntity.getBuy())
				.sale(deviceEntity.getSale())

				.cpu(deviceEntity.getCpu())
				.gpu(deviceEntity.getGpu())
				.ram(deviceEntity.getRam())
				.hdd(deviceEntity.getHdd())
				.battery(deviceEntity.isBattery())
				.defect(deviceEntity.getDefect())
				.made(deviceEntity.getMade())
				.build();
		
		return deviceResponse;
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
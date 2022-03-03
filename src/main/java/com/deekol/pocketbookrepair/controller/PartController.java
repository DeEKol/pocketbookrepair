package com.deekol.pocketbookrepair.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import com.deekol.pocketbookrepair.model.PartEntity;
import com.deekol.pocketbookrepair.payload.request.PartRequest;
import com.deekol.pocketbookrepair.payload.response.PartResponse;
import com.deekol.pocketbookrepair.repository.DeviceRepository;
import com.deekol.pocketbookrepair.repository.PartRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/part")
@AllArgsConstructor
public class PartController {
	private final PartRepository partRepository;
	private final DeviceRepository deviceRepository;
	
	@GetMapping
	public List<PartResponse> getAll() {
		List<PartEntity> partEntityList = partRepository.findAll();
		List<PartResponse> partResponseList = new ArrayList<>();
		
		for(PartEntity partEntity : partEntityList) {
			Long deviceId = null;
			if (partEntity.getDeviceEntity() != null) {
				deviceId = partEntity.getDeviceEntity().getId();
			}
			
			PartResponse partResponse = PartResponse.builder()
					.id(partEntity.getId())
					.maker(partEntity.getMaker())
					.name(partEntity.getName())
					.specification(partEntity.getSpecification())
					.description(partEntity.getDescription())
					.buy(partEntity.getBuy())
					.sale(partEntity.getSale())
					.creationDate(partEntity.getCreationDate())
					.deviceId(deviceId)
					.build();
			
			partResponseList.add(partResponse);
		}
		
		return partResponseList;
	}
	
	@GetMapping("{id}")
	public PartResponse getOne(@PathVariable("id") Long id) {
		PartEntity partEntity = partRepository.findById(id).get();
		
		Long deviceId = null;
		if (partEntity.getDeviceEntity() != null) {
			deviceId = partEntity.getDeviceEntity().getId();
		}
		
		PartResponse partResponse = PartResponse.builder()
				.id(partEntity.getId())
				.maker(partEntity.getMaker())
				.name(partEntity.getName())
				.specification(partEntity.getSpecification())
				.description(partEntity.getDescription())
				.buy(partEntity.getBuy())
				.sale(partEntity.getSale())
				.creationDate(partEntity.getCreationDate())
				.deviceId(deviceId)
				.build();
		
		return partResponse;
	}
	
	@PostMapping
	public PartResponse add(@RequestBody PartRequest partRequest) {
		DeviceEntity deviceEntity = null;
		if (partRequest.getDeviceId() != null) {
			deviceEntity = deviceRepository.getById(partRequest.getDeviceId());
		}
		
		PartEntity partEntity = PartEntity.builder()
				.name(partRequest.getName())
				.specification(partRequest.getSpecification())
				.description(partRequest.getDescription())
				.buy(partRequest.getBuy())
				.sale(partRequest.getSale())
				.creationDate(LocalDate.now())
				.deviceEntity(deviceEntity)
				.build();
		
		partRepository.save(partEntity);
		
		Long deviceId = null;
		if (partEntity.getDeviceEntity() != null) {
			deviceId = partEntity.getDeviceEntity().getId();
		}
		
		return PartResponse.builder()
												.id(partEntity.getId())
												.name(partEntity.getName())
												.specification(partEntity.getSpecification())
												.description(partEntity.getDescription())
												.buy(partEntity.getBuy())
												.sale(partEntity.getSale())
												.creationDate(partEntity.getCreationDate())
												.deviceId(deviceId)
												.build();
	}
	
	@PutMapping("{id}")
	public PartResponse update(@PathVariable("id") PartEntity partFromDb, @Valid @RequestBody PartRequest partRequest) {
		DeviceEntity deviceEntity = null;
		if (partRequest.getDeviceId() != null) {
			deviceEntity = deviceRepository.getById(partRequest.getDeviceId());
		}
		PartEntity partEntity = PartEntity.builder()
				.name(partRequest.getName())
				.specification(partRequest.getSpecification())
				.description(partRequest.getDescription())
				.buy(partRequest.getBuy())
				.sale(partRequest.getSale())
				.creationDate(LocalDate.now())
				.deviceEntity(deviceEntity)
				.build();
		
		BeanUtils.copyProperties(partEntity, partFromDb, "id");
		partRepository.save(partFromDb);
		
		Long deviceId = null;
		if (partFromDb.getDeviceEntity() != null) {
			deviceId = partFromDb.getDeviceEntity().getId();
		}
		
		return PartResponse.builder()
				.id(partFromDb.getId())
				.name(partFromDb.getName())
				.specification(partFromDb.getSpecification())
				.description(partFromDb.getDescription())
				.buy(partFromDb.getBuy())
				.sale(partFromDb.getSale())
				.creationDate(partFromDb.getCreationDate())
				.deviceId(deviceId)
				.build();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		partRepository.deleteById(id);
	}
}

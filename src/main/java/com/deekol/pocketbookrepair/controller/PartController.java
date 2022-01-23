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

import com.deekol.pocketbookrepair.model.PartEntity;
import com.deekol.pocketbookrepair.repository.PartRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/part")
@AllArgsConstructor
public class PartController {
	private final PartRepository partRepository;
	
	@GetMapping
	public List<PartEntity> getAll() {
		return partRepository.findAll();
	}
	
	@GetMapping("{id}")
	public PartEntity getOne(@PathVariable("id") Long id) {
		return partRepository.findById(id).get();
	}
	
	@PostMapping
	public PartEntity add(@RequestBody PartEntity partEntity) {
		return partRepository.save(partEntity);
	}
	
	@PutMapping("{id}")
	public PartEntity update(@PathVariable("id") PartEntity partFromDb, @RequestBody PartEntity partEntity) {
		BeanUtils.copyProperties(partEntity, partFromDb, "id");
		return partRepository.save(partFromDb);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		partRepository.deleteById(id);
	}
}

package com.deekol.pocketbookrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deekol.pocketbookrepair.model.PartEntity;

public interface PartRepository extends JpaRepository<PartEntity, Long> {
}

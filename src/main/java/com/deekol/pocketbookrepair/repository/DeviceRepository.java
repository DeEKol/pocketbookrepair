package com.deekol.pocketbookrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deekol.pocketbookrepair.model.DeviceEntity;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

}

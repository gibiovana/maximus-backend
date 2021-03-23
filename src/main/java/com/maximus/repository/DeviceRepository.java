package com.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maximus.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Device findByModel(String model);
    
    Device findByDeviceId(Integer deviceId);
}
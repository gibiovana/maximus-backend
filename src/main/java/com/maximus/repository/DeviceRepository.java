package com.maximus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maximus.model.Device;
import com.maximus.model.Patient;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Device findByModel(String model);
    
    Device findByDeviceId(Integer deviceId);
    
    List<Device> findAll();
}

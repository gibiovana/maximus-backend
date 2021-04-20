package com.maximus.service;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.maximus.model.Device;
import com.maximus.model.Doctor;
import com.maximus.model.Patient;
import com.maximus.dto.DeviceDTO;
import com.maximus.mapper.DeviceMapper;
import com.maximus.repository.DeviceRepository;
import com.maximus.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceService {
	@Autowired
	private DeviceRepository repository;

	@Autowired
	private PatientRepository patRepo;
	
	public Optional<Device> findById(Integer id){
		return this.repository.findById(id);
	}
	
	@Transactional
	public DeviceDTO storeDeviceData(DeviceDTO dto) {
		Device device = DeviceMapper.fromDTOToEntity(dto);		
		
		device.setModel(dto.getModel());
		device.setOperatingSystem(dto.getOperatingSystem());
		device.setOwner(dto.getOwner());
		
		Patient owner = dto.getOwner();
		Optional<Patient> existingPatient = Optional.ofNullable(this.patRepo.findByPatientId(owner.getPatientId()));
		existingPatient.get().setDevice(device);
		owner.setDevice(device);

		device = this.repository.save(device);
		return DeviceMapper.fromEntityToDTO(device);
	}
}

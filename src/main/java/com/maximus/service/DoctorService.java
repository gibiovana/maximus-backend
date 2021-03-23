package com.maximus.service;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.maximus.model.Doctor;
import com.maximus.dto.DoctorDTO;
import com.maximus.mapper.DoctorMapper;
import com.maximus.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository repository;
	
	public Optional<Doctor> findById(Integer id){
		return this.repository.findByDoctorId(id);
	}
	
	public DoctorDTO storeDoctorData(DoctorDTO dto) {
		Doctor doctor = DoctorMapper.fromDTOToEntity(dto);
		Optional<Doctor> existingUser = this.repository.findByDoctorId(doctor.getDoctorId());
		
		if(!existingUser.isPresent()) {
			doctor = this.repository.save(doctor);
			return DoctorMapper.fromEntityToDTO(doctor);
		}
		
		return DoctorMapper.fromEntityToDTO(existingUser.get());
	}
	
}

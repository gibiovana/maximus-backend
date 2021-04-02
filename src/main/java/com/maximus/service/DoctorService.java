package com.maximus.service;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.maximus.model.Doctor;
import com.maximus.dto.DoctorDTO;
import com.maximus.mapper.DoctorMapper;
import com.maximus.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DoctorService {
	@Autowired
	private DoctorRepository repository;
	
	public Optional<Doctor> findById(Integer id){
		return this.repository.findByDoctorId(id);
	}

	public Optional<Doctor> getUser(String doctorEmail, String password){
		return this.repository.findByDoctorEmailAndPassword(doctorEmail, password);
	}
	
	@Transactional
	public DoctorDTO storeDoctorData(DoctorDTO dto) {
		Doctor doctor = DoctorMapper.fromDTOToEntity(dto);
		Optional<Doctor> existingUser = doctor.getDoctorId() != null ? this.repository.findByDoctorCRM(dto.getDoctorCRM()) : Optional.ofNullable(null);
		
		if(!existingUser.isPresent()) {
			doctor.setDoctorName(dto.getDoctorName());
			doctor.setDoctorEmail(dto.getDoctorEmail());
			doctor.setDoctorCRM(dto.getDoctorCRM());
			doctor.setPassword(dto.getPassword());
		}else{
			new Exception("Usuário já foi registrado.");
		}

		doctor = this.repository.save(doctor);
		return DoctorMapper.fromEntityToDTO(doctor);
	}
}

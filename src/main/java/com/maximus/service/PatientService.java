package com.maximus.service;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.maximus.model.Doctor;
import com.maximus.model.Institution;
import com.maximus.model.Patient;
import com.maximus.dto.PatientDTO;
import com.maximus.mapper.PatientMapper;
import com.maximus.repository.DoctorRepository;
import com.maximus.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PatientService {
	@Autowired
	private PatientRepository repository;
	
	@Autowired
	private DoctorRepository docRepository;
	
	public Optional<Patient> findById(Integer id){
		return this.repository.findById(id);
	}

	public Optional<Patient> getUser(String username, String password){
		return this.repository.findByUsernameAndPassword(username, password);
	}
	
	@Transactional
	public PatientDTO storePatientData(PatientDTO dto) {
		Patient patient = PatientMapper.fromDTOToEntity(dto);
		Optional<Patient> existingUser = patient.getPatientId() != null ? this.repository.findByProntuary(dto.getProntuary()) : Optional.ofNullable(null);
		
		if(!existingUser.isPresent()) {
	    	patient.setName(dto.getName());
	    	patient.setProntuary(dto.getProntuary());
	    	patient.setPathologicalCondition(dto.getPathologicalCondition());
	    	patient.setPatientHeight(dto.getPatientHeight());
	    	patient.setPatientWeight(dto.getPatientWeight());
	    	patient.setBirthdate(dto.getBirthdate());
	    	patient.setUsername(dto.getUsername());
	    	patient.setPassword(dto.getPassword());
	    	patient.setInstitution(dto.getInstitution());
	    	patient.setDoctorsAssigned(dto.getDoctorsAssigned());
	    	
			List<Doctor> doctors = patient.getDoctorsAssigned();
			List<Patient> aux = new ArrayList<>();
			aux.add(patient);
			
			for(Doctor doctor : doctors) {
				List<Patient> hasPatients = doctor.getPatientList();
				if(hasPatients != null) {
					hasPatients.add(patient);
				}else {
					//doctor.setPatientList(aux);
					Optional<Doctor> existingDoc = this.docRepository.findByDoctorId(doctor.getDoctorId());
					existingDoc.get().setPatientList(aux);
				}
			}
			
			Institution institution = patient.getInstitution();
			institution.setPatientList(aux);
			
		}else{
			new Exception("Usuário já foi registrado.");
		}

		patient = this.repository.save(patient);
		return PatientMapper.fromEntityToDTO(patient);
	}
}

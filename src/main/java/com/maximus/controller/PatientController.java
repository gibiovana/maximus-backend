package com.maximus.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maximus.dto.PatientDTO;
import com.maximus.mapper.PatientMapper;
import com.maximus.model.Patient;
import com.maximus.repository.PatientRepository;
import com.maximus.service.PatientService;


@Controller
@RequestMapping(path = "/patient")
public class PatientController {
	 
	private PatientService patientService;
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@Autowired
	private PatientRepository repo;
	
	@GetMapping(path = "/patientname/{value}")
	@ResponseBody
	public List<Patient> getAllPatientsByName (@PathVariable(required = true, name="value")String name){
		return repo.findByNameContaining(name);
	}
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Patient> getAllPatients(){
		return repo.findAll();
	}
	
	
	@PostMapping("/register")
	public PatientDTO registerPatient(@RequestBody PatientDTO patientDTO) {
		return this.patientService.storePatientData(patientDTO);
	}

	@RequestMapping("/login/{doctorEmail}&{password}")
	@ResponseBody
	public PatientDTO getCurrentPatient(@PathVariable(required = true, name="username")String username, @PathVariable(required = true, name="password")String password) throws Exception{
		Patient patient = ((PatientRepository) this.repo).findByUsernameAndPassword(username, password)
		.orElseThrow(() -> new Exception("Patient not found - " + username));
		return PatientMapper.fromEntityToDTO(patient);
	}
	
}

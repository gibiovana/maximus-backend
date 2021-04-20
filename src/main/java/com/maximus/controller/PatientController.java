package com.maximus.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maximus.dto.PatientDTO;
import com.maximus.mapper.PatientMapper;
import com.maximus.model.Institution;
import com.maximus.model.Patient;
import com.maximus.repository.PatientRepository;
import com.maximus.service.PatientService;

@CrossOrigin(origins = "*")
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

	@GetMapping(path = "/{id}")
	@ResponseBody
	public Patient getPatientById (@PathVariable(required = true, name="id")Integer id){
		return repo.findByPatientId(id);
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
	
	@RequestMapping("/login/{institution}")
	@ResponseBody
	public PatientDTO getCurrentPatientByInstitution(@PathVariable(required = true, name="institution")Institution institution) throws Exception{
		Patient patient = ((PatientRepository) this.repo).findByInstitution(institution)
		.orElseThrow(() -> new Exception("Patient not found"));
		return PatientMapper.fromEntityToDTO(patient);
	}
	
}

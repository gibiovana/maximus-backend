package com.example.maximus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.maximus.model.Patient;
import com.example.maximus.repository.PatientRepository;


@Controller
@RequestMapping(path = "/patient")
public class PatientController {
	 
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
	
	@GetMapping(path = "/count")
	@ResponseBody
	public long count() {
		return repo.count();
	}
}

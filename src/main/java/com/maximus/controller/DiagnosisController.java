package com.maximus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maximus.model.Diagnosis;
import com.maximus.model.Patient;
import com.maximus.repository.DiagnosisRepository;

@Controller
@RequestMapping(path = "/diagnosis")
public class DiagnosisController {
	
	@Autowired
	private DiagnosisRepository repo;
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Diagnosis> getAllDiagnosis (@PathVariable(required = true, name="value")String name){
		return repo.findAll();
	}
	
	@GetMapping(path = "/patient/{value}")
	@ResponseBody
	public Diagnosis getDiagnosisByPatient (@PathVariable(required = true, name="value")Patient patient){
		return repo.findByPatient(patient);
	}
}

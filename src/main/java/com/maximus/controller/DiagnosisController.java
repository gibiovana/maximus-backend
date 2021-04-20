package com.maximus.controller;

import java.util.List;
import java.util.Optional;

import com.maximus.dto.DiagnosisDTO;
import com.maximus.dto.DoctorDTO;
import com.maximus.service.DiagnosisService;
import com.maximus.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maximus.model.Diagnosis;
import com.maximus.model.Patient;
import com.maximus.repository.DiagnosisRepository;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/diagnosis")
public class DiagnosisController {
	private DiagnosisService diagnosisService;

	@Autowired
	public DiagnosisController(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	@Autowired
	private DiagnosisRepository repo;
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Diagnosis> getAllDiagnosis (){
		return repo.findAll();
	}
	
	@GetMapping(path = "/patient/{value}")
	@ResponseBody
	public Optional<Diagnosis> getDiagnosisByPatient (@PathVariable(required = true, name="value")Patient patient){
		return repo.findByPatient(patient);
	}

	@PostMapping("/register")
	public DiagnosisDTO registerDoctor(@RequestBody DiagnosisDTO diagnosisDTO) {
		return this.diagnosisService.storeDiagnosisData(diagnosisDTO);
	}

}

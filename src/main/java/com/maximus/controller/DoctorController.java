package com.maximus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.maximus.service.DoctorService;
import com.maximus.model.Doctor;
import com.maximus.dto.DoctorDTO;
import com.maximus.repository.DoctorRepository;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/doctor")
public class DoctorController {
	
	private DoctorService doctorService;

  @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
  }

	@Autowired
	private DoctorRepository repo;
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Doctor> getAllDoctors (){
		return repo.findAll();
	}
	
	@GetMapping(path = "/{value}")
	@ResponseBody
	public Optional<Doctor> getDoctorByCRM (@PathVariable(required = true, name="value")String crm){
		return repo.findByDoctorCRM(crm);
	}

	@GetMapping(path = "/id/{id}")
	@ResponseBody
	public Optional<Doctor> getDoctorById (@PathVariable(required = true, name="id")Integer id){
		return repo.findByDoctorId(id);
	}

	@PostMapping("/register")
	public DoctorDTO registerDoctor(@RequestBody DoctorDTO doctorDTO) {
		return this.doctorService.storeDoctorData(doctorDTO);
	}

	@RequestMapping("/login/{doctorEmail}?{password}")
	@ResponseBody
	public Optional<Doctor> getCurrentDoctor(@PathVariable(required = true, name="doctorEmail")String doctorEmail, @PathVariable(required = true, name="password")String password){
		return repo.findByDoctorEmailAndPassword(doctorEmail, password);
	}
}

package com.example.maximus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.maximus.model.Doctor;
import com.example.maximus.repository.DoctorRepository;

@Controller
@RequestMapping(path = "/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorRepository repo;
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Doctor> getAllDoctors (@PathVariable(required = true, name="value")String name){
		return repo.findAll();
	}
	
	@GetMapping(path = "/doctorname/{value}")
	@ResponseBody
	public Doctor getDoctorByName (@PathVariable(required = true, name="value")String name){
		return repo.findByDoctorName(name);
	}
}

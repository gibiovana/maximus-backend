package com.example.maximus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.maximus.model.Institution;
import com.example.maximus.repository.InstitutionRepository;

@Controller
@RequestMapping(path = "/institution")
public class InstitutionController {
	@Autowired
	private InstitutionRepository repo;
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Institution> getAllInstitutions (@PathVariable(required = true, name="value")String name){
		return repo.findAll();
	}
	
	@GetMapping(path = "/institution/{value}")
	@ResponseBody
	public Institution getInstitutionByName (@PathVariable(required = true, name="value")String name){
		return repo.findByInstitutionName(name);
	}
}

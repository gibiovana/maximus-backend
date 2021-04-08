package com.maximus.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maximus.dto.DoctorDTO;
import com.maximus.dto.InstitutionDTO;
import com.maximus.mapper.DoctorMapper;
import com.maximus.mapper.InstitutionMapper;
import com.maximus.model.Doctor;
import com.maximus.model.Institution;
import com.maximus.repository.DoctorRepository;
import com.maximus.repository.InstitutionRepository;
import com.maximus.service.InstitutionService;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(path = "/institution")
public class InstitutionController {

	private InstitutionService institutionService;

  @Autowired
    public InstitutionController(InstitutionService institutionService) {
    	this.institutionService = institutionService;
  }

	@Autowired
	private InstitutionRepository repo;
	
	@GetMapping(path = "/all")
	@ResponseBody
	public List<Institution> getAllInstitutions (){
		return repo.findAll();
	}
	
	@GetMapping(path = "/institution/{value}")
	@ResponseBody
	public  Optional<Institution> getInstitutionByName (@PathVariable(required = true, name="value")String name){
		return repo.findByInstitutionName(name);
	}
	
	@PostMapping("/register")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public InstitutionDTO registerAdmin(@RequestBody InstitutionDTO institutionDTO) {
		return this.institutionService.storeInstitutionData(institutionDTO);
	}
	
	@RequestMapping("/login/{adminEmail}&{password}")
	@ResponseBody
	public InstitutionDTO getCurrentInstitution(@PathVariable(required = true, name="adminEmail")String adminEmail, @PathVariable(required = true, name="password")String password) throws Exception{
		Institution institution = ((InstitutionRepository) this.repo).findByAdminEmailAndPassword(adminEmail, password)
		.orElseThrow(() -> new Exception("Institution not found - " + adminEmail));
		return InstitutionMapper.fromEntityToDTO(institution);
	}

}

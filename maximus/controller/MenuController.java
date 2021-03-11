package com.example.maximus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.maximus.MaximusApplication;
import com.example.maximus.repository.DeviceRepository;
import com.example.maximus.repository.DiagnosisRepository;
import com.example.maximus.repository.DoctorRepository;
import com.example.maximus.repository.InstitutionRepository;
import com.example.maximus.repository.PatientRepository;

@RestController
public class MenuController {
	MaximusApplication ma = new MaximusApplication();
	
    @GetMapping(value="/")
    public PatientRepository startApp(PatientRepository patientRepository, DoctorRepository doctorRepository, InstitutionRepository institutionRepository, DeviceRepository deviceRepository, DiagnosisRepository diagnosisRepository){
        ma.run(patientRepository, doctorRepository, institutionRepository, deviceRepository, diagnosisRepository);
        return patientRepository;
    }
    
}


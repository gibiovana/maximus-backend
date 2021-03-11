package com.example.maximus.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maximus.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByName(String name);
    
    List<Patient> findByNameContaining(String name);
    
    Patient findByPatientId(Integer patientId);
    
}

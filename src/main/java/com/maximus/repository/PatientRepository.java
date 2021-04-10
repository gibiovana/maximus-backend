package com.maximus.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maximus.model.Institution;
import com.maximus.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByName(String name);
    
    List<Patient> findByNameContaining(String name);
    
    Patient findByPatientId(Integer patientId);
    
    Optional<Patient> findByUsernameAndPassword(String username, String password);
    
    Optional<Patient> findByProntuary(String prontuary);
    
    Optional<Patient> findByInstitution(Institution institution);
    
    List<Patient> findAll();
}

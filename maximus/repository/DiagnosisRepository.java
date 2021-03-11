package com.example.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.maximus.model.Diagnosis;
import com.example.maximus.model.Patient;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    Diagnosis findByPatient(Patient patient);
    
    Diagnosis findByDiagnosisId(Integer idDiagnosis);
}

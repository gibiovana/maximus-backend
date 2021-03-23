package com.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maximus.model.Diagnosis;
import com.maximus.model.Patient;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    Diagnosis findByPatient(Patient patient);
    
    Diagnosis findByDiagnosisId(Integer idDiagnosis);
}

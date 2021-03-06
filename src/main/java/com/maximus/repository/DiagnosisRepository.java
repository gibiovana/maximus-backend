package com.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maximus.model.Diagnosis;
import com.maximus.model.Patient;

import java.util.List;
import java.util.Optional;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer> {

    List<Diagnosis> findByPatient(Patient patient);
    
    Optional<Diagnosis> findByDiagnosisId(Integer idDiagnosis);
}

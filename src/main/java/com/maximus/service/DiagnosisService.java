package com.maximus.service;
import com.maximus.dto.DiagnosisDTO;
import com.maximus.mapper.DiagnosisMapper;
import com.maximus.model.Diagnosis;
import com.maximus.repository.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.maximus.model.Doctor;
import com.maximus.model.Institution;
import com.maximus.model.Patient;
import com.maximus.dto.PatientDTO;
import com.maximus.mapper.PatientMapper;
import com.maximus.repository.DoctorRepository;
import com.maximus.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DiagnosisService {
    @Autowired
    private DiagnosisRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository docRepository;

    public Optional<Diagnosis> findByDiagnosisId(Integer id){
        return this.repository.findByDiagnosisId(id);
    }

    public Optional<Diagnosis> getDiagnosisByPatient(PatientDTO dto){
        Patient selectedPatient = PatientMapper.fromDTOToEntity(dto);
        return this.repository.findByPatient(selectedPatient);
    }

    @Transactional
    public DiagnosisDTO storeDiagnosisData(DiagnosisDTO dto) {
        Diagnosis entity = DiagnosisMapper.fromDTOToEntity(dto);
        entity.setDiagnosisId(dto.getDiagnosisId());
        entity.setDiagnosisDate(dto.getDiagnosisDate());
        entity.setDiagnosisDescription(dto.getDiagnosisDescription());
        entity.setPatient(dto.getPatient());
        entity.setResponsibleDoctor(dto.getAuthor());

        Patient patient = dto.getPatient();
        List<Diagnosis> aux = patient.getDiagnosisList();
        if(aux != null){
            aux.add(entity);
        } else {
            aux = new ArrayList<>();
            aux.add(entity);
        }
        patient.setDiagnosisList(aux);

        Doctor doctor = dto.getAuthor();
        List<Diagnosis> doctorDiagnosisList = doctor.getDiagnosisList();
        if(doctorDiagnosisList != null){
            doctorDiagnosisList.add(entity);
        } else {
            doctorDiagnosisList = new ArrayList<>();
            doctorDiagnosisList.add(entity);
        }
        doctorDiagnosisList.add(entity);
        doctor.setDiagnosisList(doctorDiagnosisList);

        entity = this.repository.save(entity);
        return DiagnosisMapper.fromEntityToDTO(entity);
    }
}

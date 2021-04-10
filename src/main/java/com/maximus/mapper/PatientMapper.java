package com.maximus.mapper;

import com.maximus.model.Patient;
import com.maximus.dto.PatientDTO;

public class PatientMapper {
    public static PatientDTO fromEntityToDTO(Patient entity){
        PatientDTO dto = new PatientDTO();
    	dto.setPatientId(dto.getPatientId());
    	dto.setName(entity.getName());
    	dto.setProntuary(entity.getProntuary());
    	dto.setPathologicalCondition(entity.getPathologicalCondition());
    	dto.setPatientHeight(entity.getPatientHeight());
    	dto.setPatientWeight(entity.getPatientWeight());
    	dto.setBirthdate(entity.getBirthdate());
    	dto.setUsername(entity.getUsername());
    	dto.setPassword(entity.getPassword());
        return dto;
   }
    
    public static Patient fromDTOToEntity(PatientDTO dto) {
    	Patient entity = new Patient();
    	entity.setPatientId(dto.getPatientId());
    	entity.setName(dto.getName());
    	entity.setProntuary(dto.getProntuary());
    	entity.setPathologicalCondition(dto.getPathologicalCondition());
    	entity.setPatientHeight(dto.getPatientHeight());
    	entity.setPatientWeight(dto.getPatientWeight());
    	entity.setBirthdate(dto.getBirthdate());
    	entity.setUsername(dto.getUsername());
    	entity.setPassword(dto.getPassword());
        return entity;
    }
}

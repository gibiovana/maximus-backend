package com.maximus.mapper;

import com.maximus.dto.DiagnosisDTO;
import com.maximus.model.Diagnosis;

public class DiagnosisMapper {
    public static DiagnosisDTO fromEntityToDTO(Diagnosis entity){
        DiagnosisDTO dto = new DiagnosisDTO();
        dto.setDiagnosisId(entity.getDiagnosisId());
        dto.setDiagnosisDate(entity.getDiagnosisDate());
        dto.setDiagnosisDescription(entity.getDiagnosisDescription());
        dto.setPatient(entity.getPatient());
        dto.setAuthor(entity.getAuthor());
        return dto;
    }

    public static Diagnosis fromDTOToEntity(DiagnosisDTO dto) {
        Diagnosis entity = new Diagnosis();
        entity.setDiagnosisId(dto.getDiagnosisId());
        entity.setDiagnosisDate(dto.getDiagnosisDate());
        entity.setDiagnosisDescription(dto.getDiagnosisDescription());
        entity.setPatient(dto.getPatient());
        entity.setResponsibleDoctor(dto.getAuthor());
        return entity;
    }
}

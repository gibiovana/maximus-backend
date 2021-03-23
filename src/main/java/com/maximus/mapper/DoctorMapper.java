package com.maximus.mapper;

import com.maximus.model.Doctor;
import com.maximus.dto.DoctorDTO;

public class DoctorMapper {
    public static DoctorDTO fromEntityToDTO(Doctor entity){
        DoctorDTO dto = new DoctorDTO();
        dto.setDoctorId(entity.getDoctorId());
        dto.setDoctorName(entity.getDoctorName());
        dto.setDoctorEmail(entity.getDoctorEmail());
        dto.setDoctorCRM(entity.getDoctorCRM());
        dto.setPassword(entity.getPassword());
        return dto;
   }
    
    public static Doctor fromDTOToEntity(DoctorDTO dto) {
    	Doctor entity = new Doctor();
    	entity.setDoctorId(dto.getDoctorId());
    	entity.setDoctorName(dto.getDoctorName());
    	entity.setDoctorEmail(dto.getDoctorEmail());
    	entity.setDoctorCRM(dto.getDoctorCRM());
    	entity.setPassword(dto.getPassword());
        return entity;
    }
}

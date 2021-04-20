package com.maximus.mapper;

import com.maximus.dto.InstitutionDTO;
import com.maximus.model.Institution;

public class InstitutionMapper {
  public static InstitutionDTO fromEntityToDTO(Institution entity){
    InstitutionDTO dto = new InstitutionDTO();
    dto.setInstitutionId(entity.getInstitutionId());
    dto.setInstitutionName(entity.getInstitutionName());
    dto.setInstitutionCNES(entity.getCnes());
    dto.setAdminEmail(entity.getAdminEmail());
    dto.setPassword(entity.getPassword());
    return dto;
  }

public static Institution fromDTOToEntity(InstitutionDTO dto) {
  Institution entity = new Institution();
  entity.setInstitutionId(dto.getInstitutionId());
  entity.setInstitutionName(dto.getInstitutionName());
  entity.setInstitutionCNES(dto.getCnes());
  entity.setAdminEmail(dto.getAdminEmail());
  entity.setPassword(dto.getPassword());
  return entity;
  }
}

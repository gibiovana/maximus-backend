package com.maximus.service;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.maximus.model.Institution;
import com.maximus.dto.InstitutionDTO;
import com.maximus.mapper.InstitutionMapper;
import com.maximus.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InstitutionService {
	@Autowired
	private InstitutionRepository repository;
	
	public Optional<Institution> findById(Integer id){
		return this.repository.findByInstitutionId(id);
	}

	public Optional<Institution> getInstitution(String adminEmail, String password){
		return this.repository.findByAdminEmailAndPassword(adminEmail, password);
	}
	
	@Transactional
	public InstitutionDTO storeInstitutionData(InstitutionDTO dto) {
		Institution institution = InstitutionMapper.fromDTOToEntity(dto);
		Optional<Institution> existingAdmin = institution.getInstitutionId() != null ? this.repository.findByCnes(dto.getCnes()) : Optional.ofNullable(null);
		
		if(!existingAdmin.isPresent()) {
			institution.setInstitutionName(dto.getInstitutionName());
			institution.setInstitutionCNES(dto.getCnes());
      		institution.setAdminEmail(dto.getAdminEmail());
			institution.setPassword(dto.getPassword());
		}else{
			new Exception("Usuário já foi registrado.");
		}

		institution = this.repository.save(institution);
		return InstitutionMapper.fromEntityToDTO(institution);
	}
}
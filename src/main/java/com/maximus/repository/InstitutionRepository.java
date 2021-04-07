package com.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.maximus.model.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
	Optional<Institution> findByInstitutionName(String name);
	
	Optional<Institution> findByInstitutionId(Integer institutionId);

	Optional<Institution> findByCnes(String cnes);

	Optional<Institution> findByAdminEmailAndPassword(String adminEmail, String password);

	List<Institution> findAll(); 
}

package com.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.maximus.model.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
	Institution findByInstitutionName(String name);
	
	Institution findByInstitutionId(Integer institutionId);

}

package com.example.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.maximus.model.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
	Institution findByInstitutionName(String name);
	
	Institution findByInstitutionId(Integer institutionId);

}

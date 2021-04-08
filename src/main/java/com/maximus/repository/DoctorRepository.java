package com.maximus.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.maximus.model.Doctor;
import com.maximus.model.Institution;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByDoctorCRM(String crm);
    
    List<Doctor> findByInstitution(Institution institution);
    
    Optional<Doctor> findByDoctorId(Integer doctorId);

    Optional<Doctor> findByDoctorEmailAndPassword(String doctorEmail, String password);

    List<Doctor> findAll();
}

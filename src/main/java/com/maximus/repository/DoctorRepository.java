package com.maximus.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.maximus.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByDoctorCRM(String crm);
    
    Optional<Doctor> findByDoctorId(Integer doctorId);

    Optional<Doctor> findByDoctorEmailAndPassword(String doctorEmail, String password);

    List<Doctor> findAll();
}

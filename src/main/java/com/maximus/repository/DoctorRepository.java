package com.maximus.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.maximus.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findByDoctorName(String name);
    
    Optional<Doctor> findByDoctorId(Integer doctorId);

    List<Doctor> findAll();
}

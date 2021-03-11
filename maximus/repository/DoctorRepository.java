package com.example.maximus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.maximus.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Doctor findByDoctorName(String name);
    
    Doctor findByDoctorId(Integer doctorId);
}

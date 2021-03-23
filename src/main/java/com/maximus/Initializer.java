package com.maximus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.maximus.model.Doctor;
import com.maximus.model.Patient;
import com.maximus.repository.DoctorRepository;

@Component
class Initializer implements CommandLineRunner {

    private final DoctorRepository repository;

    public Initializer(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Sharpay", "Gabriela", "Chad",
                "Troy").forEach(name ->
                repository.save(new Doctor(name))
        );

        Doctor doctor = repository.findByDoctorName("Sharpay");
        repository.save(doctor);

        System.out.println(repository.findAll().toString());
    }
}
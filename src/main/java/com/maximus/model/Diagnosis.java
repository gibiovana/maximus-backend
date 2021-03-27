package com.maximus.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;   

@Entity
public class Diagnosis {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diagnosisId;
    private Date diagnosisDate;
    @Column(length = 1000)
    private String diagnosisDescription;

    @ManyToOne
    private Doctor author;

    @ManyToOne
    private Patient patient;

    public Diagnosis(){}

    public Diagnosis(Integer idDiagnosis, Date diagnosisDate, String diagnosisDescription, Doctor author, Patient patient) {
        this.diagnosisId = idDiagnosis;
        this.diagnosisDate = diagnosisDate;
        this.diagnosisDescription = diagnosisDescription;
        this.author = author;
        this.patient = patient;
    }
    
    public Integer getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Integer diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public Doctor getAuthor() {
        return author;
    }

    public void setResponsibleDoctor(Doctor author) {
        this.author = author;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }   
}
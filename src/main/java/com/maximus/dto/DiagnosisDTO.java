package com.maximus.dto;

import com.maximus.model.Doctor;
import com.maximus.model.Patient;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

public class DiagnosisDTO {
    private Integer diagnosisId;
    private Date diagnosisDate;
    private String diagnosisDescription;
    private Doctor author;
    private Patient patient;

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

    public void setAuthor(Doctor author) {
        this.author = author;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

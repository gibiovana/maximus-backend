package com.maximus.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorId;
	private String doctorName;
	private String doctorEmail;
	private String doctorCRM;
	private String password;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "doctor_patient", joinColumns = @JoinColumn(name = "doctor_patient_id", referencedColumnName = "doctorId"), inverseJoinColumns = @JoinColumn(name = "patient_doctor_id", referencedColumnName = "patientId"))
	private List<Patient> patientList;

	@JsonIgnore
	@ManyToOne
	private Institution institution;

	@JsonIgnore
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
	private List<Diagnosis> diagnosisList;

	public Doctor() {
	}

	public Doctor(Integer doctorId, String doctorName, String doctorCRM, String password, List<Patient> patientList,
			Institution institution, List<Diagnosis> diagnosisList) {
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorCRM = doctorCRM;
		this.password = password;
		this.patientList = patientList;
		this.institution = institution;
		this.diagnosisList = diagnosisList;
	}

	public Doctor(String name) {
		this.doctorName = name;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getDoctorCRM() {
		return doctorCRM;
	}

	public void setDoctorCRM(String doctorCRM) {
		this.doctorCRM = doctorCRM;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	@Override
	public String toString() {
		return doctorName;
	}

	public List<Diagnosis> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(List<Diagnosis> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}
}
package com.maximus.dto;

import java.sql.Date;

import java.util.List;

import com.maximus.model.Doctor;
import com.maximus.model.Institution;

public class PatientDTO {
	private Integer patientId;
	private String name;
	private String prontuary;
	private String pathologicalCondition;
	private String patientHeight;
	private String patientWeight;
	private Date birthdate;
	private String username;
	private String password;
	private Institution institution;
	private List<Doctor> doctorsAssigned;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Doctor> getDoctorsAssigned() {
		return doctorsAssigned;
	}

	public void setDoctorsAssigned(List<Doctor> doctorsAssigned) {
		this.doctorsAssigned = doctorsAssigned;
	}

	public String getProntuary() {
		return prontuary;
	}

	public void setProntuary(String prontuary) {
		this.prontuary = prontuary;
	}

	public String getPathologicalCondition() {
		return pathologicalCondition;
	}

	public void setPathologicalCondition(String pathologicalCondition) {
		this.pathologicalCondition = pathologicalCondition;
	}

	public String getPatientHeight() {
		return patientHeight;
	}

	public void setPatientHeight(String patientHeight) {
		this.patientHeight = patientHeight;
	}

	public String getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
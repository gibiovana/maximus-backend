package com.maximus.dto;

import java.util.List;

import com.maximus.model.Diagnosis;
import com.maximus.model.Institution;
import com.maximus.model.Patient;

public class DoctorDTO{
	private Integer doctorId;
	private String doctorName;
	private String doctorEmail;
	private String doctorCRM;
	private String password;
	private List<Patient> patientList;
	private Institution institution;
	private List<Diagnosis> diagnosisList;

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
	
	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public List<Diagnosis> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(List<Diagnosis> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}
}
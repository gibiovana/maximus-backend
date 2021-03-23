package com.maximus.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.ForeignKey;

import java.util.List;

@Entity
public class Patient {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientId;
	private String name;
	private String prontuary;
	private String pathologicalCondition;
	private String patientHeight;
	private String patientWeight;
	private String patientAge;

	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
	private List<Diagnosis> diagnosisList;

	@ManyToMany(mappedBy="patientList")
	private List<Doctor> doctorsAssigned;

	@OneToOne
	@JoinColumn(name = "device_id", referencedColumnName="deviceId", foreignKey = @ForeignKey(name="device_id"))
	private Device device;

	public Patient(Integer patientId, String name, List<Doctor> doctorsAssigned, String prontuary, String pathologicalCondition, String patientHeight, String patientWeight, String patientAge, List<Diagnosis> diagnosisList, Device device) {
		this.patientId = patientId;
		this.name = name;
		this.doctorsAssigned = doctorsAssigned;
		this.prontuary = prontuary;
		this.pathologicalCondition = pathologicalCondition;
		this.patientHeight = patientHeight;
		this.patientWeight = patientWeight;
		this.patientAge = patientAge;
		this.diagnosisList = diagnosisList;
		this.device = device;
	}

	public Patient() {
	}

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

	public List<Doctor> getAssignedDoctors() {
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

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public List<Diagnosis> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(List<Diagnosis> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
package com.maximus.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Table(name = "Institution")
public class Institution {
  @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer institutionId;
	private String institutionName;
  	private String cnes; 
	private String adminEmail;
	private String password;
	
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "institution")
	private List<Patient> patientList;

	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "institution")
    private List<Doctor> doctors;

	public Institution() {
	}

	public Institution(Integer institutionId, String name, String cnes, String adminEmail, String password, List<Doctor> doctors, List<Patient> patientList) {
		this.institutionId = institutionId;
		this.institutionName = name;
    	this.cnes = cnes;
		this.adminEmail = adminEmail;
		this.password = password;
		this.doctors = doctors;
		this.patientList = patientList;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public void setInstitutionName(String name) {
		this.institutionName = name;
	}

    public String getCnes() {
		return cnes;
	}

	public void setInstitutionCNES(String cnes) {
		this.cnes = cnes;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
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
	
	
}
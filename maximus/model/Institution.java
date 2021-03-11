package com.example.maximus.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
public class Institution {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer institutionId;
	private String institutionName;
    private String cnes; 

	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "institution")
    private List<Doctor> doctors;

	public Institution() {
	}

	public Institution(Integer institutionId, String name, String cnes, List<Doctor> doctors) {
		this.institutionId = institutionId;
		this.institutionName = name;
        this.cnes = cnes;
		this.doctors = doctors;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getName() {
		return institutionName;
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
}
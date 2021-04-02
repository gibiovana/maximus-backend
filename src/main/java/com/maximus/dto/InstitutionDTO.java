package com.maximus.dto;

public class InstitutionDTO {
	private Integer institutionId;
	private String institutionName;
  private String cnes; 
	private String adminEmail;
	private String password;

	public InstitutionDTO() {
	}

	public InstitutionDTO(Integer institutionId, String name, String cnes, String adminEmail, String password) {
		this.institutionId = institutionId;
		this.institutionName = name;
    this.cnes = cnes;
		this.adminEmail = adminEmail;
		this.password = password;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getInstitutionName() {
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
}
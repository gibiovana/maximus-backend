public class DoctorDTO{
	private Integer doctorId;
	private String doctorName;
	private String doctorEmail;
	private String doctorCRM;
	private String password;

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
	
}
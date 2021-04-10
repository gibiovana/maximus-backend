/*package com.maximus.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class DoctorPatient {
	
	@EmbeddedId
	DoctorPatientKey id;
	
	@ManyToOne
	@MapsId("doctorPatientId")
	@JoinColumn(name="doctor_patient_id")
	Patient patient;
	
	@ManyToOne
	@MapsId("patientDoctorId")
	@JoinColumn(name="patient_doctor_id")
	Doctor doctor;

}
*/
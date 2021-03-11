package com.example.maximus;

import java.awt.BorderLayout;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import com.example.maximus.repository.DeviceRepository;
import com.example.maximus.repository.DiagnosisRepository;
import com.example.maximus.repository.DoctorRepository;
import com.example.maximus.repository.InstitutionRepository;
import com.example.maximus.repository.PatientRepository;

@SpringBootApplication
public class MaximusApplication {
	private JButton btPatient = new JButton("Patient");
	private JButton btInstitution = new JButton("Institution");
	private JButton btDoctor = new JButton("Doctor");
	private JButton btDiagnosis = new JButton("Diagnosis");
	private JButton btDevice = new JButton("Device");
	JFrame window = new JFrame("Maximus");

	public static void main(String[] args) {
		new SpringApplicationBuilder(MaximusApplication.class)
				.headless(false)
				.run(args);
	}
	
	private JFrame startUI() {
		window.setSize(480, 320);
		
		System.out.print(window);
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 2));

		panel.add(btPatient);
		panel.add(btInstitution);
		panel.add(btDoctor);
		panel.add(btDiagnosis);
		panel.add(btDevice);
		

		window.getContentPane().setLayout(new GridLayout());

		window.getContentPane().add(panel, BorderLayout.CENTER);
		
		window.revalidate();

		return window;
	}

	@Bean
	@Lazy
	@Primary
	public PatientRepository run(PatientRepository pr, DoctorRepository dr, InstitutionRepository ir, DeviceRepository der, DiagnosisRepository diagR){
		startUI();
		
		btPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PatientUI patientFrame = new PatientUI();
				patientFrame.runPatientRepository(pr);
				window.setVisible(false);
			}
		});
		
		btDoctor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DoctorUI doctorFrame = new DoctorUI();
				doctorFrame.runDoctorRepository(dr, pr);
				window.setVisible(false);
			}
		});
		
		btInstitution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstitutionUI institutionFrame = new InstitutionUI();
				institutionFrame.runInstitutionRepository(ir, dr);
				window.setVisible(false);
			}
		});
		
		btDevice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeviceUI deviceFrame = new DeviceUI();
				deviceFrame.runDeviceRepository(der, pr);
				window.setVisible(false);
			}
		});

		btDiagnosis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DiagnosisUI diagFrame = new DiagnosisUI();
				diagFrame.runDiagnosisRepository(diagR, pr, dr);
				window.setVisible(false);
			}
		});

		return pr;
	}

}

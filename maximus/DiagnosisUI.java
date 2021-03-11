package com.example.maximus;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.maximus.model.Diagnosis;
import com.example.maximus.model.Doctor;
import com.example.maximus.model.Patient;
import com.example.maximus.repository.DiagnosisRepository;
import com.example.maximus.repository.DoctorRepository;
import com.example.maximus.repository.PatientRepository;

@SpringBootApplication
public class DiagnosisUI {
	private JTextField txtDescription = new JTextField();
	private JButton btCadastrar = new JButton("Register new");
	private JButton btSalvar = new JButton("Save");
	private JButton btSalvarEdit = new JButton("Save");
	private JButton btListar = new JButton("List all");
	private Patient selectedPatient;
	private Doctor selectedDoctor;
	private JButton btEditar;
	private JButton btExcluir;

	JFrame janela = new JFrame("Diagnosis");
	PatientRepository dr;
	DoctorRepository docR;
	
	Optional<Diagnosis> currentDiagnosis;
	Patient currentPatients;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DiagnosisUI.class);
		builder.headless(false);
		builder.run(args);
	}

	private JFrame mainUI() {
		janela.setSize(480, 320);
		janela.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(0, 2));

		buttonsPanel.add(btCadastrar);
		buttonsPanel.add(btListar);

		janela.getContentPane().setLayout(new BorderLayout());
		janela.getContentPane().add(buttonsPanel, BorderLayout.CENTER);
		janela.revalidate();

		return janela;
	}

	private JFrame createRegisterWindow(PatientRepository dr, DoctorRepository docR) {
		JFrame registerWindow = new JFrame("Register Diagnosis");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblDesc = new JLabel("Description:");
		JLabel lblPatients = new JLabel("Please, select your Patient:");
		JLabel lblDoc = new JLabel("Please, select the author");

		List<Patient> patients = new ArrayList<Patient>();
		patients = dr.findAll();

		painel.add(lblDesc);
		painel.add(txtDescription);

		painel.add(lblPatients);

		JPanel patientPanel = new JPanel();
		JComboBox<String> cb = new JComboBox<>();
		
		for (Patient patient : patients) {
			cb.addItem(patient.getName());
		}
		
		patientPanel.add(cb);
		painel.add(patientPanel);
		
		String selectedItem = (String) cb.getSelectedItem();
		selectedPatient = dr.findByName(selectedItem);
		
		painel.add(lblDoc);
		JPanel doctorPanel = new JPanel();
		JComboBox<String> docCb = new JComboBox<>();
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors = docR.findAll();
		
		for (Doctor doc : doctors) {
			docCb.addItem(doc.getDoctorName());
		}
		
		doctorPanel.add(docCb);
		
		String selected = (String) docCb.getSelectedItem();
		selectedDoctor = docR.findByDoctorName(selected);
		
		painel.add(doctorPanel);
		painel.add(btSalvar);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);
		registerWindow.revalidate();

		return registerWindow;
	}

	private JFrame createEditDiagnosisWindow(DiagnosisRepository ir, PatientRepository dr, Integer diagnosisId, Date diagDate, String description, Patient patient, Doctor author) {
		JFrame registerWindow = new JFrame("Register Diagnosis");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblDesc = new JLabel("Description:");
		JLabel lblPatients = new JLabel("Your Patients:");

		painel.add(lblDesc);
		txtDescription.setText(description);
		painel.add(txtDescription);
		
		currentDiagnosis = ir.findById(diagnosisId);
		currentPatients = currentDiagnosis.get().getPatient();
		
		if(selectedPatient != null) {
			painel.add(lblPatients);
			JPanel patientPanel = new JPanel();
			JLabel lblPatient = new JLabel(selectedPatient.getName());
			patientPanel.add(lblPatient);
			painel.add(patientPanel);
		}
		painel.add(btSalvarEdit);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);

		btSalvarEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String description = txtDescription.getText();
				Diagnosis d = ir.findByDiagnosisId(diagnosisId);
				selectedPatient = currentDiagnosis.get().getPatient();

				d.setDiagnosisDate(new Date());
				d.setDiagnosisDescription(description);

				d.setPatient(selectedPatient);
				d.setResponsibleDoctor(selectedDoctor);

				ir.save(d);
				
				Patient p = dr.findByPatientId(selectedPatient.getPatientId());
				List<Diagnosis> diagList = new ArrayList<Diagnosis>();
				diagList.add(d);
				p.setDiagnosisList(diagList);
				dr.save(p);
		
				JOptionPane.showMessageDialog(null, "Updated Diagnosis");
			}
		});
		
		registerWindow.revalidate();
		return registerWindow;
	}

	public DiagnosisRepository runDiagnosisRepository(DiagnosisRepository ir, PatientRepository dr, DoctorRepository docR) {
		mainUI();

		btListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Diagnosis> diagnosisList = new ArrayList<Diagnosis>();
				diagnosisList = ir.findAll();

				JFrame listWindow = new JFrame("Register Diagnosis");
				listWindow.setSize(300, 320);
				listWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				listWindow.setVisible(true);
				JPanel painel = new JPanel();
				painel.setLayout(new FlowLayout(FlowLayout.TRAILING));

				for (Diagnosis diagnosis : diagnosisList) {
					JLabel lblDiagnosis = new JLabel("PATIENT:" + diagnosis.getPatient() + " | DATE: " + diagnosis.getDiagnosisDate());
					painel.add(lblDiagnosis);
					btEditar = new JButton("Edit");
					btExcluir = new JButton("Delete");
					painel.add(btEditar);
					painel.add(btExcluir);
					listWindow.getContentPane().add(painel, BorderLayout.CENTER);

					btExcluir.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							ir.deleteById(diagnosis.getDiagnosisId());

							JOptionPane.showMessageDialog(null, "Deleted Diagnosis");
							SwingUtilities.updateComponentTreeUI(janela);
							janela.invalidate();
							janela.validate();
							janela.repaint();
						}

					});

					btEditar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							createEditDiagnosisWindow(ir, dr, diagnosis.getDiagnosisId(), diagnosis.getDiagnosisDate(),
									diagnosis.getDiagnosisDescription(), diagnosis.getPatient(), diagnosis.getAuthor());
						}

					});

				}
			}

		});

		btCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createRegisterWindow(dr, docR);
			}
		});

		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String description = txtDescription.getText();
				
				Diagnosis d = new Diagnosis();

				d.setDiagnosisDate(new Date());
				d.setDiagnosisDescription(description);

				d.setPatient(selectedPatient);
				d.setResponsibleDoctor(selectedDoctor);

				ir.save(d);
				
				Patient p = dr.findByPatientId(selectedPatient.getPatientId());
				List<Diagnosis> diagList = new ArrayList<Diagnosis>();
				diagList.add(d);
				p.setDiagnosisList(diagList);
				dr.save(p);
				
				JOptionPane.showMessageDialog(null, "Registered Diagnosis");
			}

		});

		return ir;
	}

}

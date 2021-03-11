package com.example.maximus;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.maximus.model.Doctor;
import com.example.maximus.model.Patient;
import com.example.maximus.repository.DoctorRepository;
import com.example.maximus.repository.PatientRepository;

@SpringBootApplication
public class DoctorUI {
	private JTextField txtDoctorName = new JTextField();
	private JTextField txtDoctorCRM = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
	private JCheckBox checkbox;
	private JButton btCadastrar = new JButton("Register new");
	private JButton btSalvar = new JButton("Save");
	private JButton btSalvarEdit = new JButton("Save");
	private JButton btListar = new JButton("List all");

	private JButton btEditar;
	private JButton btExcluir;

	JFrame janela = new JFrame("Doctor");
	PatientRepository pr;
	List<Patient> selectedPatients = null;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DoctorUI.class);
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

	private JFrame createRegisterWindow(PatientRepository pr) {
		JFrame registerWindow = new JFrame("Register Doctor");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblName = new JLabel("Name");
		JLabel lblCRM = new JLabel("CRM:");
		JLabel lblPassword = new JLabel("Password:");
		JLabel lblPatients = new JLabel("Please, select your patients:");

		List<Patient> patients = new ArrayList<Patient>();
		patients = pr.findAll();

		painel.add(lblName);
		painel.add(txtDoctorName);

		painel.add(lblCRM);
		painel.add(txtDoctorCRM);

		painel.add(lblPassword);
		painel.add(txtPassword);

		painel.add(lblPatients);
		selectedPatients = new ArrayList<Patient>();

		JPanel patientPanel = new JPanel();
		for (Patient patient : patients) {
			checkbox = new JCheckBox(patient.getName());
			checkbox.addItemListener(e -> mudouStatus(e, pr, selectedPatients));
			patientPanel.add(checkbox);
		}

		painel.add(patientPanel);
		painel.add(btSalvar);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);
		registerWindow.revalidate();

		return registerWindow;
	}

	private JFrame createEditPatientWindow(DoctorRepository dr, Integer doctorId, String doctorName, String CRM,
			String password, List<Patient> patients) {
		JFrame registerWindow = new JFrame("Register Patient");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblName = new JLabel("Name");
		JLabel lblCRM = new JLabel("CRM:");
		JLabel lblPassword = new JLabel("Password:");
		JLabel lblPatients = new JLabel("Your patients:");

		painel.add(lblName);
		txtDoctorName.setText(doctorName);
		painel.add(txtDoctorName);

		painel.add(lblCRM);
		txtDoctorCRM.setText(CRM);
		painel.add(txtDoctorCRM);

		painel.add(lblPassword);
		txtPassword.setText(password);
		painel.add(txtPassword);

		painel.add(lblPatients);

		JPanel patientPanel = new JPanel();
		for (Patient patient : patients) {
			checkbox = new JCheckBox(patient.getName());
			checkbox.addItemListener(e -> mudouStatus(e, pr, selectedPatients));
			patientPanel.add(checkbox);
		}

		painel.add(patientPanel);
		painel.add(btSalvarEdit);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);

		btSalvarEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtDoctorName.getText();
				String crm = txtDoctorCRM.getText();
				String password = txtPassword.getPassword().toString();

				Doctor d = dr.findByDoctorId(doctorId);

				d.setDoctorName(name);
				d.setDoctorCRM(crm);
				d.setPassword(password);
				d.setPatientList(selectedPatients);

				dr.save(d);
				
				for(Patient pat : selectedPatients) {
					List<Doctor> assignedDoc = new ArrayList<Doctor>();
					pr.findByPatientId(pat.getPatientId());
					assignedDoc.add(d);
					pat.setDoctorsAssigned(assignedDoc);
					pr.save(pat);
				}


				JOptionPane.showMessageDialog(null, "Updated doctor");
			}
		});
		
		registerWindow.revalidate();
		return registerWindow;
	}

	public DoctorRepository runDoctorRepository(DoctorRepository dr, PatientRepository pr) {
		mainUI();

		btListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Doctor> doctors = new ArrayList<Doctor>();
				doctors = dr.findAll();

				JFrame listWindow = new JFrame("Register Doctor");
				listWindow.setSize(300, 320);
				listWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				listWindow.setVisible(true);
				JPanel painel = new JPanel();
				painel.setLayout(new FlowLayout(FlowLayout.TRAILING));

				for (Doctor doctor : doctors) {
					JLabel lblDoctor = new JLabel("NOME: " + doctor.getDoctorName() + " | ID: " + doctor.getDoctorId());
					painel.add(lblDoctor);
					btEditar = new JButton("Edit");
					btExcluir = new JButton("Delete");
					painel.add(btEditar);
					painel.add(btExcluir);
					listWindow.getContentPane().add(painel, BorderLayout.CENTER);

					btExcluir.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dr.deleteById(doctor.getDoctorId());

							JOptionPane.showMessageDialog(null, "Deleted doctor");
							SwingUtilities.updateComponentTreeUI(janela);
							janela.invalidate();
							janela.validate();
							janela.repaint();
						}

					});

					btEditar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							createEditPatientWindow(dr, doctor.getDoctorId(), doctor.getDoctorName(),
									doctor.getDoctorCRM(), doctor.getPassword(), doctor.getPatientList());
						}

					});

				}
			}

		});

		btCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createRegisterWindow(pr);
			}
		});

		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtDoctorName.getText();
				String crm = txtDoctorCRM.getText();
				String password = txtPassword.getPassword().toString();

				Doctor d = new Doctor();

				d.setDoctorName(name);
				d.setDoctorCRM(crm);
				d.setPassword(password);
				d.setPatientList(selectedPatients);
				dr.save(d);
				
				for(Patient pat : selectedPatients) {
					List<Doctor> assignedDoc = new ArrayList<Doctor>();
					pr.findByPatientId(pat.getPatientId());
					assignedDoc.add(d);
					pat.setDoctorsAssigned(assignedDoc);
					pr.save(pat);
				}
				
				dr.save(d);
				
				JOptionPane.showMessageDialog(null, "Registered doctor");
			}

		});

		return dr;
	}

	private void mudouStatus(ItemEvent e, PatientRepository pr, List<Patient> selectedPatients) {
		AbstractButton abstractButton = (AbstractButton) e.getSource();
		String patient = abstractButton.getText();
		Patient foundPat = pr.findByName(patient);
		selectedPatients.add(foundPat);
	}
}

package com.example.maximus;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.maximus.model.Patient;
import com.example.maximus.repository.PatientRepository;

@SpringBootApplication
public class PatientUI {
	private JTextField txtPatientName = new JTextField();
	private JTextField txtProntuary = new JTextField();
	private JTextField txtPathologicalCondition = new JTextField();
	private JTextField txtPatientHeight = new JTextField();
	private JTextField txtPatientWeight = new JTextField();
	private JTextField txtPatientAge = new JTextField();

	private JButton btCadastrar = new JButton("Register new");
	private JButton btSalvar = new JButton("Save");
	private JButton btSalvarEdit = new JButton("Save");
	private JButton btListar = new JButton("List all");

	private JButton btEditar;
	private JButton btExcluir;
	JFrame janela = new JFrame("Patient");
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PatientUI.class);
		builder.headless(false);
		builder.run(args);
	}

	private JFrame mainUI(PatientRepository pr) {
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

	private JFrame createRegisterWindow() {
		JFrame registerWindow = new JFrame("Register Patient");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblName = new JLabel("Patient name:");
		JLabel lblProntuary = new JLabel("Prontuary:");
		JLabel lblPathologic = new JLabel("Pathological Condition:");
		JLabel lblHeight = new JLabel("Height:");
		JLabel lblWeight = new JLabel("Weight:");
		JLabel lblAge = new JLabel("Age:");

		painel.add(lblName);
		painel.add(txtPatientName);

		painel.add(lblProntuary);
		painel.add(txtProntuary);

		painel.add(lblPathologic);
		painel.add(txtPathologicalCondition);

		painel.add(lblHeight);
		painel.add(txtPatientHeight);

		painel.add(lblWeight);
		painel.add(txtPatientWeight);

		painel.add(lblAge);
		painel.add(txtPatientAge);

		painel.add(btSalvar);

		registerWindow.getContentPane().setLayout(new BorderLayout());

		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);

		registerWindow.revalidate();

		return registerWindow;
	}

	private JFrame createEditPatientWindow(PatientRepository pr, Integer patientId, String patientName, 
			String prontuary, String patCond, String height,String weight, String age) {
		JFrame registerWindow = new JFrame("Register Patient");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblName = new JLabel("Patient name:");
		JLabel lblProntuary = new JLabel("Prontuary:");
		JLabel lblPathologic = new JLabel("Pathological Condition:");
		JLabel lblHeight = new JLabel("Height:");
		JLabel lblWeight = new JLabel("Weight:");
		JLabel lblAge = new JLabel("Age:");

		painel.add(lblName);
		txtPatientName.setText(patientName);
		painel.add(txtPatientName);

		painel.add(lblProntuary);
		txtProntuary.setText(prontuary);
		painel.add(txtProntuary);

		painel.add(lblPathologic);
		txtPathologicalCondition.setText(patCond);
		painel.add(txtPathologicalCondition);

		painel.add(lblHeight);
		txtPatientHeight.setText(height);
		painel.add(txtPatientHeight);

		painel.add(lblWeight);
		txtPatientWeight.setText(weight);
		painel.add(txtPatientWeight);

		painel.add(lblAge);
		txtPatientAge.setText(age);
		painel.add(txtPatientAge);

		painel.add(btSalvarEdit);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);

		btSalvarEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtPatientName.getText();
				String prontuary = txtProntuary.getText();
				String pathologicalCondition = txtPathologicalCondition.getText();
				String patientHeight = txtPatientHeight.getText();
				String patientWeight = txtPatientWeight.getText();
				String patientAge = txtPatientAge.getText();

				Patient p = pr.findByPatientId(patientId);

				p.setName(name);
				p.setProntuary(prontuary);
				p.setPathologicalCondition(pathologicalCondition);
				p.setPatientHeight(patientHeight);
				p.setPatientWeight(patientWeight);
				p.setPatientAge(patientAge);

				pr.save(p);

				JOptionPane.showMessageDialog(null, "Updated patient");
			}

	});
		registerWindow.revalidate();
		
		return registerWindow;
	}

	public PatientRepository runPatientRepository(PatientRepository pr) {
		mainUI(pr);

		btListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Patient> patients = new ArrayList<Patient>();
				patients = pr.findAll();
				
				JFrame listWindow = new JFrame("Register Patient");
				listWindow.setSize(300, 320);
				listWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				listWindow.setVisible(true);
				JPanel painel = new JPanel();
				painel.setLayout(new FlowLayout(FlowLayout.TRAILING));
				
				for (Patient patient : patients) {
					JLabel lblPatient = new JLabel("NOME: " + patient.getName() + " | ID: " + patient.getPatientId());
					painel.add(lblPatient);
					btEditar = new JButton("Edit");
					btExcluir = new JButton("Delete");
					painel.add(btEditar);
					painel.add(btExcluir);
					listWindow.getContentPane().add(painel, BorderLayout.CENTER);

					btExcluir.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							pr.deleteById(patient.getPatientId());

							JOptionPane.showMessageDialog(null, "Deleted patient");
							listWindow.revalidate();
						}

					});

					btEditar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							createEditPatientWindow(pr, patient.getPatientId(), patient.getName(), patient.getProntuary(),
									patient.getPathologicalCondition(), patient.getPatientHeight(), patient.getPatientWeight(),
									patient.getPatientAge());
							listWindow.revalidate();
						}

					});

				}
			}

		});

		btCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createRegisterWindow();
			}
		});

		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtPatientName.getText();
				String prontuary = txtProntuary.getText();
				String pathologicalCondition = txtPathologicalCondition.getText();
				String patientHeight = txtPatientHeight.getText();
				String patientWeight = txtPatientWeight.getText();
				String patientAge = txtPatientAge.getText();

				Patient p = new Patient();

				p.setName(name);
				p.setProntuary(prontuary);
				p.setPathologicalCondition(pathologicalCondition);
				p.setPatientHeight(patientHeight);
				p.setPatientWeight(patientWeight);
				p.setPatientAge(patientAge);

				pr.save(p);

				JOptionPane.showMessageDialog(null, "Registered patient");
			}

		});
		
		return pr;
	}
}

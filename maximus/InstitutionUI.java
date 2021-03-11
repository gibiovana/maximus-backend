package com.example.maximus;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.maximus.model.Institution;
import com.example.maximus.model.Doctor;
import com.example.maximus.repository.InstitutionRepository;
import com.example.maximus.repository.DoctorRepository;

@SpringBootApplication
public class InstitutionUI {
	private JTextField txtInstitutionName = new JTextField();
	private JTextField txtInstitutionCNES = new JTextField();
	private JCheckBox checkbox;
	private JButton btCadastrar = new JButton("Register new");
	private JButton btSalvar = new JButton("Save");
	private JButton btSalvarEdit = new JButton("Save");
	private JButton btListar = new JButton("List all");

	private JButton btEditar;
	private JButton btExcluir;

	JFrame janela = new JFrame("Institution");
	DoctorRepository dr;
	
	Optional<Institution> currentInstitution;
	List<Doctor> currentDoctors;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(InstitutionUI.class);
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

	private JFrame createRegisterWindow(DoctorRepository dr) {
		JFrame registerWindow = new JFrame("Register Institution");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblName = new JLabel("Name");
		JLabel lblCNES = new JLabel("CNES:");
		JLabel lblDoctors = new JLabel("Please, select your Doctors:");

		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors = dr.findAll();

		painel.add(lblName);
		painel.add(txtInstitutionName);

		painel.add(lblCNES);
		painel.add(txtInstitutionCNES);

		painel.add(lblDoctors);
		currentDoctors = new ArrayList<Doctor>();

		JPanel doctorPanel = new JPanel();
		for (Doctor doctor : doctors) {
			checkbox = new JCheckBox(doctor.getDoctorName());
			checkbox.addItemListener(e -> mudouStatus(e, dr));
			doctorPanel.add(checkbox);
		}

		painel.add(doctorPanel);
		painel.add(btSalvar);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);
		registerWindow.revalidate();

		return registerWindow;
	}

	private JFrame createEditInstitutionWindow(InstitutionRepository ir, DoctorRepository dr, Integer InstitutionId, String InstitutionName, String CNES, List<Doctor> Doctors) {
		JFrame registerWindow = new JFrame("Register institution");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblName = new JLabel("Name");
		JLabel lblCNES = new JLabel("CNES:");
		JLabel lblDoctors = new JLabel("Your Doctors:");

		painel.add(lblName);
		txtInstitutionName.setText(InstitutionName);
		painel.add(txtInstitutionName);

		painel.add(lblCNES);
		txtInstitutionCNES.setText(CNES);
		painel.add(txtInstitutionCNES);
		
		currentInstitution = ir.findById(InstitutionId);
		currentDoctors = currentInstitution.get().getDoctors();
		
		if(currentDoctors != null) {
			painel.add(lblDoctors);
			JPanel doctorPanel = new JPanel();
			for (Doctor doctor : currentDoctors) {
				JLabel lblDoctor = new JLabel(doctor.getDoctorName());
				doctorPanel.add(lblDoctor);
			}
			painel.add(doctorPanel);
		}
		painel.add(btSalvarEdit);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);

		btSalvarEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtInstitutionName.getText();
				String CNES = txtInstitutionCNES.getText();
				Institution d = ir.findByInstitutionId(InstitutionId);
				currentDoctors = currentInstitution.get().getDoctors();

				d.setInstitutionName(name);
				d.setInstitutionCNES(CNES);
				d.setDoctors(currentDoctors);
				
				ir.save(d);
				
				for(Doctor doctor : currentDoctors) {
					dr.findByDoctorId(doctor.getDoctorId());
					doctor.setInstitution(d);
					dr.save(doctor);
				}


				JOptionPane.showMessageDialog(null, "Updated Institution");
			}
		});
		
		registerWindow.revalidate();
		return registerWindow;
	}

	public InstitutionRepository runInstitutionRepository(InstitutionRepository ir, DoctorRepository dr) {
		mainUI();

		btListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Institution> Institutions = new ArrayList<Institution>();
				Institutions = ir.findAll();

				JFrame listWindow = new JFrame("Register Institution");
				listWindow.setSize(300, 320);
				listWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				listWindow.setVisible(true);
				JPanel painel = new JPanel();
				painel.setLayout(new FlowLayout(FlowLayout.TRAILING));

				for (Institution Institution : Institutions) {
					JLabel lblInstitution = new JLabel("NOME: " + Institution.getName() + " | ID: " + Institution.getInstitutionId());
					painel.add(lblInstitution);
					btEditar = new JButton("Edit");
					btExcluir = new JButton("Delete");
					painel.add(btEditar);
					painel.add(btExcluir);
					listWindow.getContentPane().add(painel, BorderLayout.CENTER);

					btExcluir.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							ir.deleteById(Institution.getInstitutionId());

							JOptionPane.showMessageDialog(null, "Deleted Institution");
							SwingUtilities.updateComponentTreeUI(janela);
							janela.invalidate();
							janela.validate();
							janela.repaint();
						}

					});

					btEditar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							createEditInstitutionWindow(ir, dr, Institution.getInstitutionId(), Institution.getName(),
									Institution.getCnes(), Institution.getDoctors());
						}

					});

				}
			}

		});

		btCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createRegisterWindow(dr);
			}
		});

		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtInstitutionName.getText();
				String CNES = txtInstitutionCNES.getText();
				
				Institution d = new Institution();

				d.setInstitutionName(name);
				d.setInstitutionCNES(CNES);
				d.setDoctors(currentDoctors);
				
				ir.save(d);

				for(Doctor doctor : currentDoctors) {
					dr.findByDoctorId(doctor.getDoctorId());
					doctor.setInstitution(d);
					dr.save(doctor);
				}

				JOptionPane.showMessageDialog(null, "Registered Institution");
			}

		});

		return ir;
	}

	private void mudouStatus(ItemEvent e, DoctorRepository dr) {
		AbstractButton abstractButton = (AbstractButton) e.getSource();
		String doctor = abstractButton.getText();
		Doctor foundPat = dr.findByDoctorName(doctor);
		currentDoctors.add(foundPat);
		System.out.println(currentDoctors);
	}
}

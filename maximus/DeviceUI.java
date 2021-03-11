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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.maximus.model.Device;
import com.example.maximus.model.Patient;
import com.example.maximus.repository.DeviceRepository;
import com.example.maximus.repository.PatientRepository;

@SpringBootApplication
public class DeviceUI {
	private JTextField txtOperatingSystem = new JTextField();
	private JTextField txtModel= new JTextField();
	private JButton btCadastrar = new JButton("Register new");
	private JButton btSalvar = new JButton("Save");
	private JButton btSalvarEdit = new JButton("Save");
	private JButton btListar = new JButton("List all");
	private Patient selectedPatient;
	private JButton btEditar;
	private JButton btExcluir;

	JFrame janela = new JFrame("Device");
	PatientRepository dr;
	
	Optional<Device> currentDevice;
	Patient currentPatients;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(DeviceUI.class);
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

	private JFrame createRegisterWindow(PatientRepository dr) {
		JFrame registerWindow = new JFrame("Register Device");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblOS = new JLabel("OperatingSystem");
		JLabel lblModel = new JLabel("Model:");
		JLabel lblPatients = new JLabel("Please, select your Patient:");

		List<Patient> patients = new ArrayList<Patient>();
		patients = dr.findAll();

		painel.add(lblOS);
		painel.add(txtOperatingSystem);

		painel.add(lblModel);
		painel.add(txtModel);

		painel.add(lblPatients);

		JPanel patientPanel = new JPanel();
		JComboBox<String> cb = new JComboBox<>();
		
		for (Patient patient : patients) {
			cb.addItem(patient.getName());
		}
		
		painel.add(cb);
		
		String selectedItem = (String) cb.getSelectedItem();
		selectedPatient = dr.findByName(selectedItem);

		painel.add(patientPanel);
		painel.add(btSalvar);

		registerWindow.getContentPane().setLayout(new BorderLayout());
		registerWindow.getContentPane().add(painel, BorderLayout.CENTER);
		registerWindow.revalidate();

		return registerWindow;
	}

	private JFrame createEditDeviceWindow(DeviceRepository ir, PatientRepository dr, Integer deviceId, String operatingSystem, String model, Patient owner) {
		JFrame registerWindow = new JFrame("Register Device");
		registerWindow.setSize(480, 320);
		registerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		registerWindow.setVisible(true);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(0, 1));

		JLabel lblOS = new JLabel("Operating System");
		JLabel lblModel = new JLabel("model:");
		JLabel lblPatients = new JLabel("Your Patients:");

		painel.add(lblOS);
		txtOperatingSystem.setText(operatingSystem);
		painel.add(txtOperatingSystem);

		painel.add(lblModel);
		txtModel.setText(model);
		painel.add(txtModel);
		
		currentDevice = ir.findById(deviceId);
		currentPatients = currentDevice.get().getOwner();
		
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
				String name = txtOperatingSystem.getText();
				String model = txtModel.getText();
				Device d = ir.findByDeviceId(deviceId);
				selectedPatient = currentDevice.get().getOwner();

				d.setOperatingSystem(name);
				d.setModel(model);
				d.setOwner(selectedPatient);
				
				ir.save(d);
				
				Patient p = dr.findByPatientId(selectedPatient.getPatientId());
				p.setDevice(d);
				dr.save(p);
				
				JOptionPane.showMessageDialog(null, "Updated Device");
			}
		});
		
		registerWindow.revalidate();
		return registerWindow;
	}

	public DeviceRepository runDeviceRepository(DeviceRepository ir, PatientRepository dr) {
		mainUI();

		btListar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Device> Devices = new ArrayList<Device>();
				Devices = ir.findAll();

				JFrame listWindow = new JFrame("Register Device");
				listWindow.setSize(300, 320);
				listWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				listWindow.setVisible(true);
				JPanel painel = new JPanel();
				painel.setLayout(new FlowLayout(FlowLayout.TRAILING));

				for (Device Device : Devices) {
					JLabel lblDevice = new JLabel("NOME: " + Device.getOperatingSystem() + " | ID: " + Device.getDeviceId());
					painel.add(lblDevice);
					btEditar = new JButton("Edit");
					btExcluir = new JButton("Delete");
					painel.add(btEditar);
					painel.add(btExcluir);
					listWindow.getContentPane().add(painel, BorderLayout.CENTER);

					btExcluir.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							ir.deleteById(Device.getDeviceId());

							JOptionPane.showMessageDialog(null, "Deleted Device");
							SwingUtilities.updateComponentTreeUI(janela);
							janela.invalidate();
							janela.validate();
							janela.repaint();
						}

					});

					btEditar.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							createEditDeviceWindow(ir, dr, Device.getDeviceId(), Device.getOperatingSystem(),
									Device.getModel(), Device.getOwner());
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
				String opSystem = txtOperatingSystem.getText();
				String model = txtModel.getText();
				
				Device d = new Device();

				d.setOperatingSystem(opSystem);
				d.setModel(model);
				d.setOwner(selectedPatient);

				ir.save(d);
				
				Patient p = dr.findByPatientId(selectedPatient.getPatientId());
				p.setDevice(d);
				dr.save(p);
				
				JOptionPane.showMessageDialog(null, "Registered Device");
			}

		});

		return ir;
	}

}

package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.lang.NumberFormatException;

import javax.swing.JFrame;

import controller.Controller;
import entity.Persona;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class EditorPersonaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Persona persona;
	private boolean create;
	private JTextField textFieldNome;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldEta;
	private JTextField textFieldCognome;
	private JTextField textFieldTelefono;
	private JLabel lblNome;
	private JLabel lblIndirizzo;
	private JLabel lblTelefono;
	private JLabel lblCognome;
	private JLabel lblEta;
	private JButton btnAnnulla;
	private JButton btnSalva;

	public EditorPersonaView() {
		setSize(new Dimension(400, 400));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(67, 50, 90, 20);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(67, 110, 90, 20);
		getContentPane().add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		textFieldEta = new JTextField();
		textFieldEta.setBounds(155, 175, 90, 20);
		getContentPane().add(textFieldEta);
		textFieldEta.setColumns(10);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setColumns(10);
		textFieldCognome.setBounds(242, 110, 90, 20);
		getContentPane().add(textFieldCognome);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(242, 50, 90, 20);
		getContentPane().add(textFieldTelefono);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(67, 35, 46, 14);
		getContentPane().add(lblNome);
		
		lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setBounds(67, 95, 67, 14);
		getContentPane().add(lblIndirizzo);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(242, 95, 67, 14);
		getContentPane().add(lblTelefono);
		
		lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(242, 35, 67, 14);
		getContentPane().add(lblCognome);
		
		lblEta = new JLabel("Eta");
		lblEta.setBounds(155, 160, 46, 14);
		getContentPane().add(lblEta);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(67, 257, 90, 23);
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.backFromCreateButtonPressed();
			}
			
		});
		getContentPane().add(btnAnnulla);
		
		btnSalva = new JButton("Salva");
		btnSalva.setBounds(242, 257, 90, 23);
		btnSalva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(checkValidity()){
						if(create) {
							if(controller.createSalvaContactButtonPressed(generatePersonaFromTextfields())) {
								JOptionPane.showMessageDialog(null, "Inserimento effettuato con succsso","Informazione",JOptionPane.INFORMATION_MESSAGE);
								controller.successSave();
							}
							else {
								JOptionPane.showMessageDialog(null, "Inserimento fallito, riprovare","Errore",JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							if(controller.editSalvaContactButtonPressed(generatePersonaFromTextfields())) {
								JOptionPane.showMessageDialog(null, "Modifica effettuata con succsso","Informazione",JOptionPane.INFORMATION_MESSAGE);
								controller.successSave();
							}
							else {
								JOptionPane.showMessageDialog(null, "Modifica fallita, riprovare","Errore",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Assicurati di aver popolato tutti i campi","Errore",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException exc) {
					JOptionPane.showMessageDialog(null, "Inserire un età valida", "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		getContentPane().add(btnSalva);
		
		
	}
	
	private boolean checkValidity() throws NumberFormatException {
		boolean valid = !textFieldNome.getText().isBlank() && !textFieldCognome.getText().isBlank()
				&& !textFieldIndirizzo.getText().isBlank() && !textFieldEta.getText().isBlank()
				&& !textFieldTelefono.getText().isBlank(); 
		
		Integer.parseInt(textFieldEta.getText());
		return valid;
	}
	
	private Persona generatePersonaFromTextfields() {
		return new Persona(create ? 0 : persona.getId(),textFieldNome.getText(), textFieldCognome.getText(), textFieldIndirizzo.getText(), textFieldEta.getText(), Integer.parseInt(textFieldEta.getText()));
	}
	
	public EditorPersonaView(Controller controller, Persona persona) {
		this();
		this.controller = controller;
		this.persona = persona;	
		this.create = Objects.isNull(persona);
		this.setTitle(create ? "Aggiungi contatto" : "Modifica contatto");
		
		if(!create) {
			textFieldNome.setText(persona.getNome());
			textFieldCognome.setText(persona.getCognome());
			textFieldIndirizzo.setText(persona.getIndirizzo());
			textFieldTelefono.setText(persona.getTelefono());
			textFieldEta.setText(String.valueOf(persona.getEta()));
		}
	}
}

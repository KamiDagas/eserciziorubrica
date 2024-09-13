package views;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import controller.Controller;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField username;
	private JPasswordField password;
	private JLabel labelCredenzialiErrate;
	private Controller controller;
	private JButton btnRegistrati;
	
	private LoginView() {
		setSize(new Dimension(400, 400));
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Accedi o registrati");
		
		username = new JTextField();
		username.setBounds(150, 125, 100, 20);
		getContentPane().add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(150, 200, 100, 20);
		getContentPane().add(password);
		password.setColumns(10);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setBounds(150, 98, 100, 14);
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(150, 175, 100, 14);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblPassword);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.setBounds(59, 269, 100, 23);
		getContentPane().add(buttonLogin);
		
		labelCredenzialiErrate = new JLabel("Credenziali non riconosciute");
		labelCredenzialiErrate.setHorizontalAlignment(SwingConstants.CENTER);
		labelCredenzialiErrate.setForeground(Color.RED);
		labelCredenzialiErrate.setBounds(125, 244, 165, 14);
		labelCredenzialiErrate.setVisible(false);
		getContentPane().add(labelCredenzialiErrate);
		
		btnRegistrati = new JButton("Registrati");
		btnRegistrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkValidity())
				{
					if(controller.registerButtonInLoginViewPressed(username.getText(), String.valueOf(password.getPassword()))) {
						JOptionPane.showMessageDialog(null, "Registrazione effettuata con successo","Informazione",JOptionPane.INFORMATION_MESSAGE);
						username.setText("");
						password.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Nome utente già utilizzato","Errore",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Assicurati di aver popolato tutti i campi","Errore",JOptionPane.ERROR_MESSAGE);
				}
			}			
		});
		btnRegistrati.setBounds(238, 269, 100, 23);
		getContentPane().add(btnRegistrati);
		
		
		
		buttonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loginButtonPressed(username.getText(), String.valueOf(password.getPassword()));
			}
			
		});

	}
	

	private boolean checkValidity() {
		return !username.getText().isBlank() && !String.valueOf(password.getPassword()).isBlank(); 
	}
	
	public LoginView(Controller controller) {
		this();
		this.controller = controller;
	}
	
	public void credenzialiErrateCallback() {
		labelCredenzialiErrate.setVisible(true);
	}
}

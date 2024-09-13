package controller;

import views.ContactsView;
import views.EditorPersonaView;
import views.LoginView;

import javax.swing.JOptionPane;

import dao.*;
import entity.Persona;

public class Controller {
	
	private LoginView loginView;
	private ContactsView contactsView;
	private EditorPersonaView editPersonaView;
	
	private UtenteDAO utenteDao = new UtenteDAOImpl();
	private PersonaDAO personaDao = new PersonaDAOImpl();
	
	public void startLoginView() {
		loginView = new LoginView(this);
		loginView.setVisible(true);
	}
	

	public void loginButtonPressed(String username, String password) {
		
		if(!utenteDao.login(username,password)) {
			loginView.credenzialiErrateCallback();
		}
		else {
			loginView.setVisible(false);
			loginView = null;
			initContactsView();
		}
		
	}
	
	public boolean confirmDeleteContactButtonPressed(int id) {
		return personaDao.eliminaPersona(id);	
	}
	
	public void createButtonPressed() {
		contactsView.setVisible(false);
		contactsView = null;
		editPersonaView = new EditorPersonaView(this,null);
		editPersonaView.setVisible(true);
		
	}
	
	public void editButtonPressed(Persona persona) {
		contactsView.setVisible(false);
		contactsView = null;
		editPersonaView = new EditorPersonaView(this,persona);
		editPersonaView.setVisible(true);
		
	}
	
	public void backFromCreateButtonPressed() {
		editPersonaView.setVisible(false);
		editPersonaView = null;
		initContactsView();
	}
	
	public void successSave() {
		editPersonaView.setVisible(false);
		editPersonaView = null;
		initContactsView();
	}
	
	public boolean editSalvaContactButtonPressed(Persona persona) {
		return personaDao.aggiornaPersona(persona);
	}
	
	public boolean createSalvaContactButtonPressed(Persona persona) {
		return personaDao.creaPersona(persona);
	}
	
	public void initContactsView() {
		Persona[] persone = personaDao.estraiPersone();
		contactsView = new ContactsView(this,persone);
		contactsView.setVisible(true);
	}
	
	public boolean registerButtonInLoginViewPressed(String username, String password) {
		return utenteDao.register(username,password);
	}
	
	
}

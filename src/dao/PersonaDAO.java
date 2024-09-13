package dao;

import entity.Persona;

public interface PersonaDAO {

	public Persona[] estraiPersone();
	
	public boolean eliminaPersona(int id);
	
	public boolean creaPersona(Persona persona);
	
	public boolean aggiornaPersona(Persona persona);
	
}	

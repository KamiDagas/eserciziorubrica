package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Persona;
import persistence.ConnectionSingleton;

public class PersonaDAOImpl implements PersonaDAO{

	@Override
	public Persona[] estraiPersone() {
		try {
			List<Persona> persone = new ArrayList<>();
			Statement stmt = ConnectionSingleton.getInstance().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id,nome,cognome,indirizzo,telefono,eta from persona order by nome");
			while(rs.next()) {
				persone.add(new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), rs.getInt(6)));
			}
			Persona[] personeArr = new Persona[persone.size()];
			int index = 0;
			for(Persona persona : persone)
				personeArr[index++] = persona;
			return personeArr;
		}catch (SQLException e) {
			e.printStackTrace();
			return new Persona[] {};
		}
		
	}

	@Override
	public boolean eliminaPersona(int id) {
		Statement stmt;
		try {
			stmt = ConnectionSingleton.getInstance().getConnection().createStatement();
			int res = stmt.executeUpdate("delete from persona where id = "+ id +" ;");
			return res == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean creaPersona(Persona persona) {
		Statement stmt;
		try {
			stmt = ConnectionSingleton.getInstance().getConnection().createStatement();
			int res = stmt.executeUpdate("insert into persona(nome,cognome,indirizzo,telefono,eta) "
					+ "values('"+ persona.getNome()+"','"+persona.getCognome()+"','"+persona.getIndirizzo()+
					"','"+ persona.getTelefono() + "'," + persona.getEta()+");");
			return res == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean aggiornaPersona(Persona persona) {
		Statement stmt;
		try {
			stmt = ConnectionSingleton.getInstance().getConnection().createStatement();
			int res = stmt.executeUpdate("update persona set nome = '"+ persona.getNome()+"',"
					+ "cognome = '" + persona.getCognome() + "', indirizzo = '" + persona.getIndirizzo()+"', "
					+ "telefono = '"+ persona.getTelefono() + "', eta = " + persona.getEta()+
					" where id = " + persona.getId());
			return res == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	


}

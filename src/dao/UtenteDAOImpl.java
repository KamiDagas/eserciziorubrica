package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import persistence.ConnectionSingleton;

public class UtenteDAOImpl implements UtenteDAO {

	public boolean login(String username, String password) {
		try {
			Statement stmt = ConnectionSingleton.getInstance().getConnection().createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("select username, password from utente where username = '"+username+"';");
			if(!rs.next())
				return false;
			return rs.getString(2).equals(password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean register(String username, String password) {
		try {
			Statement stmt = ConnectionSingleton.getInstance().getConnection().createStatement();
			stmt.executeUpdate("insert into utente(username,password) values('"+username+"','"+password+"')");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

}

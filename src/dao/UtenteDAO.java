
package dao;

public interface UtenteDAO {

	public boolean login(String username, String password);
	
	public boolean register(String username, String password);
	
}

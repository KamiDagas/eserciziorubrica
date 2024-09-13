package controller;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.Driver;

import persistence.ConnectionSingleton;
import views.*;


public class Main {

	public static void main(String[] args) throws SQLException {
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prova","root","D1oL4dro!!");
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery("select * from prova");
//		while(rs.next()) {
//			System.out.println("Faa "+ rs.getInt(1) + ", bho " + rs.getString(2));
//		}
		Controller controller = new Controller();
		controller.startLoginView();
//		JOptionPane.showMessageDialog(null,"Faa","Ammo", JOptionPane.INFORMATION_MESSAGE);
//
//		ConnectionSingleton.getInstance();
		
		
	}

	
	
}

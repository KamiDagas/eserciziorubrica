package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConnectionSingleton {
	
	private static ConnectionSingleton instance;
	private Connection connection;
	
	private ConnectionSingleton() {}
	
	public static ConnectionSingleton getInstance() {
		if(instance == null) {
			instance = new ConnectionSingleton();
			Properties prop = new Properties();
			try {
				FileInputStream file = new FileInputStream("credenziali_database.txt");
				Scanner scanner;
				prop.load(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				Connection con = DriverManager.getConnection("jdbc:mysql://"+prop.getProperty("ip-server-mysql")+":"+prop.getProperty("porta")+"/rubrica",prop.getProperty("username"),prop.getProperty("password"));
				instance.connection = con;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return this.connection;
	}

}

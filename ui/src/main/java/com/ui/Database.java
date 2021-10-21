package com.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	
	private static Database db = null;
	public Connection conn;
	
	private Database(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ui","root","");
		}catch(Exception err) {
			
		}
	}
	
	public static Database getInstance() {
		if(db==null) 
			db = new Database();
		return db;
	}
	
	protected boolean addUser(String name, String email, String code, String contact, String password) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3,code);
			ps.setString(4, contact);
			ps.setString(5, password);
			return !ps.execute();
		} catch (Exception err) {
			err.printStackTrace();
			return false;
		}
	}
	
	protected boolean login(String email,String password) {
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM users WHERE email='" + email + "'");
			if(rs.next()) {
				String pswrd = rs.getString("password");
				return password.equals(pswrd);
			}
			return false;
		}catch(Exception err) {
			err.printStackTrace();
			return false;
		}
		
	}

}

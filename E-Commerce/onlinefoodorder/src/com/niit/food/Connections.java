package com.niit.food;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections {
	static Connection con = null;

	public static Connection getMyConnection() {
		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");  
			  //create  the connection object 
			  con=DriverManager.getConnection(  
					  "jdbc:mysql://172.28.242.65:3307/martian", "root", "alyx");  
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

	public static void main(String... args) throws SQLException {
		 Connections.getMyConnection();
		Statement st = Connections.con.createStatement();
		ResultSet rs = st.executeQuery("select * from Customer ");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}

	}
}

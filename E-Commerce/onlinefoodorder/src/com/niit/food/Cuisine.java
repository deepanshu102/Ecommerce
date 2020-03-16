package com.niit.food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cuisine {
	String Id;
	String name;

	public String getName() {
		return name;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Cuisine() {
		// TODO Auto-generated constructor stub
	}

	public Cuisine(String id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	public static int cuisineMenu() {
		Connections.con = Connections.getMyConnection();
		int cuisineNumber;
		try {
			String query = "{CALL cuisineMenu()}";
			PreparedStatement st = Connections.con.prepareCall(query);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(1)+" "+ rs.getString(2));
			}
			System.out.println("Enter your choice");
			Scanner sc=new Scanner(System.in);
			cuisineNumber=sc.nextInt();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return cuisineNumber;
	}

}

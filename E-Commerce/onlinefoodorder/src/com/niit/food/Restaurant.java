package com.niit.food;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Restaurant {

	public String getRestaurantName() {
		return RestaurantName;
	}

	public void setCustomerName(String RestaurantName) {
		this.RestaurantName = RestaurantName;
	}

	public String getRestaurantAddress() {
		return RestaurantAddress;
	}

	public void setCustomerAddress(String RestaurantAddress) {
		this.RestaurantAddress = RestaurantAddress;
	}

	public String getRestaurantId() {
		return RestaurantId;
	}

	public void setCustomerPhone(String RestaurantId) {
		this.RestaurantId = RestaurantId;
	}

	public String getPassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public Restaurant() {
		super();
	}

	String RestaurantId;
	String RestaurantName;
	String RestaurantAddress;
	String city;
	String state;
	int pincode;
	String password;
	public PreparedStatement pst;
	Scanner sc;

	public void registerRestaurant() {
		Connections.con = Connections.getMyConnection();
		sc = new Scanner(System.in);
		try {
			String stql = "insert into Restaurant values(?,?,?,?,?,?,?)";
			System.out.println("Enter your ID");
			RestaurantId = sc.next();
			System.out.println("Enter your Name");
			RestaurantName = sc.next();
			System.out.println("Enter your Address");
			RestaurantAddress = sc.next();
			System.out.println("Enter your City");
			city = sc.next();
			System.out.println("Enter your State");
			state = sc.next();
			System.out.println("Enter your Pincode");
			pincode = sc.nextInt();
			System.out.println("Enter your Password");
			password = sc.next();
			pst = Connections.con.prepareStatement(stql);
			pst.setString(1, RestaurantId);
			pst.setString(2, RestaurantName);
			pst.setString(3, RestaurantAddress);
			pst.setString(4, city);
			pst.setString(5, state);
			pst.setInt(6, pincode);
			pst.setString(7, password);

			int cnt = pst.executeUpdate();
			if (cnt != -1)
				System.out.println("Registered");
			else
				System.out.println("Failed");
			Connections.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public void login() {
		try {
			String RestaurantId, password;
			sc = new Scanner(System.in);
			System.out.println("Enter PhoneNo.: ");
			RestaurantId = sc.next();
			System.out.println("Enter Password: ");
			password = sc.next();
			Connections.con = Connections.getMyConnection();
			String sql = "select * from Restaurant where restaurantId=? and password=?";
			pst = Connections.con.prepareStatement(sql);
			pst.setString(1, RestaurantId);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("Welcome: " + rs.getString(2));
			} else {
				System.out.println("PhoneNo. or Password Is Incorrect");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
/*
 * public static void main(String[] arg) { Restaurant obj = new Restaurant(); //
 * obj.setInput(); obj.registerRestaurant(); obj.login(); }
 */

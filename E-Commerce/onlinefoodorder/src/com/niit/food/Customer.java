package com.niit.food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
	
	PreparedStatement pst;
	String customerId;
	String customerName;
	String address;
	String city;
	String state;
	int pincode;
	String password;
	Scanner sc;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public void registerCustomer() {
		Connections.con = Connections.getMyConnection();
		sc = new Scanner(System.in);
		try {
			String stql = "insert into Customer values(?,?,?,?,?,?,?)";
			System.out.println("Enter your ID");
			customerId = sc.next();
			System.out.println("Enter your Name");
			customerName = sc.next();
			System.out.println("Enter your Address");
			address = sc.next();
			System.out.println("Enter your City");
			city = sc.next();
			System.out.println("Enter your state");
			state = sc.next();
			System.out.println("Enter your Pincode");
			pincode = sc.nextInt();
			System.out.println("Enter your Password");
			password = sc.next();
			pst = Connections.con.prepareStatement(stql);
			pst.setString(1, customerId);
			pst.setString(2, customerName);
			pst.setString(3, address);
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
		System.out.println("Login in system!!");
		sc = new Scanner(System.in);
		try {
			
			sc = new Scanner(System.in);
			System.out.println("Enter PhoneNo.: ");
			customerId = sc.next();
			System.out.println("Enter Password: ");
			password = sc.next();
			Connections.con = Connections.getMyConnection();
			String sql = "select * from customer where customerId=? and password=?";
			pst = Connections.con.prepareStatement(sql);
			pst.setString(1, customerId);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				System.out.println("Welcome: " + rs.getString(2));
				afterLogin();
			} else {
				System.out.println("PhoneNo. or Password Is Incorrect");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void forgetPassword() {
		sc = new Scanner(System.in);
		System.out.println("Forget Password?");
		try {
			Connections.con=Connections.getMyConnection();
			String stql2 = "update customer set password=? where customerId=?";
			String customerId=sc.next();
			System.out.println("Enter New Password");
			String password = sc.next();
			pst = Connections.con.prepareStatement(stql2);
			pst.setString(1, password);
			pst.setString(2, customerId);
			
			int a = pst.executeUpdate();
			if (a > 0) {
				this.password=password;
				this.customerId=customerId;
				System.out.println("Password successfully changed ");
			} else {
				System.out.println("PhoneNo. is Incorrect");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public void afterLogin() {
		Scanner s=new Scanner(System.in);
		ArrayList<Cuisine> cuisineList = Cuisine.cuisineMenu();
		int i = 0;
		for (Cuisine cuisine : cuisineList) {
			i++;
			System.out.println(i + "\t" + cuisine.getName());
		}

		System.out.println("Choose Cuisine:");
		i = s.nextInt();

		System.out.println(cuisineList.get(i - 1).getName());
		ArrayList<FoodMenu> foodMenuList = FoodMenu.foodMenu(cuisineList.get(i - 1));

		i = 0;

		for (FoodMenu foodMenu : foodMenuList) {
			i++;
			System.out.println(i + "\t " + foodMenu.getFood().getFoodName() + "\t"
					+ foodMenu.getRestaurant().getRestaurantName() + "\t" + foodMenu.getPrice());
		}

		i = s.nextInt();

		System.out.println("Selected Food Menu " + foodMenuList.get(i - 1).getFood().getFoodName() + "\t"
				+ foodMenuList.get(i - 1).getRestaurant().getRestaurantName() + "\t"
				+ foodMenuList.get(i - 1).price);

		System.out.println("Enter the Quantity:-");

		int quantity = s.nextInt();

		FoodOrder.makeOrder(foodMenuList.get(i - 1), this, quantity);

	}

	public static void main(String[] arg) {
		Customer obj = new Customer();
		//obj.registerCustomer();
	
		obj.forgetPassword();

	}
}

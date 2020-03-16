package com.niit.food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Food {

	Scanner s = new Scanner(System.in);
	Cuisine cuisine;
	String foodIdentity;
	public String getFoodIdentity() {
		return foodIdentity;
	}

	public void setFoodIdentity(String foodIdentity) {
		this.foodIdentity = foodIdentity;
	}

	protected static int foodId = 1;
	String foodName;
	Scanner sc;
	PreparedStatement pst;

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public void getItem() {

		foodId = 1;
		foodName = s.next();

		foodId++;
	}

	public void foodInsertion() {
		Connections.con = Connections.getMyConnection();
		sc = new Scanner(System.in);
		try {
			String stql = "{call addFood(?,?,?);}";
			System.out.println("Enter Food Name");
			setFoodName(sc.next());
			System.out.println("Enter Cuisine Name");
			Cuisine obj = new Cuisine();
			obj.setName(sc.next());
			setCuisine(obj);
			pst = Connections.con.prepareStatement(stql);

			pst.setString(1, foodName);
			int cnt = pst.executeUpdate();
			if (cnt != -1)
				System.out.println("Food Succesfully Entered");
			else
				System.out.println("Failed");
			Connections.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static int getFoodId() {
		return foodId;
	}

	public static void setFoodId(int foodId) {
		Food.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

//	public static void main(String[] arg) {
//		Scanner sc = new Scanner(System.in);
//		int i = 0;
//		ArrayList<Cuisine> cuisineList= Cuisine.cuisineMenu();
//		for (Cuisine ob1 : oblk) {
//			i++;
//			System.out.println(i + "\t" + ob1.getName());
//		}
//		;
//		System.out.println("Choose Cuisine:");
//		i = sc.nextInt();
//		System.out.println(oblk.get(i - 1).getName());
//		new Food().foodMenu(oblk.get(i - 1));
////		Food obj = new Food();
////		obj.foodInsertion();
//
//	}


}
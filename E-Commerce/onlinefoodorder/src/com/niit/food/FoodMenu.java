package com.niit.food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FoodMenu {
	Food food;
	Restaurant restaurant;
	double price;
	static int foodMenuIdentity = 0;

	public FoodMenu() {
		// TODO Auto-generated constructor stub
	}

	public static int getFoodMenuIdentity() {
		return foodMenuIdentity;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public FoodMenu(Food food, Restaurant restaurant, double price) {
		super();
		this.food = food;
		this.restaurant = restaurant;
		this.price = price;
	}

	public static ArrayList<FoodMenu> foodMenu(Cuisine obj) {
		ArrayList<FoodMenu> foodMenuList = new ArrayList<FoodMenu>();
		Connections.con = Connections.getMyConnection();
		try {
			String query = "Select * from food,foodMenu,restaurant where foodMenu.foodId=food.foodId  and foodMenu.restaurantId=restaurant.restaurantId AND food.cuisineId=?";
			PreparedStatement st = Connections.con.prepareCall(query);
			st.setString(1, obj.getId());
			ResultSet rs = st.executeQuery();
			int i = 0;

			while (rs.next()) {
				Food food = new Food();
				food.setFoodName(rs.getString("foodName"));
				food.setFoodIdentity(rs.getString("foodId"));

				Restaurant restaurant = new Restaurant();
				restaurant.setCustomerName(rs.getString("RestaurantName"));
				restaurant.setCustomerPhone(rs.getString("RestaurantId"));

				i++;

				FoodMenu foodMenu = new FoodMenu(food, restaurant, rs.getInt("foodPrice"));

				foodMenuList.add(foodMenu);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return foodMenuList;
		}
		
	}
}

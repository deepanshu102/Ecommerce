package com.niit.food;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodOrder {
	static int i = 0;

	public FoodOrder() {
		// TODO Auto-generated constructor stub
	}

	public static void makeOrder(FoodMenu selectedMenu, Customer customer, int quantity) {
		Connections.con = Connections.getMyConnection();
		try {
			String query = "{CALL addOrder(?,?,?,?,?,?)}";
			
			PreparedStatement st = Connections.con.prepareCall(query);
			st.setString(1, selectedMenu.getFood().getFoodName());
			st.setString(2, customer.getCustomerId());
			st.setInt(3, quantity);
			st.setString(4, (selectedMenu.getPrice() * quantity)+"");
			st.setString(5, i + "");
			st.setString(6, selectedMenu.getRestaurant().getRestaurantName());

			int rowsEffect = st.executeUpdate();
			if(rowsEffect<0) {
				System.out.println("Sorry Some issue occur during Ordering Food!");
			}
			else
			{
				System.out.println("SuccessFully Order:- O"+(i++));
				System.out.println("Total Amount:"+(selectedMenu.getPrice() * quantity));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}
	}
public void cancleOrder() {
	Connections.con = Connections.getMyConnection();
	try
	{
		Scanner sc=new Scanner(System.in);
		String query = "update foodorder set cancelStatus=? where orderId=?";
		
		PreparedStatement st = Connections.con.prepareCall(query);
		System.out.println("Enter order Id:-");
		String orderId=sc.next();
		st.setString(1,"Y");
		st.setString(2, orderId);

		int rowsEffect = st.executeUpdate();
		if(rowsEffect<0) {
			System.out.println("Sorry Some issue occur during cancelling the Ordering Food!");
		}
		else
		{
			System.out.println("Your Order sucessfully canclled:- "+orderId);
		}
	
	}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
}
	public ArrayList<FoodOrder> orderDetails(Customer customer) {
		ArrayList<FoodOrder> foodOrderList = new ArrayList<FoodOrder>();

		return foodOrderList;
	}
	public static void main(String ... args) {

			FoodOrder order=new FoodOrder();
			order.cancleOrder();
	}
}

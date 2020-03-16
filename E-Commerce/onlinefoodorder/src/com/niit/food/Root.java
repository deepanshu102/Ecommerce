package com.niit.food;

import java.util.*;

public class Root {

	public static void main(String[] args) {
		System.out.println("Welcome Foodie");
		System.out.println("Enter your choice to proceed further!");
		System.out.println("1)Customer");
		System.out.println("2)Restaurant");
		int choice;

		{

			Scanner s = new Scanner(System.in);

			choice = s.nextInt();
			switch (choice) {
			case 1:
				Customer lc = null;
				System.out.println("HEY Foodie");
				System.out.println("1)New User?Register now");
				System.out.println("2)Login"); 
				System.out.println("3)Forgot Password");{
				int option;
				option = s.nextInt();
				switch (option) {
				case 1:
					System.out.println("New Customer");
					lc = new Customer();
					lc.registerCustomer();
					lc.login();
					break;
				case 2:
					System.out.println("Hey Welcome Back Foodie");
					lc = new Customer();
					lc.login();
					break;
				case 3:
					System.out.println("-----------Forgot Password------------");
					lc = new Customer();
					lc.forgetPassword();

				}
			}


				break;

			case 2:
				System.out.println("Hey");
				System.out.println("1)New Restaurant?Join Us");
				System.out.println("2)Login"); {
				int option;
				option = s.nextInt();
				switch (option) {
				case 1:
					System.out.println("New Restaurant");
					Restaurant r = new Restaurant();
					r.registerRestaurant();
					break;

				case 2:
					System.out.println("Hey Welcome Back");
					Restaurant lr = new Restaurant();
					lr.login();
					break;
				}

			}
				break;
			default:
				System.out.println("ok,bye!");
			}
		}
	}
}

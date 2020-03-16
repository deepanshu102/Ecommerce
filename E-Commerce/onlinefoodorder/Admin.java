package com.niit.food;

public class Admin {

	static String adminId;
	static String adminName;
	static String adminPassword;

	public static String getAdminId() {
		return adminId;
	}

	public static void setAdminId(String adminId) {
		Admin.adminId = adminId;
	}

	public static String getAdminName() {
		return adminName;
	}

	public static void setAdminName(String adminName) {
		Admin.adminName = adminName;
	}

	public static String getAdminPassword() {
		return adminPassword;
	}

	public static void setAdminPassword(String adminPassword) {
		Admin.adminPassword = adminPassword;
	}

}

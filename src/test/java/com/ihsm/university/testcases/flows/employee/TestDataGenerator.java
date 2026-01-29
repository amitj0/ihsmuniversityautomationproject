package com.ihsm.university.testcases.flows.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class TestDataGenerator {

	private static Random random = new Random();

	// Random String
	public static String randomString(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return sb.toString();
	}

	// Random Number String
	public static String randomNumber(int length) {
		String nums = "0123456789";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(nums.charAt(random.nextInt(nums.length())));
		}
		return sb.toString();
	}

	// Unique Email
//	public static String randomEmail() {
//		return "name_" + System.currentTimeMillis() + "@testmail.com";
//	}

	public static String randomEmail() {

		String[] names = { "Nia", "Liam", "Ava", "Noah", "Mia", "Ethan", "Zara", "Leo" };

		String randomName = names[(int) (Math.random() * names.length)];

		String unique = UUID.randomUUID().toString().substring(0, 6);

		return randomName + "_" + unique + "@testmail.com";
	}

	// Random Passport
	public static String randomPassport() {
		return "P" + randomNumber(7);
	}

	// Random Phone
	public static String randomPhone() {
		return "9" + randomNumber(9);
	}

	// Random Future Date
	public static String futureDate() {
		return "01012030";
	}

	// Random Past Date
	public static String pastDate() {
		return "01011995";
	}

	public static String randomCountry() {

		String[] countries = { "India", "United States", "United Kingdom", "Canada", "Germany", "France", "Australia",
				"Japan", "Switzerland", "Brazil", "Netherlands", "Singapore" };

		return countries[random.nextInt(countries.length)];
	}

	// Random Guardian Type
	public static String randomGuardian() {

		String[] guardians = { "Father", "Mother", "Spouse", "Brother", "Sister", "Uncle", "Aunt", "Guardian" };

		return guardians[random.nextInt(guardians.length)];
	}

	// Random Marital Status
	public static String randomMaritalStatus() {

		String[] statuses = { "Single", "Married", "Divorced", "Widowed", "Separated" };

		return statuses[random.nextInt(statuses.length)];
	}

}

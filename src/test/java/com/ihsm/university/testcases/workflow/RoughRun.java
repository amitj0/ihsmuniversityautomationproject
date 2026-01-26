package com.ihsm.university.testcases.workflow;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.ihsm.university.base.BaseClass;

public class RoughRun extends BaseClass {

	// ================= PASS TESTS =================

	@Test(description = "Login Test")
	public void loginTest() {
		System.out.println("Login Test Executed");
	}

	@Test(description = "Dashboard Test")
	public void dashboardLoadTest() {
		System.out.println("Dashboard Loaded");
	}

	@Test(description = "ST Search Test")
	public void searchStudentTest() {
		System.out.println("Student Search Success");
	}

	@Test(description = "ST Add Test")
	public void addStudentTest() {
		System.out.println("Student Added");
	}

	@Test(description = "ST Update Test")
	public void updateStudentTest() {
		System.out.println("Student Updated");
	}

	// ================= FAIL TESTS =================

	@Test(description = "Dlt Btn Test")
	public void deleteStudentTest() {
		System.out.println("Delete Student Failed");
		Assert.fail("Delete button not clickable");
	}

	@Test
	public void uploadDocumentTest() {
		System.out.println("Upload Document Failed");
		Assert.assertTrue(false);
	}

	@Test(description = "Fee Payment Test")
	public void feePaymentTest() {
		System.out.println("Fee Payment Failed");
		Assert.fail();
	}

	// ================= SKIP TESTS =================

	@Test(description = "Feature Under development Test")
	public void exportReportTest() {
		throw new SkipException("Feature under development");
	}

	@Test(description = "Block by Env Test")
	public void emailNotificationTest() {
		throw new SkipException("Blocked by environment");
	}

	// ================= MORE MIX =================

	@Test(description = "Logout Test")
	public void logoutTest() {
		System.out.println("Logout Success");
	}

	@Test(description = "Profile update Test")
	public void profileUpdateTest() {
		System.out.println("Profile Updated");
	}

	@Test(description = "Pass rule mismatch Test")
	public void changePasswordTest() {
		Assert.fail("Password rules mismatch");
	}

	@Test(description = "Waiting for API Test")
	public void auditLogTest() {
		throw new SkipException("Waiting for API");
	}

	@Test(description = "Setting Saved Test")
	public void settingsSaveTest() {
		System.out.println("Settings Saved");
	}
}

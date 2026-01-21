package com.ihsm.university.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ihsm.university.base.BaseClass;

import org.testng.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static String reportPath;

	@Override
	public synchronized void onStart(ITestContext context) {
		if (extent == null) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
			String reportName = "IHSM_University_Report_" + timeStamp + ".html";
			reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setDocumentTitle("IHSM University Automation Report");
			spark.config().setReportName("Regression Test Report");
			spark.config().setTheme(Theme.DARK);
			spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Project", "IHSM University");
			extent.setSystemInfo("Environment", "QA");
			extent.setSystemInfo("User", System.getProperty("user.name"));
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getDescription();
		if (testName == null || testName.isEmpty()) {
			testName = result.getMethod().getMethodName();
		}

		ExtentTest extentTest = extent.createTest(testName);
		extentTest.assignCategory(result.getMethod().getGroups());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, MarkupHelper.createLabel("PASSED: " + result.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().log(Status.FAIL, MarkupHelper.createLabel("FAILED: " + result.getName(), ExtentColor.RED));
		test.get().fail(result.getThrowable());

		try {
			BaseClass base = new BaseClass();
			String screenshotPath = base.captureScreenshot(result.getName());

			test.get().addScreenCaptureFromPath("../screenshots/" + new File(screenshotPath).getName());
		} catch (Exception e) {
			test.get().log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, MarkupHelper.createLabel("SKIPPED: " + result.getName(), ExtentColor.YELLOW));
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}

		try {
			File reportFile = new File(reportPath);
			if (Desktop.isDesktopSupported() && reportFile.exists()) {
				Desktop.getDesktop().browse(reportFile.toURI());
			}
		} catch (IOException e) {
			System.out.println("Could not auto-open report: " + e.getMessage());
		}
	}
}

package com.ihsm.university.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ihsm.university.base.BaseClass;

import org.openqa.selenium.logging.*;
import org.testng.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class ExtentListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static String reportPath;

	// ================= SUITE START =================
	@Override
	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
		String reportName = "IHSM_University_Report_" + timeStamp + ".html";
		reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setDocumentTitle("IHSM University Automation Report");
		spark.config().setReportName("Regression Test Report");
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("Project", "IHSM University");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("User", System.getProperty("user.name"));
	}

	// ================= TEST START =================
	@Override
	public void onTestStart(ITestResult result) {

		String testName = result.getMethod().getDescription();
		if (testName == null || testName.isEmpty()) {
			testName = result.getMethod().getMethodName();
		}

		ExtentTest extentTest = extent.createTest(testName);
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.assignAuthor("Automation Team"); // 👈 AUTHOR ADDED

		test.set(extentTest);
	}

	// ================= PASS =================
	@Override
	public void onTestSuccess(ITestResult result) {

		TestResultSummary.passed++;

		test.get().log(Status.PASS, MarkupHelper.createLabel("TEST PASSED", ExtentColor.GREEN));
	}

	// ================= FAIL =================
	@Override
	public void onTestFailure(ITestResult result) {

		TestResultSummary.failed++;

		test.get().log(Status.FAIL, MarkupHelper.createLabel("TEST FAILED", ExtentColor.RED));

		test.get().fail(result.getThrowable());

		// Browser Console Errors
		try {
			if (BaseClass.getDriver() != null) {

				LogEntries logs = BaseClass.getDriver().manage().logs().get(LogType.BROWSER);

				StringBuilder consoleErrors = new StringBuilder();

				for (LogEntry entry : logs) {
					if (entry.getLevel() == Level.SEVERE) {
						consoleErrors.append(entry.getMessage()).append("\n");
					}
				}

				if (consoleErrors.length() > 0) {
					test.get().fail("🚨 Browser Console Errors:\n" + consoleErrors);
				}
			}
		} catch (Exception e) {
			test.get().warning("Unable to fetch browser logs");
		}

		// Screenshot
		try {
			if (BaseClass.getDriver() != null) {
				String screenshotPath = BaseClass.captureScreenshot(result.getName());

				test.get().addScreenCaptureFromPath("../screenshots/" + new File(screenshotPath).getName());
			}
		} catch (Exception e) {
			test.get().warning("Screenshot capture failed");
		}
	}

	// ================= SKIP =================
	@Override
	public void onTestSkipped(ITestResult result) {

		TestResultSummary.skipped++;

		test.get().log(Status.SKIP, MarkupHelper.createLabel("TEST SKIPPED", ExtentColor.YELLOW));
	}

	// ================= FINISH =================
	@Override
	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}

		// Email Report
		try {
			if (reportPath != null && new File(reportPath).exists()) {
				EmailUtils.sendMail(reportPath);
			}
		} catch (Exception e) {
			System.out.println("Email skipped");
		}
	}
}

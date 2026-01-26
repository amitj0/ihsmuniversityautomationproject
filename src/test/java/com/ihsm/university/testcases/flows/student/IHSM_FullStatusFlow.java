package com.ihsm.university.testcases.flows.student;

import org.openqa.selenium.WebDriver;
import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.student.status.Status_Status;

public class IHSM_FullStatusFlow extends BaseClass {

	private WebDriver driver;

	public IHSM_FullStatusFlow(WebDriver driver) {
		this.driver = driver;
	}

	public void execute(StudentFullRegistrationDataVariables student) {
		logger.info("===== STARTING FULL STATUS FLOW =====");

		logger.info("Filling Status Information...");
		Status_Status statusInfo = new Status_Status(getDriver());
		statusInfo.fillStatusStatusForm(student.status, student.statusDate, student.statusCode, student.statusRemarks,
				getTestDataPath(student.statusImage));
		logger.info("Status Information submitted successfully");

		logger.info("===== FULL STATUS INFORMATION FLOW COMPLETED =====");
	}
}

package com.ihsm.university.testcases.flows.student;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.status.Status_ExamStatus;
import com.ihsm.university.pageobjects.status.Status_Status;

public class IHSM_FullStatusFlow extends BaseClass {

	public void execute() {
		logger.info("===== STARTING FULL STATUS FLOW =====");

		logger.info("Filling Status Information...");
		// ------------------ STATUS INFORMATION ------------------
		Status_Status statusInfo = new Status_Status(getDriver());
		statusInfo.fillStatusStatusForm("Cancelled", "01/01/2026", "4554", "Thankyou", getTestDataPath("male.png"));
		logger.info("Status Information submitted successfully");

		logger.info("===== FULL STATUS INFORMATION FLOW COMPLETED =====");
	}

}

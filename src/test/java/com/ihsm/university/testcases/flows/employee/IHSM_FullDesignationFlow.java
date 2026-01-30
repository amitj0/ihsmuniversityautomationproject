package com.ihsm.university.testcases.flows.employee;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.employee.designation.Designation_EmploymentRights;
import com.ihsm.university.pageobjects.employee.designation.Designation_Position;

public class IHSM_FullDesignationFlow extends BaseClass {
	public void execute() throws Exception {
		logger.info("===== STARTING FULL DESIGNATION INFORMATION FLOW =====");

		// --------------------------DESIGNATION INFORMATION FLOW -----------
		// Employee Rights
		logger.info("Filling Employee Rights Information...");
		Designation_EmploymentRights empRights = new Designation_EmploymentRights(getDriver());
		empRights.fillEmploymentRightsForm("Part Time", "0.25", "Transfer", TestDataGenerator.randomNumber(5),
				"01012026", "01012027", "Academic", "Rector", "Nursuing / PG / MBBS", "2", "01012026",
				TestDataGenerator.randomNumber(3), "200000", TestDataGenerator.randomNotes());
		logger.info("Employee Rights Information submitted successfully");

		// Employee Position
		logger.info("Filling Employee Position Information.....");
		Designation_Position empPosition = new Designation_Position(getDriver());
		empPosition.fillPositionInOtherOrgForm("Experience in IHSM", "01/01/2026", "01/01/2027", TestDataGenerator.randomUniversity(),
				TestDataGenerator.randomUniversityPosition(), TestDataGenerator.randomNotes());
		logger.info("Employee Position Information submitted successfully");

		logger.info("===== FULL DESIGNATION INFORMATION FLOW COMPLETED =====");
	}

}

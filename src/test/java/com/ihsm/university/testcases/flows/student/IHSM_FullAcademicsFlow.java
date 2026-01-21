package com.ihsm.university.testcases.flows.student;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.academics.Academics_Qualification_Diploma;
import com.ihsm.university.pageobjects.academics.Academics_Qualification_LastEducation;
import com.ihsm.university.pageobjects.academics.Academics_Qualification_Qualification;

public class IHSM_FullAcademicsFlow extends BaseClass {

	public void execute() throws Exception {
		logger.info("===== STARTING FULL ACADEMICS FLOW =====");

		logger.info("Filling Qualification Information...");
		// --------------------------QUALIFICATION INFORMATION -----------
		Academics_Qualification_LastEducation lastInfo = new Academics_Qualification_LastEducation(getDriver());
		lastInfo.fillLastEducationInfo("Diploma", "School", "01/01/2025", "01/01/2026", "01/01/2027", "23", "Subject",
				"100", getTestDataPath("male.png"));
		logger.info("Qualification Information submitted successfully");

		logger.info("Filling Diploma Information....");
		Academics_Qualification_Diploma diplomaInfo = new Academics_Qualification_Diploma(getDriver());
		diplomaInfo.fillDiplomaDetails("123", "234", "897", "01/01/2025", "01/01/2026", "St Prof", "Advice", "Diploma",
				"799", "01/01/2026", getTestDataPath("male.png"));
		logger.info("Diploma Information submitted successfully");

		logger.info("Filling Qualification Information.....");
		Academics_Qualification_Qualification qualificationInfo = new Academics_Qualification_Qualification(
				getDriver());
		qualificationInfo.fillQualificationInformation("Diploma", "School/College", "4546", "01/01/2025", "01/01/2024",
				"01/01/2026", "Student", "India", "Haryana (HR)", "Panipat", getTestDataPath("male.png"));
		logger.info("Qualification Information submitted successfully");

		logger.info("===== FULL ACADEMICS INFORMATION FLOW COMPLETED =====");

	}

}

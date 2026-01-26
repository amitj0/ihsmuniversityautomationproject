package com.ihsm.university.testcases.flows.student;

import org.openqa.selenium.WebDriver;
import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.student.academics.Academics_Qualification_Diploma;
import com.ihsm.university.pageobjects.student.academics.Academics_Qualification_LastEducation;
import com.ihsm.university.pageobjects.student.academics.Academics_Qualification_Qualification;

public class IHSM_FullAcademicsFlow extends BaseClass {

	private WebDriver driver;

	public IHSM_FullAcademicsFlow(WebDriver driver) {
		this.driver = driver;
	}

	public void execute(StudentFullRegistrationDataVariables student) throws Exception {
		logger.info("===== STARTING FULL ACADEMICS FLOW =====");

		// ---------------- Last Education ----------------
		Academics_Qualification_LastEducation lastInfo = new Academics_Qualification_LastEducation(getDriver());
		lastInfo.fillLastEducationInfo(student.lastEducation, student.lastEducationSchool,
				student.lastEducationStartDate, student.lastEducationEndDate, student.lastEducationGraduationDate,
				student.lastEducationMarks, student.lastEducationSubject, student.lastEducationPercentage,
				getTestDataPath(student.lastEducationImage));
		logger.info("Last Education Information submitted successfully");

		// ---------------- Diploma Information ----------------
		Academics_Qualification_Diploma diplomaInfo = new Academics_Qualification_Diploma(getDriver());
		diplomaInfo.fillDiplomaDetails(student.diplomaCode, student.diplomaNumber, student.diplomaRegistration,
				student.diplomaStartDate, student.diplomaEndDate, student.diplomaInstitution, student.diplomaRemarks,
				student.diplomaType, student.diplomaMarks, student.diplomaGraduationDate,
				getTestDataPath(student.diplomaImage));
		logger.info("Diploma Information submitted successfully");

		// ---------------- Qualification Information ----------------
		Academics_Qualification_Qualification qualificationInfo = new Academics_Qualification_Qualification(
				getDriver());
		qualificationInfo.fillQualificationInformation(student.qualificationType, student.qualificationInstitution,
				student.qualificationRegistrationNumber, student.qualificationStartDate, student.qualificationEndDate,
				student.qualificationCompletionDate, student.qualificationStatus, student.qualificationCountry,
				student.qualificationState, student.qualificationCity, getTestDataPath(student.qualificationImage));
		logger.info("Qualification Information submitted successfully");

		logger.info("===== FULL ACADEMICS INFORMATION FLOW COMPLETED =====");
	}
}

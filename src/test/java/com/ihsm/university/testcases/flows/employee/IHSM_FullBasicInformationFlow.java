package com.ihsm.university.testcases.flows.employee;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.employee.basicinformation.BasicInfo_BiometricsInformation;
import com.ihsm.university.pageobjects.employee.basicinformation.BasicInfo_EnrollnmentInformation;
import com.ihsm.university.pageobjects.employee.basicinformation.BasicInfo_GuardianInformation;
import com.ihsm.university.pageobjects.employee.basicinformation.BasicInfo_LanguageInformation;
import com.ihsm.university.pageobjects.employee.basicinformation.BasicInfo_PersonalInformation;
import com.ihsm.university.pageobjects.employee.basicinformation.BasicInfo_VaccinationInformation;

public class IHSM_FullBasicInformationFlow extends BaseClass {

	public void execute() throws Exception {
		logger.info("===== STARTING FULL BASIC INFORMATION FLOW =====");

		// --------------------------BASIC INFORMATION FLOW -----------
		// Enrollnment Information
		logger.info("Filling Employee Enrollnment Information...");
		BasicInfo_EnrollnmentInformation enrollInfo = new BasicInfo_EnrollnmentInformation(getDriver());
		enrollInfo.fillEnrollnmentInformationForm("jfadk", "Nia", "Female", "412131", "nia123@gmail.com", "Swizz");
		logger.info("Employee Enrollnment Information submitted successfully");

		// Personal Information
		logger.info("Filling Employee Personal Information...");
		BasicInfo_PersonalInformation personalInfo = new BasicInfo_PersonalInformation(getDriver());
		personalInfo.fillPersonalInformationForm("kjf", "Nia", "4512", "8454", "01012000", "Female", "Single",
				"01012026", "nia49613@gmail.com", "91", "9813981398", "India",
				"Other Address Line 1, Other Address Line 2, City, State, 123456");
		logger.info("Employee Personal Information submitted successfully");

		// Guardian Information
		logger.info("Filling Employee Guardian Information...");
		BasicInfo_GuardianInformation guardianInfo = new BasicInfo_GuardianInformation(getDriver());
		guardianInfo.fillGuardianInformationForm("Nothing", "Ramesh Kumar", "01011970", "No");
		logger.info("Employee Guardian Information submitted successfully");

		// Language Information
		logger.info("Filling Employee Language Information...");
		BasicInfo_LanguageInformation languageInfo = new BasicInfo_LanguageInformation(getDriver());
		languageInfo.fillLanguageInformation("сертификат Duolingo", "B2");
		logger.info("Employee Language Information submitted successfully");

		// Vaccination Information
		logger.info("Filling Employee Vaccination Information...");
		BasicInfo_VaccinationInformation vaccinationInfo = new BasicInfo_VaccinationInformation(getDriver());
		vaccinationInfo.fillVaccinationForm("kuch bi", "Phases", "4851", "01012026", "Remarks");
		logger.info("Employee Vaccination Information submitted successfully");

		// Biometrics Information
		logger.info("Filling Employee Biometrics Information...");
		BasicInfo_BiometricsInformation biometricsInfo = new BasicInfo_BiometricsInformation(getDriver());
		biometricsInfo.fillBiometricsInfo(getTestDataPath("male.png"));
		logger.info("Employee Biometrics Information submitted successfully");

		logger.info("===== FULL BASIC INFORMATION FLOW COMPLETED =====");
	}

}

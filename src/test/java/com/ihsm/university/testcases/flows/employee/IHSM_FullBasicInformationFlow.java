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
		enrollInfo.fillEnrollnmentInformationForm(TestDataGenerator.generateRandomRussianFirstName(), TestDataGenerator.generateRandomFirstName(),
				TestDataGenerator.generateRandomGender(), TestDataGenerator.randomNumber(5), TestDataGenerator.randomEmail(),
				TestDataGenerator.randomCountry());
		logger.info("Employee Enrollnment Information submitted successfully");

		// Personal Information
		logger.info("Filling Employee Personal Information...");
		BasicInfo_PersonalInformation personalInfo = new BasicInfo_PersonalInformation(getDriver());
		personalInfo.fillPersonalInformationForm(TestDataGenerator.randomString(4), TestDataGenerator.randomString(3),
				TestDataGenerator.randomNumber(4), TestDataGenerator.randomNumber(5), "01012000", TestDataGenerator.generateRandomGender(),
				TestDataGenerator.randomMaritalStatus(), "01012026", "91", TestDataGenerator.randomPhone(), TestDataGenerator.randomIndianAddress(),
				TestDataGenerator.randomIndianAddress());
		logger.info("Employee Personal Information submitted successfully");

		// Guardian Information
		logger.info("Filling Employee Guardian Information...");
		BasicInfo_GuardianInformation guardianInfo = new BasicInfo_GuardianInformation(getDriver());
		guardianInfo.fillGuardianInformationForm(TestDataGenerator.randomGuardian(), TestDataGenerator.randomGuardianName(), "01011970", "No");
		logger.info("Employee Guardian Information submitted successfully");

		// Language Information
		logger.info("Filling Employee Language Information...");
		BasicInfo_LanguageInformation languageInfo = new BasicInfo_LanguageInformation(getDriver());
		languageInfo.fillLanguageInformation("сертификат Duolingo", "B2");
		logger.info("Employee Language Information submitted successfully");

		// Vaccination Information
		logger.info("Filling Employee Vaccination Information...");
		BasicInfo_VaccinationInformation vaccinationInfo = new BasicInfo_VaccinationInformation(getDriver());
		vaccinationInfo.fillVaccinationForm(TestDataGenerator.randomVaccinationType(), TestDataGenerator.randomVaccinationPhase(),
				TestDataGenerator.randomNumber(5), "01012026", TestDataGenerator.randomNotes());
		logger.info("Employee Vaccination Information submitted successfully");

		// Biometrics Information
		logger.info("Filling Employee Biometrics Information...");
		BasicInfo_BiometricsInformation biometricsInfo = new BasicInfo_BiometricsInformation(getDriver());
		biometricsInfo.fillBiometricsInfo(TestDataGenerator.randomEmployeePhotoFile());
		logger.info("Employee Biometrics Information submitted successfully");

		logger.info("===== FULL BASIC INFORMATION FLOW COMPLETED =====");
	}

}

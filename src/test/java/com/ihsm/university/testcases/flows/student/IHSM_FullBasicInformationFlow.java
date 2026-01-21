package com.ihsm.university.testcases.flows.student;

import org.testng.annotations.Test;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_Biometrics;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_EnrollnmentInformation;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_FamilyInformation;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_GeneralInformation_Prerights;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_GeneralInformation_SocialStatus;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_GeneralInformation_SocialWorkLocation;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_LanguageInformation;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_MedicalInforamtion_AtPoly;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_MedicalInformation_Disability;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_MedicalInformation_Insurance;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_MedicalInformation_Vaccination;
import com.ihsm.university.pageobjects.basicinformation.BasicInfo_PersonalInformation;

public class IHSM_FullBasicInformationFlow extends BaseClass {

	@Test
	public void execute() {
		logger.info("===== STARTING FULL BASIC INFORMATION FLOW =====");

		logger.info("Filling Enrollment Information...");
		// ------------------ ENROLLMENT INFORMATION ------------------
		BasicInfo_EnrollnmentInformation enrollInfo = new BasicInfo_EnrollnmentInformation(getDriver());
		enrollInfo.fillEnrollmentInformation("Term-1", "CENTRAL / Bachelor / MBBS", "1", "1", "784512", "Rohan",
				"Singh", "Kumar", "Male", "01/01/2000", "India", "Haryana (HR)", "9090909090", "rohan@gmail.com",
				"India");
		logger.info("Enrollment Information submitted successfully");

		logger.info("Filling Personal Information...");
		// ------------------ PERSONAL INFORMATION ------------------
		BasicInfo_PersonalInformation personalInfo = new BasicInfo_PersonalInformation(getDriver());
		personalInfo.fillPersonalInformationForm("Амит", "Джангра", "Panipat", "Single", "India");
		logger.info("Personal Information submitted successfully");

		logger.info("Filling Biometrics Information...");
		// ------------------ BIOMETRICS ------------------
		BasicInfo_Biometrics biometrics = new BasicInfo_Biometrics(getDriver());
		biometrics.fillBiometricsInfo(getTestDataPath("male.png"));
		logger.info("Biometrics Information submitted successfully");

		logger.info("Filling Family Information...");
		// ------------------ FAMILY INFORMATION ------------------
		BasicInfo_FamilyInformation familyInfo = new BasicInfo_FamilyInformation(getDriver());
		familyInfo.fillFamilyInformation("Father", "Mukesh Kumar", "01/01/1970", "Business", "91", "454545245", "No",
				"India", "Haryana (HR)", "Panipat", "India");
		logger.info("Family Information submitted successfully");

		logger.info("Filling Language Information...");
		// ------------------ LANGUAGE INFORMATION ------------------
		BasicInfo_LanguageInformation languageInfo = new BasicInfo_LanguageInformation(getDriver());
		languageInfo.fillLanguageInformationForm("English", "B2");
		logger.info("Language Information submitted successfully");

		logger.info("Filling General Pre Rights Information...");
		// ------------------ GENERAL INFORMATION ------------------
		BasicInfo_GeneralInformation_Prerights prerightsInfo = new BasicInfo_GeneralInformation_Prerights(getDriver());
		prerightsInfo.fillPreferRightsInformation("rights", getTestDataPath("male.png"));
		logger.info("General Pre Rights Information submitted successfully");

		logger.info("Filling General Social Status Information...");
		BasicInfo_GeneralInformation_SocialStatus socialInfo = new BasicInfo_GeneralInformation_SocialStatus(
				getDriver());
		socialInfo.fillSocialStatusForm("value", getTestDataPath("male.png"));
		logger.info("General Social Status Information submitted successfully");

		logger.info("Filling General Student Work Location Information...");
		BasicInfo_GeneralInformation_SocialWorkLocation socialWorkInfo = new BasicInfo_GeneralInformation_SocialWorkLocation(
				getDriver());
		socialWorkInfo.fillSocialWorkLocationDetails(getTestDataPath("male.png"));
		logger.info("General Student Work Location Information submitted successfully");

		logger.info("General Information submitted successfully");

		logger.info("Filling Medical Information...");
		// ------------------ MEDICAL INFORMATION ------------------
		BasicInfo_MedicalInformation_Vaccination medicalInfo = new BasicInfo_MedicalInformation_Vaccination(
				getDriver());
		medicalInfo.fillVaccinationInfo("dose 1", "3", "01/01/2026", "562", "Thank You", getTestDataPath("male.png"));
		logger.info("Medical Information submitted successfully");

		logger.info("Filling Medical Information Poly details..............");
		BasicInfo_MedicalInforamtion_AtPoly medicalPolyInfo = new BasicInfo_MedicalInforamtion_AtPoly(getDriver());
		medicalPolyInfo.fillAtPolyMedicalInformation("01/02/2026", "Poly type", getTestDataPath("male.png"));
		logger.info("Medical Information Poly details filled successfully..............");

		logger.info("Filling Medical Insurance details..............");
		BasicInfo_MedicalInformation_Insurance medicalInsuranceInfo = new BasicInfo_MedicalInformation_Insurance(
				getDriver());
		medicalInsuranceInfo.fillInsuranceInformation("01/01/2026", "01/01/2027", getTestDataPath("male.png"));
		logger.info("Medical Insurance details filled successfully..............");

		logger.info("Filling Medical Disability details..............");
		BasicInfo_MedicalInformation_Disability medicalDisabilityInfo = new BasicInfo_MedicalInformation_Disability(
				getDriver());
		medicalDisabilityInfo.fillDisabilityForm("adhd", "5611", "01/01/2026", getTestDataPath("male.png"));
		logger.info("Medical Disability details filled successfully..............");

		logger.info("===== FULL BASIC INFORMATION FLOW COMPLETED =====");
	}

}

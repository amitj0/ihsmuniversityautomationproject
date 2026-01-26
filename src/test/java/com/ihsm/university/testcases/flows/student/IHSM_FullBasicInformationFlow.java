package com.ihsm.university.testcases.flows.student;

import org.openqa.selenium.WebDriver;
import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.student.basicinformation.*;

public class IHSM_FullBasicInformationFlow extends BaseClass {

	private WebDriver driver;

	public IHSM_FullBasicInformationFlow(WebDriver driver) {
		this.driver = driver;
	}

	public void execute(StudentFullRegistrationDataVariables student) {
		logger.info("===== STARTING FULL BASIC INFORMATION FLOW =====");

		// ---------------- Enrollment Information ----------------
		BasicInfo_EnrollnmentInformation enrollInfo = new BasicInfo_EnrollnmentInformation(getDriver());
		enrollInfo.fillEnrollmentInformation(student.term, student.course, student.year, student.semester, student.pin,
				student.firstName, student.middleName, student.lastName, student.gender, student.dob, student.country,
				student.state, student.mobile, student.email, student.nationality);
		logger.info("Enrollment Information submitted successfully");

		// ---------------- Personal Information ----------------
		BasicInfo_PersonalInformation personalInfo = new BasicInfo_PersonalInformation(getDriver());
		personalInfo.fillPersonalInformationForm(student.firstName2, student.lastName2, student.city,
				student.maritalStatus, student.country2);
		logger.info("Personal Information submitted successfully");

		// ---------------- Biometrics ----------------
		BasicInfo_Biometrics biometrics = new BasicInfo_Biometrics(getDriver());
		biometrics.fillBiometricsInfo(getTestDataPath(student.biometricsImage));
		logger.info("Biometrics Information submitted successfully");

		// ---------------- Family Information ----------------
		BasicInfo_FamilyInformation familyInfo = new BasicInfo_FamilyInformation(getDriver());
		familyInfo.fillFamilyInformation(student.relation, student.familyName, student.familyDob, student.occupation,
				student.countryCode, student.phone, student.dependent, student.famCountry, student.famState,
				student.famCity, student.famNationality);
		logger.info("Family Information submitted successfully");

		// ---------------- Language Information ----------------
		BasicInfo_LanguageInformation languageInfo = new BasicInfo_LanguageInformation(getDriver());
		languageInfo.fillLanguageInformationForm(student.language, student.languageLevel);
		logger.info("Language Information submitted successfully");

		// ---------------- Pre Rights Information ----------------
		BasicInfo_GeneralInformation_Prerights prerightsInfo = new BasicInfo_GeneralInformation_Prerights(getDriver());
		prerightsInfo.fillPreferRightsInformation(student.preRights, getTestDataPath(student.preRightsImage));
		logger.info("Pre Rights Information submitted successfully");

		// ---------------- Social Status ----------------
		BasicInfo_GeneralInformation_SocialStatus socialInfo = new BasicInfo_GeneralInformation_SocialStatus(
				getDriver());
		socialInfo.fillSocialStatusForm(student.socialStatus, getTestDataPath(student.socialStatusImage));
		logger.info("Social Status Information submitted successfully");

		// ---------------- Work Location ----------------
		BasicInfo_GeneralInformation_SocialWorkLocation socialWorkInfo = new BasicInfo_GeneralInformation_SocialWorkLocation(
				getDriver());
		socialWorkInfo.fillSocialWorkLocationDetails(getTestDataPath(student.workLocationImage));
		logger.info("Work Location Information submitted successfully");

		// ---------------- Medical - Vaccination ----------------
		BasicInfo_MedicalInformation_Vaccination medicalInfo = new BasicInfo_MedicalInformation_Vaccination(
				getDriver());
		medicalInfo.fillVaccinationInfo(student.vacDose, student.vacNumber, student.vacDate, student.vacCode,
				student.vacRemarks, getTestDataPath(student.vacImage));

		// ---------------- Medical - At Poly ----------------
		BasicInfo_MedicalInforamtion_AtPoly medicalPolyInfo = new BasicInfo_MedicalInforamtion_AtPoly(getDriver());
		medicalPolyInfo.fillAtPolyMedicalInformation(student.polyDate, student.polyType,
				getTestDataPath(student.polyImage));

		// ---------------- Medical - Insurance ----------------
		BasicInfo_MedicalInformation_Insurance medicalInsuranceInfo = new BasicInfo_MedicalInformation_Insurance(
				getDriver());
		medicalInsuranceInfo.fillInsuranceInformation(student.insStartDate, student.insEnd,
				getTestDataPath(student.insImage));

		// ---------------- Medical - Disability ----------------
		BasicInfo_MedicalInformation_Disability medicalDisabilityInfo = new BasicInfo_MedicalInformation_Disability(
				getDriver());
		medicalDisabilityInfo.fillDisabilityForm(student.disType, student.disCode, student.disDate,
				getTestDataPath(student.disImage));

		logger.info("===== FULL BASIC INFORMATION FLOW COMPLETED =====");
	}
}

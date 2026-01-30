package com.ihsm.university.testcases.flows.student;

import org.openqa.selenium.WebDriver;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.student.documents.*;

public class IHSM_FullDocumentsFlow extends BaseClass {

	private WebDriver driver;

	public IHSM_FullDocumentsFlow(WebDriver driver) {
		this.driver = driver;
	}

	public void execute(StudentFullRegistrationDataVariables student) {
		logger.info("===== STARTING FULL DOCUMENTS FLOW =====");

		Documents_OtherDocuments otherDocs = new Documents_OtherDocuments(getDriver());
		otherDocs.fillOtherDocumentsForm(student.otherDocumentName, getTestDataPath(student.otherDocumentImage));
		logger.info("Other Documents Information submitted successfully");

		Documents_IdentificationCard idCard = new Documents_IdentificationCard(getDriver());
		idCard.fillIdentificationCardDetails(student.idNumber, student.idCountry, student.idIssueDate,
				student.idExpiryDate, getTestDataPath(student.idImage));
		logger.info("Identification Card Information submitted successfully");

		/*
		 * Documents_VisaInfo_OffVisa visaInfo = new
		 * Documents_VisaInfo_OffVisa(getDriver());
		 * visaInfo.fillVisaInfoOffVisaForm(student.visaType, student.visaPlaceOfIssue,
		 * student.visaIssueDate, student.visaStartDate, student.visaEndDate,
		 * student.visaRenewDate, student.visaNumber, student.visaCountry,
		 * student.visaRemarks, getTestDataPath(student.visaImage));
		 * logger.info("Visa Offline Information submitted successfully");
		 * 
		 * Documents_VisaInfo_OnVisa visaOnlineInfo = new
		 * Documents_VisaInfo_OnVisa(getDriver());
		 * visaOnlineInfo.fillOnlineVisaInfo(student.visaOnlineType,
		 * student.visaOnlineStartDate, student.visaOnlineIssueDate,
		 * student.visaOnlineEndDate, student.visaOnlineNumber);
		 * logger.info("Visa Online Information submitted successfully");
		 * 
		 * Documents_VisaInfo_Register visaRegisterInfo = new
		 * Documents_VisaInfo_Register(getDriver());
		 * visaRegisterInfo.fillRegisterInfo(student.visaRegisterPlace,
		 * student.visaRegisterCountry, student.visaRegisterDate,
		 * student.visaRegisterRemarks);
		 * logger.info("Visa Register Information submitted successfully");
		 * 
		 * Documents_VisaInfo_PassportLocation visaPassportLocInfo = new
		 * Documents_VisaInfo_PassportLocation(getDriver());
		 * visaPassportLocInfo.fillPassportLocationInfo(student.passportLocation,
		 * student.passportLocationDate);
		 * logger.info("Visa Passport Location Information submitted successfully");
		 */

		Documents_PassportInformation passportInfo = new Documents_PassportInformation(getDriver());
		passportInfo.fillPassportInformation(student.passportNumber, student.passportIssuePlace,
				student.passportIssueDate, student.passportExpiryDate);
		logger.info("Passport Information submitted successfully");

		logger.info("===== FULL DOCUMENTS FLOW COMPLETED =====");
	}
}

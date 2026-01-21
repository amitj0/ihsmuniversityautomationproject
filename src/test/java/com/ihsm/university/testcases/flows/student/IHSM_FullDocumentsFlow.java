package com.ihsm.university.testcases.flows.student;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.documents.Documents_IdentificationCard;
import com.ihsm.university.pageobjects.documents.Documents_OtherDocuments;
import com.ihsm.university.pageobjects.documents.Documents_PassportInformation;
import com.ihsm.university.pageobjects.documents.Documents_VisaInfo_OffVisa;
import com.ihsm.university.pageobjects.documents.Documents_VisaInfo_OnVisa;
import com.ihsm.university.pageobjects.documents.Documents_VisaInfo_PassportLocation;
import com.ihsm.university.pageobjects.documents.Documents_VisaInfo_Register;

public class IHSM_FullDocumentsFlow extends BaseClass {

	public void execute() throws Exception {
		logger.info("===== STARTING FULL DOCUMENTS FLOW =====");

		logger.info("Filling Documents Information...");
		Documents_OtherDocuments otherDocs = new Documents_OtherDocuments(getDriver());
		otherDocs.fillOtherDocumentsForm("Passport Front", getTestDataPath("male.png"));
		logger.info("Documents Information submitted successfully");

		logger.info("Filling Identification Documents Information...");
		Documents_IdentificationCard idCard = new Documents_IdentificationCard(getDriver());
		idCard.fillIdentificationCardDetails("234411", "India", "01/01/2026", "01/01/2027",
				getTestDataPath("male.png"));
		logger.info("Identification Documents Information submitted successfully");

		// visa information pending
		logger.info("Filling Visa Offline Information...");
		Documents_VisaInfo_OffVisa visaInfo = new Documents_VisaInfo_OffVisa(getDriver());
		visaInfo.fillVisaInfoOffVisaForm("10 Month Single", "Home Country", "01/01/2026", "01/01/2026", "01/01/2028",
				"01/01/2027", "414613", "India", "Thank You.", getTestDataPath("male.png"));
		logger.info("Visa Information submitted successfully");

		logger.info("Filling Visa Online Information...");
		Documents_VisaInfo_OnVisa visaOnlineInfo = new Documents_VisaInfo_OnVisa(getDriver());
		visaOnlineInfo.fillOnlineVisaInfo("11 Month Single", "01/01/2025", "01/01/2026", "01/01/2029", "461461");
		logger.info("Visa Online Information submitted successfully");

		logger.info("Filling Visa Register Information again...");
		Documents_VisaInfo_Register visaRegisterInfo = new Documents_VisaInfo_Register(getDriver());
		visaRegisterInfo.fillRegisterInfo("Home Country", "India", "01/01/2026", "Thank you.");
		logger.info("Visa Register Information submitted successfully");

		logger.info("Filling Visa Passport Location Information again...");
		Documents_VisaInfo_PassportLocation visaPassportLocInfo = new Documents_VisaInfo_PassportLocation(getDriver());
		visaPassportLocInfo.fillPassportLocationInfo("Ambala", "01/01/2026");
		logger.info("Visa Passport Location Information submitted successfully");

		logger.info("Filling Passport Documents Information...");
		Documents_PassportInformation passportInfo = new Documents_PassportInformation(getDriver());
		passportInfo.fillPassportInformation("MNBA234323", "Gurgaon", "01/01/2026", "01/01/2027");
		logger.info("Passport Documents Information submitted successfully");

		logger.info("===== FULL DOCUMENTS FLOW COMPLETED =====");
	}

}

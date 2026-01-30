package com.ihsm.university.testcases.flows.employee;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.employee.designation.Designation_EmploymentRights;
import com.ihsm.university.pageobjects.employee.designation.Designation_Position;
import com.ihsm.university.pageobjects.employee.documents.Documents_Documents;
import com.ihsm.university.pageobjects.employee.documents.Documents_Passport;

public class IHSM_FullDocumentsFlow extends BaseClass {

	public void execute() throws Exception {
		logger.info("===== STARTING FULL DOCUMENTS INFORMATION FLOW =====");

		// --------------------------DOCUMENTS INFORMATION FLOW -----------
		// Documents
		logger.info("Filling Documents Information...");
		Documents_Documents docInfo = new Documents_Documents(getDriver());
		docInfo.fillDocumentInformation(TestDataGenerator.randomDocumentType(), TestDataGenerator.randomNotes(), TestDataGenerator.randomPhotoFile());
		logger.info("Documents Information submitted successfully");

		logger.info("Filling Passport Information.....");
		Documents_Passport docPassInfo = new Documents_Passport(getDriver());
		docPassInfo.fillPassportDetails(TestDataGenerator.randomPassportType(), TestDataGenerator.randomPassportCountry(),
				TestDataGenerator.randomIssueAgency(), TestDataGenerator.randomNumber(5), TestDataGenerator.randomPassportNumber(),
				TestDataGenerator.randomCountry(), "01012026", "01012027");
		logger.info("Passport Information submitted successfully");

		logger.info("===== FULL DESIGNATION INFORMATION FLOW COMPLETED =====");
	}

}

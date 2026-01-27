package com.ihsm.university.testcases.flows.employee;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_ProfessionalInfoAcademics;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_ProfessionalInfoDegreeLvl;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_ProfessionalInfoTitle;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_ProfessionalInfo_Military;

public class IHSM_FullProfessionalInformation extends BaseClass {

	public void execute() throws Exception {
		logger.info("===== STARTING FULL PROFESSIONAL INFORMATION FLOW =====");

		// --------------------------PROFESSIONAL INFORMATION FLOW -----------
		logger.info("Filling Professional Information...");
		ProfInfo_ProfessionalInfoDegreeLvl profInfo = new ProfInfo_ProfessionalInfoDegreeLvl(getDriver());
		profInfo.fillProfessionalInformationForm(TestDataGenerator.randomString(4), "Psychology",
				TestDataGenerator.randomString(5), TestDataGenerator.randomNumber(4), "01012026", "01012027",
				"Thank You");
		logger.info("Professional Information submitted successfully");

		ProfInfo_ProfessionalInfoAcademics profAcadInfo = new ProfInfo_ProfessionalInfoAcademics(getDriver());
		profAcadInfo.fillAcademicInfoForm(TestDataGenerator.randomString(5), "Сертификат", "01012026", "01012027",
				TestDataGenerator.randomString(5), TestDataGenerator.randomString(4), TestDataGenerator.randomString(4),
				TestDataGenerator.randomNumber(4), "01012028", "Thank you");
		logger.info("Professional Academic Information submitted successfully");

		ProfInfo_ProfessionalInfoTitle profTitleInfo = new ProfInfo_ProfessionalInfoTitle(getDriver());
		profTitleInfo.fillTitleForm(TestDataGenerator.randomString(4), TestDataGenerator.randomString(5),
				TestDataGenerator.randomNumber(5), "01012026", "Thank you", getTestDataPath("doc2.jpg"));
		logger.info("Professional Title Information submitted successfully");

		ProfInfo_ProfessionalInfo_Military profMilitaryInfo = new ProfInfo_ProfessionalInfo_Military(getDriver());
		profMilitaryInfo.fillMilitaryInformationForm(TestDataGenerator.randomString(5),
				TestDataGenerator.randomNumber(4), "01012026", "Thank You");
		logger.info("Professional Military Information submitted successfully");

		logger.info("===== FULL PROFESSIONAL INFORMATION FLOW COMPLETED =====");
	}

}

package com.ihsm.university.testcases.flows.employee;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_ProfessionalInfoAcademics;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_ProfessionalInfoDegreeLvl;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_ProfessionalInfoTitle;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_DevResearch_Attestations;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_DevResearch_Patent;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_DevResearch_Rewards;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_DevResearch_SciResearch;
import com.ihsm.university.pageobjects.employee.professionalinformation.ProfInfo_Military;

public class IHSM_FullProfessionalInformation extends BaseClass {

	public void execute() throws Exception {
		logger.info("===== STARTING FULL PROFESSIONAL INFORMATION FLOW =====");

		// --------------------------PROFESSIONAL INFORMATION FLOW -----------
		logger.info("Filling Professional Information...");
		ProfInfo_ProfessionalInfoDegreeLvl profInfo = new ProfInfo_ProfessionalInfoDegreeLvl(getDriver());
		profInfo.fillProfessionalInformationForm(TestDataGenerator.randomDegreeLevel(), TestDataGenerator.randomSphere(),
				TestDataGenerator.randomUniversity(), TestDataGenerator.randomNumber(4), "01012026", "01012027",
				TestDataGenerator.randomNotes());
		logger.info("Professional Information submitted successfully");

		ProfInfo_ProfessionalInfoAcademics profAcadInfo = new ProfInfo_ProfessionalInfoAcademics(getDriver());
		profAcadInfo.fillAcademicInfoForm(TestDataGenerator.randomAcademicDegree(), "Сертификат", "01012026", "01012027",
				TestDataGenerator.randomAcademicDegree(), TestDataGenerator.randomUniversity(), TestDataGenerator.randomSpeciality(),
				TestDataGenerator.randomNumber(4), "01012028", TestDataGenerator.randomNotes());
		logger.info("Professional Academic Information submitted successfully");

		ProfInfo_ProfessionalInfoTitle profTitleInfo = new ProfInfo_ProfessionalInfoTitle(getDriver());
		profTitleInfo.fillTitleForm(TestDataGenerator.randomTitle(), TestDataGenerator.randomUniversity(),
				TestDataGenerator.randomNumber(5), "01012026", TestDataGenerator.randomNotes(), TestDataGenerator.randomPhotoFile());
		logger.info("Professional Title Information submitted successfully");

		ProfInfo_DevResearch_SciResearch profSciResearchInfo = new ProfInfo_DevResearch_SciResearch(getDriver());
		profSciResearchInfo.fillDevResearchForm(TestDataGenerator.randomScienceResearchWork(), "01012026",
				TestDataGenerator.randomPublishingLevel(), TestDataGenerator.randomUrl(), TestDataGenerator.randomMagazineName(),
				TestDataGenerator.randomArticleName(), TestDataGenerator.randomAuthors(),
				TestDataGenerator.randomNotes());
		logger.info("Professional Scientific Research Information submitted successfully");

		ProfInfo_DevResearch_Rewards profRewardsInfo = new ProfInfo_DevResearch_Rewards(getDriver());
		profRewardsInfo.fillRewardsForm(TestDataGenerator.randomDocumentType(), "01012026",
				TestDataGenerator.randomDocumentType(), TestDataGenerator.randomNumber(4),
				TestDataGenerator.randomNotes());
		logger.info("Professional Rewards Information submitted successfully");

		ProfInfo_DevResearch_Patent profPatentInfo = new ProfInfo_DevResearch_Patent(getDriver());
		profPatentInfo.fillDevResearchPatentForm(TestDataGenerator.randomPatentType(), TestDataGenerator.randomInvention(),
				TestDataGenerator.randomInventionLevel(), TestDataGenerator.randomAuthors(), TestDataGenerator.randomString(4),
				"01012026", TestDataGenerator.randomNumber(5), TestDataGenerator.randomNotes());
		logger.info("Professional Patent Information submitted successfully");

		ProfInfo_DevResearch_Attestations profAttestationsInfo = new ProfInfo_DevResearch_Attestations(getDriver());
		profAttestationsInfo.fillAttestationsForm("Excellent", "Not Suitable", "Ok", "Appropriate", "01012026",
				TestDataGenerator.randomNotes());
		logger.info("Professional Attestations Information submitted successfully");

		ProfInfo_Military profMilitaryInfo = new ProfInfo_Military(getDriver());
		profMilitaryInfo.fillMilitaryInformationForm(TestDataGenerator.randomMilitaryRank(),
				TestDataGenerator.randomNumber(4), "01012026", TestDataGenerator.randomNotes());
		logger.info("Professional Military Information submitted successfully");

		logger.info("===== FULL PROFESSIONAL INFORMATION FLOW COMPLETED =====");
	}

}

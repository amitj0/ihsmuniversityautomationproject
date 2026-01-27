package com.ihsm.university.testcases.flows.student;

import org.testng.annotations.DataProvider;
import com.ihsm.university.utilities.ExcelUtility;

public class StudentFullRegistrationDataProvider {

	// Define Excel sheet name
	private static final String SHEET_NAME = "studentData";

	@DataProvider(name = "studentData", parallel = true)
	public static Object[][] getStudentData() {
		try {
			// 1️ Load Excel
			ExcelUtility excel = new ExcelUtility("src/test/resources/student_registration.xlsx");

			// 2️ (Optional) Beautify header only once
			excel.formatHeaderRow(SHEET_NAME, 120);
			excel.autoFitColumns(SHEET_NAME, 120);

			// 3️ Read all Excel data
			Object[][] rawData = excel.getSheetData(SHEET_NAME);

			if (rawData.length == 0) {
				throw new RuntimeException("❌ No data found in Excel sheet: " + SHEET_NAME);
			}

			// 4️ Map each row to StudentFullRegistrationDataVariables object
			Object[][] finalData = new Object[rawData.length][1];

			for (int i = 0; i < rawData.length; i++) {
				Object[] row = rawData[i];
				StudentFullRegistrationDataVariables student = new StudentFullRegistrationDataVariables();

				// ---------------- ENROLLMENT INFORMATION ----------------
				student.term = row[0].toString();
				student.course = row[1].toString();
				student.year = row[2].toString();
				student.semester = row[3].toString();
				student.pin = row[4].toString();
				student.firstName = row[5].toString();
				student.middleName = row[6].toString();
				student.lastName = row[7].toString();
				student.gender = row[8].toString();
				student.dob = row[9].toString();
				student.country = row[10].toString();
				student.state = row[11].toString();
				student.mobile = row[12].toString();
				student.email = row[13].toString();
				student.nationality = row[14].toString();

				// ---------------- PERSONAL INFORMATION ----------------
				student.firstName2 = row[15].toString();
				student.lastName2 = row[16].toString();
				student.city = row[17].toString();
				student.maritalStatus = row[18].toString();
				student.country2 = row[19].toString();

				// ---------------- BIOMETRIC INFORMATION ----------------
				student.biometricsImage = row[20].toString();

				// ---------------- FAMILY INFORMATION ----------------
				student.relation = row[21].toString();
				student.familyName = row[22].toString();
				student.familyDob = row[23].toString();
				student.occupation = row[24].toString();
				student.countryCode = row[25].toString();
				student.phone = row[26].toString();
				student.dependent = row[27].toString();
				student.famCountry = row[28].toString();
				student.famState = row[29].toString();
				student.famCity = row[30].toString();
				student.famNationality = row[31].toString();

				// ---------------- LANGUAGE INFORMATION ----------------
				student.language = row[32].toString();
				student.languageLevel = row[33].toString();

				// ---------------- GENERAL INFORMATION ----------------
				student.preRights = row[34].toString();
				student.preRightsImage = row[35].toString();
				student.socialStatus = row[36].toString();
				student.socialStatusImage = row[37].toString();
				student.workLocationImage = row[38].toString();

				// ---------------- MEDICAL INFORMATION ----------------
				student.vacDose = row[39].toString();
				student.vacNumber = row[40].toString();
				student.vacDate = row[41].toString();
				student.vacCode = row[42].toString();
				student.vacRemarks = row[43].toString();
				student.vacImage = row[44].toString();

				student.polyDate = row[45].toString();
				student.polyType = row[46].toString();
				student.polyImage = row[47].toString();

				student.insStartDate = row[48].toString();
				student.insEnd = row[49].toString();
				student.insImage = row[50].toString();

				student.disType = row[51].toString();
				student.disCode = row[52].toString();
				student.disDate = row[53].toString();
				student.disImage = row[54].toString();

				// ---------------- DOCUMENTS INFORMATION ----------------
				student.otherDocumentName = row[55].toString();
				student.otherDocumentImage = row[56].toString();

				student.idNumber = row[57].toString();
				student.idCountry = row[58].toString();
				student.idIssueDate = row[59].toString();
				student.idExpiryDate = row[60].toString();
				student.idImage = row[61].toString();

				student.visaType = row[62].toString();
				student.visaPlaceOfIssue = row[63].toString();
				student.visaIssueDate = row[64].toString();
				student.visaStartDate = row[65].toString();
				student.visaEndDate = row[66].toString();
				student.visaRenewDate = row[67].toString();
				student.visaNumber = row[68].toString();
				student.visaCountry = row[69].toString();
				student.visaRemarks = row[70].toString();
				student.visaImage = row[71].toString();

				student.visaOnlineType = row[72].toString();
				student.visaOnlineStartDate = row[73].toString();
				student.visaOnlineIssueDate = row[74].toString();
				student.visaOnlineEndDate = row[75].toString();
				student.visaOnlineNumber = row[76].toString();

				student.visaRegisterPlace = row[77].toString();
				student.visaRegisterCountry = row[78].toString();
				student.visaRegisterDate = row[79].toString();
				student.visaRegisterRemarks = row[80].toString();

				student.passportLocation = row[81].toString();
				student.passportLocationDate = row[82].toString();
				student.passportNumber = row[83].toString();
				student.passportIssuePlace = row[84].toString();
				student.passportIssueDate = row[85].toString();
				student.passportExpiryDate = row[86].toString();

				// ---------------- ACADEMICS INFORMATION ----------------
				student.lastEducation = row[87].toString();
				student.lastEducationSchool = row[88].toString();
				student.lastEducationStartDate = row[89].toString();
				student.lastEducationEndDate = row[90].toString();
				student.lastEducationGraduationDate = row[91].toString();
				student.lastEducationMarks = row[92].toString();
				student.lastEducationSubject = row[93].toString();
				student.lastEducationPercentage = row[94].toString();
				student.lastEducationImage = row[95].toString();

				student.diplomaCode = row[96].toString();
				student.diplomaNumber = row[97].toString();
				student.diplomaRegistration = row[98].toString();
				student.diplomaStartDate = row[99].toString();
				student.diplomaEndDate = row[100].toString();
				student.diplomaInstitution = row[101].toString();
				student.diplomaRemarks = row[102].toString();
				student.diplomaType = row[103].toString();
				student.diplomaMarks = row[104].toString();
				student.diplomaGraduationDate = row[105].toString();
				student.diplomaImage = row[106].toString();

				student.qualificationType = row[107].toString();
				student.qualificationInstitution = row[108].toString();
				student.qualificationRegistrationNumber = row[109].toString();
				student.qualificationStartDate = row[110].toString();
				student.qualificationEndDate = row[111].toString();
				student.qualificationCompletionDate = row[112].toString();
				student.qualificationStatus = row[113].toString();
				student.qualificationCountry = row[114].toString();
				student.qualificationState = row[115].toString();
				student.qualificationCity = row[116].toString();
				student.qualificationImage = row[117].toString();

				// ---------------- STATUS INFORMATION ----------------
				student.status = row[118].toString();
				student.statusDate = row[119].toString();
				student.statusCode = row[120].toString();
				student.statusRemarks = row[121].toString();
				student.statusImage = row[122].toString();

				// Fill color in Excel based on status
				excel.setCellDataWithRules(SHEET_NAME, i + 1, 118, student.status);

				finalData[i][0] = student;
			}

			return finalData;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Object[0][0];
	}
}

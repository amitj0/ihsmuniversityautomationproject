package com.ihsm.university.utilities;

public class TestResultSummary {
	
	
	// Report Status
	public static int passed;
	public static int failed;
	public static int skipped;

	public static String getStatus() {
		if (failed > 0)
			return "FAILED";
		if (skipped > 0)
			return "SKIPPED";
		return "PASSED";
	}
}

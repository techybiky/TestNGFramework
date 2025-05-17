package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getExtentReports() {
		if (extent == null) {
			String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setReportName("Automation Results");
			reporter.config().setDocumentTitle("Test Results");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Bikramaditya");
		}
		return extent;
	}
}

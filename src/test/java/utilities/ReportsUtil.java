package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportsUtil {

	
	public static ExtentReports extent;
	public static ExtentTest logger;

	
	@BeforeSuite(alwaysRun = true)
	public static void setupReport() {
		String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/Reports/ExtentReport_" + timestamp + ".html";

        // Reporters
        ExtentSparkReporter reporter1 = new ExtentSparkReporter(reportPath);
        ExtentSparkReporter reporter2 = new ExtentSparkReporter("./Reports/LatestReport.html");

        // Configurations
        reporter1.config().setDocumentTitle("Automation Test Report");
        reporter1.config().setReportName("Selenium TestNG Report");
        reporter1.config().setTheme(Theme.DARK);

        reporter2.config().setDocumentTitle("Latest Report");
        reporter2.config().setReportName("Execution Summary");
        reporter2.config().setTheme(Theme.STANDARD);

        // Attach reporters
        extent = new ExtentReports();
        extent.attachReporter(reporter1, reporter2);

		extent.setSystemInfo("Tester", "Amrita");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	
	public void startReporting(String testcaseName) {
		logger = extent.createTest(testcaseName);
		logger.info("Test started: " + testcaseName);
	}
	
	public void stopReporting() {
		extent.flush();
		logger.info("Test completed.");
	}

}

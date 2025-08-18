package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsUtil {

	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest logger;

	
	@BeforeSuite(alwaysRun = true)
	public static void setupReport() {
		String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "/Reports/ExtentReport_" + timestamp + ".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("Selenium TestNG Report");

		extent = new ExtentReports();
		extent.attachReporter(spark);
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

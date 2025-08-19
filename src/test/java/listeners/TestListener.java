package listeners;

import java.io.File;
import java.io.IOException;
import java.util.List;
import testBase.BasePage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utilities.ReportsUtil;

public class TestListener extends ReportsUtil implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ReportsUtil.setupReport(); 

        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        } else {
            System.out.println("No groups included in the test context.");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ReportsUtil.logger = ReportsUtil.extent.createTest(testName);

        String[] groups = result.getMethod().getGroups();
        if (groups.length > 0) {
            logger.assignCategory(groups);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.pass(testName + " passed successfully ✅");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.fail(testName + " failed ❌ because: " + result.getThrowable().getMessage());
        try {
            logger.addScreenCaptureFromPath(takeScreenshot(BasePage.getDriver(), testName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.skip(testName + " was skipped ⚠️");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (BasePage.getDriver() != null) {
            BasePage.getDriver().quit();
        }
        extent.flush();
    }

    public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String ScreenshotPath = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png";
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(ScreenshotPath));
        return ScreenshotPath;
    }

    public static String takeElementScreenshot(WebElement element, String screenshotName) {
        String ScreenshotPath = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png";
        File screenshotFile = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(ScreenshotPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ScreenshotPath;
    }
}

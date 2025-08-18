package testBase;

import java.io.FileInputStream;
import java.net.*;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BasePage {
	protected static WebDriver driver;
	public Properties properties;

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" ,"os" })
	public void setup(String browser, String os) throws IOException {

		// load config file
		FileInputStream file = new FileInputStream(
				"/Users/AutomationTraining/OpencartAutomationFramework/Config/config.properties");
		properties = new Properties();
		properties.load(file);

		
		//on remote machine
		if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);

			} else if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WINDOWS);

			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);

			} else {
				System.out.println("OS not supported: " + os);
				return;
			}

			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");

				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			case "edge":
				capabilities.setBrowserName("edge");

				break;
			}
			
			driver=new RemoteWebDriver(new URL(properties.getProperty("remote_url")), capabilities);

		}
		else {
// on local machine
		// Set up WebDriver based on the browser parameter
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "/Users/amritashaw/Drivers/msedgedriver");
			// WebDriver driver = new EdgeDriver();

			driver = new EdgeDriver(); // Uncomment if EdgeDriver is set up
			break;
		default:
			System.out.println("Browser not supported: " + browser);
			return;
		}}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("APP_URL")); // Use the URL from the properties file
	}

	@AfterMethod(alwaysRun = true)

	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BasePage;

public class TC002_LoginTest extends BasePage {

	// This test case is for logging into the OpenCart website
	@Test(groups = { "regression", "sanity" })
	public void testLogin() {
		
		try{
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(properties.getProperty("EMAIL")); // Use the username from properties file
		loginPage.enterPassword(properties.getProperty("PASSWORD")); // Use the password from properties file
		loginPage.clickLogin();
		
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean expectedTitle=myAccountPage.isMyAccountHeaderDisplayed();
		Assert.assertTrue(expectedTitle, "My Account header is not displayed after login");
		} catch (Exception e) {
			Assert.fail("Login test failed due to an exception: " + e.getMessage());
		}
		
		

}
}

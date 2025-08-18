package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BasePage;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BasePage {

	// This test case is for logging into the OpenCart website using Data-Driven
	// Testing (DDT)
	// It uses data from an Excel file to test multiple login scenarios

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="datadriven")
	public void testLoginDDT(String email, String password, String expectedResult) {

		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean accountHeader = myAccountPage.isMyAccountHeaderDisplayed();

		if (expectedResult.equalsIgnoreCase("valid")) {
			if (accountHeader) {
				System.out.println(" Login successful for: " + email);
				myAccountPage.clickLogout();
			} else {
				Assert.fail("Login failed for: " + email);
			}
		} else if(expectedResult.equalsIgnoreCase("invalid")) {
			if (accountHeader) {
				System.out.println(" Login unsuccessful for: " + email);
				Assert.fail("Login failed for: " + email);
			} else {
				Assert.assertFalse(accountHeader, "Login failed for: " + email);

			}
		}
	}
}

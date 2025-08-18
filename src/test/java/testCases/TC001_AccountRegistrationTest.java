package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BasePage;
import testBase.RandomDataGenerator;

public class TC001_AccountRegistrationTest extends BasePage {

	@Test(groups = { "regression", "sanity" })
	// This test case is for account registration on the OpenCart website
	public void testAccountRegistration() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickRegister();

		AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);

		registrationPage.enterFirstName(RandomDataGenerator.getRandomString(8)); // Generate a random first name
		registrationPage.enterLastName(RandomDataGenerator.getRandomString(8)); // Generate a random last name
		registrationPage.enterEmail(RandomDataGenerator.getRandomAlphaNumeric(8) + "@gmail.com");
		registrationPage.enterTelephone(RandomDataGenerator.getRandomNumber(10));
		String password = RandomDataGenerator.getRandomAlphaNumeric(8);

		registrationPage.enterPassword(password); // Generate a random password
		registrationPage.enterConfirmPassword(password); // Use the same password for confirmation
		registrationPage.checkPrivacyPolicy();
		registrationPage.clickContinue();
		registrationPage.getAccountCreatedMessage();
		AssertJUnit.assertEquals(registrationPage.getAccountCreatedMessage(),
				"Your Account Has Been Created!");
	}

}

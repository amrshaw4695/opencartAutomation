package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BasePage;

public class AccountRegistrationPage extends BasePage {

	// invoking constructor of BasePage
	public AccountRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // This initializes all @FindBy elements
    }

	// Locators for Account Registration Page

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtfirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtlastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement chkPrivacyPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement accountCreatedMessage;

	// Methods for Account Registration Page

	public void enterFirstName(String firstName) {
		txtfirstName.sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		txtlastName.sendKeys(lastName);
	}

	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void enterTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void enterConfirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}

	public void checkPrivacyPolicy() {
		if (!chkPrivacyPolicy.isSelected()) {
			chkPrivacyPolicy.click();
		}
	}

	public void clickContinue() {
		btnContinue.click();
	}

	public String getAccountCreatedMessage() {

		return accountCreatedMessage.getText();

	}
}

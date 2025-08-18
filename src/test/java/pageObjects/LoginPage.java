package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BasePage;

public class LoginPage extends BasePage {

	// Constructor to initialize the LoginPage
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // This initializes all @FindBy elements
	}

	// Add methods specific to the LoginPage here
	// For example, methods to interact with elements on the login page

	// Example method to enter username
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailInput;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement passwordInput;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;

	public void enterEmail(String email) {
		emailInput.sendKeys(email);
	}

	// Example method to enter password

	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	// Example method to click login button

	public void clickLogin() {
		loginButton.click();
	}

}

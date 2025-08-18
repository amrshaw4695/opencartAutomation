package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // This initializes all @FindBy elements
    }

	// Add methods specific to the HomePage here
	// For example, methods to interact with elements on the home page

	// Example method
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement Register;
	
	@FindBy(xpath = "//a[text()='Login'][1]")
	WebElement login;
	
	public void clickMyAccount() {
		myAccount.click();
	}
	
	public void clickRegister() {
		Register.click();
	}
	
	public void clickLogin() {
		login.click();
	}

}

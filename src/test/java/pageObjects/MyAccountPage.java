package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BasePage;

public class MyAccountPage extends BasePage {

	// Constructor to initialize the MyAccountPage

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // This initializes all @FindBy elements
	}

	@FindBy(xpath = "//h2[contains(text(),'My Account')]")
	WebElement myAccountHeader;
	
	@FindBy(xpath = "//a[@class=\"list-group-item\" and text()='Logout']")
	WebElement logoutLink;

	public boolean isMyAccountHeaderDisplayed() {

		try {
			return myAccountHeader.isDisplayed();
		} catch (Exception e) {
			return false; // If the element is not found or not displayed, return false
		}
	}
	
	public void clickLogout() {
		logoutLink.click();
	}

}

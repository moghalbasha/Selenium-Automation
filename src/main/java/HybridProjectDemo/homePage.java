package HybridProjectDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import basePackageForPOM.baseClassPOM;

/**
 * Hello world!
 */
public class homePage extends baseClassPOM {
	

	// create constructor
	public homePage(WebDriver driver) {
		super(driver);
	}

	// create locators
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "//a[contains(text(),'Register')]")
	WebElement register;
	@FindBy(linkText = "Login")
	WebElement login;

	// create action methods

	public void clickOnMyAccount() {
		
		Actions ac= new Actions(driver);
		ac.moveToElement(myAccount).click().perform();
		
	}

	public void clickOnRegister() {
		clickElement(register);
	}
	public void clickOnLogin() {
		clickElement(login);
	}

}

package HybridProjectDemo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePackageForPOM.baseClassPOM;

public class usersHomePage extends baseClassPOM {
	WebDriver d;
	public usersHomePage(WebDriver d) {
		super(d);
	}
	
	//locators
	@FindBy(xpath="//h2[text()='My Account']") WebElement myAccText;
	@FindBy(linkText="Logout") WebElement logout;
	
	//action methods
	public boolean isAccountExists() {
		try {
			WebDriverWait wait= new WebDriverWait(d,Duration.ofSeconds(5));
			return wait.until(ExpectedConditions.visibilityOf(myAccText)).isDisplayed();
			
		}catch (Exception e) {
			return false;
			
		}
		
	}
	public void clickLogOutBtn() {
		clickElement(logout);
	}

}

package HybridProjectDemo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import basePackageForPOM.baseClassPOM;

public class registrationPage extends baseClassPOM {
	
	public registrationPage(WebDriver driver) {
		super(driver);
	}

	// locators
	@FindBy(id = "input-firstname")
	WebElement fname;
	@FindBy(id = "input-lastname")
	WebElement lname;
	@FindBy(id = "input-email")
	WebElement email;
	@FindBy(id = "input-telephone")
	WebElement telephone;
	@FindBy(id = "input-password")
	WebElement password;
	@FindBy(id = "input-confirm")
	WebElement confirmPassword;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement ContinueBtn;
	@FindBy(name = "agree")
	WebElement agreeCheckBox;
	@FindBy(xpath = "//h1[contains(text(),'Your Account Has Been Created')]")
	WebElement successMessage;

	// actionmethods
	public void setfname(String name) {
		fname.sendKeys(name);
	}

	public void setlname(String name) {
		lname.sendKeys(name);
	}

	public void setMail(String Mail) {
		email.sendKeys(Mail);
	}

	public void setPhone(String phone) {
		telephone.sendKeys(phone);
	}

	public void setPassword(String passwor) {
		password.sendKeys(passwor);
	}

	public void confirmPassword(String passwor) {
		confirmPassword.sendKeys(passwor);
	}

	public void clickAgreeBtn() {
		clickElement(agreeCheckBox);
	}

	public void clickContinueBtn() {
		clickElement(ContinueBtn);
	}

	public String getSucessMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		return successMessage.getText();
	}

}

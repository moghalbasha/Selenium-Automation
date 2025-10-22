package HybridProjectDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePackageForPOM.baseClassPOM;

public class loginPage extends baseClassPOM {
	public loginPage(WebDriver drive) {
		super(drive);
	}
	//locators
	@FindBy(id="input-email") WebElement emailInputField;
	@FindBy(id="input-password") WebElement passInputField;
	@FindBy(xpath="//input[@value='Login']") WebElement loginBtn;
	//action methods
	public void setemail(String usersEmail) {
		emailInputField.sendKeys(usersEmail);;
	}
	public void setpass(String usersPass) {
		passInputField.sendKeys(usersPass);
	}
	public void clickLoginBtn() {
		clickElement(loginBtn);
	}


}

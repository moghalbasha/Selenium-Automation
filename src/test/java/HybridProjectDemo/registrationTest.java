package HybridProjectDemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import testBasePackage.baseClassSetUp;

public class registrationTest extends baseClassSetUp {

	@Test(groups= {"Regression"}, retryAnalyzer=utilities.Retry.class)
	public void homepage() {
		homePage hm = new homePage(driver);
		hm.clickOnMyAccount();
		hm.clickOnRegister();

		registrationPage rp = new registrationPage(driver);
		String emailid=RandomStringGenerator() + "@" + RandomStringGenerator() + "." + RandomStringGenerator();
		String password = RandomStringGenerator();
		
		logger.info("entering data");
		

		rp.setfname(RandomStringGenerator());
		rp.setlname(RandomStringGenerator());
		rp.setMail(emailid);
		System.out.println(emailid);
		
		rp.setPhone(randomNum());
		rp.setPassword(password);
		rp.confirmPassword(password);
		System.out.println(password);
		rp.clickAgreeBtn();
		rp.clickContinueBtn();
		logger.info("validating sucess data");
		String SucessMessage = rp.getSucessMessage();
		logger.info("account created");
		Assert.assertEquals(SucessMessage, "Your Account Has Been Created!");
		

	}

}

package HybridProjectDemo;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testBasePackage.baseClassSetUp;

public class loginTest extends baseClassSetUp{
	
	@Test(groups= {"Regression","sanity"})
	public void testlogin() {
		logger.info("Logger intialized");
		homePage hm=new homePage(driver);
		logger.info("Clicked on my account link");
		hm.clickOnMyAccount();
		logger.info("Clicked on my login link");
		hm.clickOnLogin();
		loginPage lp=new loginPage(driver);
		logger.info("entered email");
		lp.setemail(prop.getProperty("emailID"));
		logger.info("entered password");
		lp.setpass(prop.getProperty("password"));
		logger.info("Clicked on my signin link");
		lp.clickLoginBtn();
		usersHomePage up=new usersHomePage(driver);
		logger.info("Checking if account exists");
		SoftAssert sa=new SoftAssert();
		try {
			Boolean exists=up.isAccountExists();
			if(exists) {
				sa.assertTrue(exists,"Account found");
			}else {
				sa.assertTrue(exists,"invalid account");
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			logger.info("some error");
		}
		
		sa.assertAll();
		logger.info("Clicked on logout link");
		up.clickLogOutBtn();

	}

}

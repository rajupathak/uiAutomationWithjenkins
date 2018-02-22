package uiAutomationWithjenkins.PaymentJourney;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import uiAutomationWithjenkins.Data.TestDataInputs;
import uiAutomationWithjenkins.TestBase.TestBase;
import uiAutomationWithjenkins.uiActions.ContactManagementPage;
import uiAutomationWithjenkins.uiActions.HomePage;

public class TC01_VerifyPayMentJourney extends TestBase {
	public static final Logger log = Logger.getLogger(TC01_VerifyPayMentJourney.class.getName());

	HomePage homepage;
	ContactManagementPage contact;
	TestBase obj = new TestBase();

	@BeforeTest
	public void setUp() {

		init();
		obj.getScreenShot("Login Page");
	}

	@Test
		public void verifyAddNewContactJourney() throws InterruptedException {

			log.info("==========Starting VerifyPayMentJourney Test===============");

			homepage = new HomePage(driver);
			contact = new ContactManagementPage(driver);
		
			homepage.login(TestDataInputs.getUserName(),
					TestDataInputs.getPassWord(), driver);
			obj.getScreenShot("Easy Login Cookie  Form");

			homepage.saveCookieDetails();
			
			Assert.assertEquals(homepage.getAccountSumamryText(), "Account summary");
			obj.getScreenShot("Account summary page");
			
			homepage.clickOnLHN("Billing", driver);
		
		  homepage.clickonMakeAPaymentCTA(driver);
		 homepage.clickOnpayAnotherAmoutRadio(driver);
		  homepage.enterTheAmount(driver);
		 
			log.info("==========Finished VerifyPayMentJourney Test===============");
		}

	@AfterTest
	public void endTest() {
		 driver.close();

	}
}

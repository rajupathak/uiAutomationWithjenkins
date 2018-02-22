package uiAutomationWithjenkins.ContactManagement;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import uiAutomationWithjenkins.Data.TestDataInputs;
import uiAutomationWithjenkins.TestBase.TestBase;
import uiAutomationWithjenkins.uiActions.ContactManagementPage;
import uiAutomationWithjenkins.uiActions.HomePage;

public class TC01_VerifyAddnewContact extends TestBase {
	public static final Logger log = Logger
			.getLogger(TC01_VerifyAddnewContact.class.getName());

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

		log.info("==========Starting verifyAddNewContactJourney Test===============");

		homepage = new HomePage(driver);
		contact = new ContactManagementPage(driver);
	
		homepage.login(TestDataInputs.getUserName(),
				TestDataInputs.getPassWord(), driver);
		obj.getScreenShot("Easy Login Cookie  Form");

		homepage.saveCookieDetails();
		
		Assert.assertEquals(homepage.getAccountSumamryText(), "Account summary");
		obj.getScreenShot("Account summary page");
		homepage.clickOnLHN("Account settings", driver);
		
		//Assert.assertEquals(homepage.getManageAccountText(),"Manage your account");
		obj.getScreenShot("Account Setting Taskflow");
		log.info("successfully Navigated to Manage your account Taskflow and the text is :>"
				+ homepage.getManageAccountText());

		contact.navigateToConTactManamentTeaskflow();
		
		contact.enterMemorableWord(TestDataInputs.getMemorableWord());
		obj.getScreenShot("2LA DPA view");
		contact.clickonContinueCTA();
	
		Assert.assertEquals(contact.verifyTaskflowText(), "Contact Management");
		obj.getScreenShot("Contact Management Simple view");
		Assert.assertEquals(contact.verifyExpandedViewLink(), true);

		contact.clickOnExpandedLink(driver);

		
		contact.verifyOwnerFunctinality();
		obj.getScreenShot("Contact Management Expanded view");
		Assert.assertEquals(
				contact.verifyeditContactAccordionForOwnerContact(), false);
		log.info("editContactAccordionForOwnerContact is disablled");
		Assert.assertEquals(
				contact.verifyasssignedNumberAccordionForOwnerContact(), true);

		log.info("AsssignedNumberAccordionForOwnerContact is Enablled");

		Assert.assertEquals(
				contact.verifybilledNumberAccordionForOwnerContact(), true);

		log.info("verifybilledNumberAccordionForOwnerContact is Enablled");

		contact.clickOnAddNewContactCTA();
		
		System.out.println("The Contact Number is:" +TestDataInputs.getContactnumber());
		contact.enterTheContactDetails(TestDataInputs.getFirstName(),
				TestDataInputs.getLastName(), TestDataInputs.getEmail(),
				TestDataInputs.getConfirmEmail(),
				TestDataInputs.getContactnumber(),
				TestDataInputs.getConfirmcontactnumber(),
				TestDataInputs.getCreateUsername());

		Assert.assertEquals(contact.getUsernameAvailabiltyText(),
				"Your username is available");

		contact.clickOnSaveDetailsCTA();
		obj.getScreenShot("Client side error message view");
		log.info("==========Finished verifyAddNewContactJourney Test===============");
	}

	@AfterTest
	public void endTest() {
		// driver.close();

	}
}

package uiAutomationWithjenkins.uiActions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiAutomationWithjenkins.TestBase.TestBase;

public class HomePage extends TestBase {
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	TestBase obj = new TestBase();
	@FindBy(id = "userId")
	WebElement userEmailID;

	@FindBy(id = "passwordLogin")
	WebElement userPassword;

	@FindBy(xpath = "//div[@class='formButtonContainer']/input[@value='Log in now']")
	WebElement loginButton;

	@FindBy(xpath = "//div[@class='formRow line checkbox js jsCheckbox']/label[text()='This isn’t a public computer or device']")
	WebElement firstCheckBox;

	@FindBy(xpath = "//div[@class='formRow line checkbox js jsCheckbox']/label[text()='I understand that anyone who visits My Vodafone when using this device will be able to see my account details']")
	WebElement secondCheckBox;

	@FindBy(xpath = "//div[@class='formButtonContainer']/button[text()='Save login details']")
	WebElement saveCookieCTA;

	@FindBy(xpath = "//div[@class='portlet_accountsummary_v3']/h2[text()='Account summary']")
	WebElement accountSummaryText;

	@FindBy(xpath = "//div/div[@class='portlet_myProfile_V2']/h2[text()='Manage your account']")
	WebElement ManageyouraccountText;

	@FindBy(xpath = ".//*[@id='T:oc_2333892606region1:0:pay-now']")
	WebElement MakeAPayment;

	@FindBy(xpath = "//div[@class='formRow left radio js jsRadio']/label[@class='jsNotMatched']/span[@class='radio']")
	WebElement PayAnotherAmountCTA;

	@FindBy(xpath = "//div[@class='formRow bill-amount left']/input[@type='text']")
	WebElement amount;

	@FindBy(xpath = "//div[@class='formButtonContainer']/button[text()='Pay now']")
	WebElement PayNowCTA;

	@FindBy(xpath = ".//*[@id='T:oc_2333892606region1:1:submit_continue_new']")
	WebElement Continue;
	@FindBy(xpath = "//div[@class='noTab']/iframe[@title='calling external source']")
	WebElement iFrameobject;

	@FindBy(id = "btnCancel")
	WebElement CancelButton;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void login(String userName, String passWord, WebDriver driver) {
		this.driver = driver;
		obj.waitForTheWebElement(userEmailID, driver);
		obj.highLighteBackground(driver, userEmailID);
		userEmailID.clear();
		userEmailID.sendKeys(userName);
		log.info("Entered emailId:-" + userName + "and object is:-"
				+ userEmailID.toString());
		obj.waitForTheWebElement(userPassword, driver);
		obj.highLighteBackground(driver, userPassword);
		userPassword.clear();
		userPassword.sendKeys(passWord);

		log.info("Entered Password:-" + passWord + "and object is:-"
				+ userPassword.toString());
		loginButton.click();
		log.info("clicked on logged and the object is :-"
				+ loginButton.toString());
	}

	public void saveCookieDetails() throws InterruptedException {

		firstCheckBox.click();
		log.info("FirstCheckBox is selected and the object is:-"
				+ firstCheckBox.toString());
		secondCheckBox.click();
		log.info("secondCheckBox is selected and the object is:-"
				+ secondCheckBox.toString());
		saveCookieCTA.click();
		log.info("clicked on saveCookieDetails CTA and the object is :-"
				+ saveCookieCTA.toString());
		Thread.sleep(4000);
		File file = new File("Cookies.data");
		try {
			// Delete old file if exists
			file.delete();
			file.createNewFile();
			FileWriter fileWrite = new FileWriter(file);
			BufferedWriter Bwrite = new BufferedWriter(fileWrite);
			// loop for getting the cookie information
			for (Cookie ck : driver.manage().getCookies()) {
				Bwrite.write((ck.getName() + ";" + ck.getValue() + ";"
						+ ck.getDomain() + ";" + ck.getPath() + ";"
						+ ck.getExpiry() + ";" + ck.isSecure()));
				Bwrite.newLine();
			}
			Bwrite.flush();
			Bwrite.close();
			fileWrite.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getAccountSumamryText() {
		log.info("Account summary text is :-" + accountSummaryText.getText());
		return accountSummaryText.getText();
	}

	public void clickOnLHN(String LHNName, WebDriver driver) {
		this.driver = driver;
		List<WebElement> list = driver
				.findElements(By
						.xpath("//div[@class='grid_3 ']/div[@class='nav-secondary']/ul[@id='leftHandNav']/li/a"));
		// System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {

			WebElement ele = list.get(i);

			String LHNText = list.get(i).getText();
			// System.out.println(LHNText);
			if (LHNText.contains(LHNName)) {

				obj.highLighteBackground(driver, ele);
				ele.click();
				log.info("Clicked on> " + LHNName + ">and the object is"
						+ ele.toString());
				break;
			}

		}

	}

	public String getManageAccountText() {

		String text = ManageyouraccountText.getText();
		return text;

	}

	public void clickonMakeAPaymentCTA(WebDriver driver) {

		obj.getScreenShot("Bill Description Page");
		obj.highLighteBackground(driver, MakeAPayment);
		obj.triggerClickOnWebElement(driver, MakeAPayment);
		log.info("Clicked on MakeAPayment and the object is :>"
				+ MakeAPayment.toString());

	}

	public void clickOnpayAnotherAmoutRadio(WebDriver driver) {
		obj.getScreenShot("Selected radio button");
		obj.highLighteBackground(driver, PayAnotherAmountCTA);
		obj.triggerClickOnWebElement(driver, PayAnotherAmountCTA);
		log.info("Clicked on PayAnotherAmountCTA and the object is :>"
				+ MakeAPayment.toString());

	}

	public void enterTheAmount(WebDriver driver) {
		amount.sendKeys("2");

		log.info("Entered amount object is  :>" + amount.toString());
		obj.triggerClickOnWebElement(driver, PayNowCTA);

		log.info("Clicked on PAY Now CTA and the Object is :>"
				+ PayNowCTA.toString());

		obj.getScreenShot("Payment journey");
		obj.highLighteBackground(driver, Continue);
		Continue.click();
		log.info("Clicked on Continue and the object is :"
				+ Continue.toString());

		driver.switchTo().frame(iFrameobject);
		obj.getScreenShot("3D Secure Page");
		obj.waitForTheWebElement(CancelButton, driver);
		obj.highLighteBackground(driver, CancelButton);
		CancelButton.click();
		driver.switchTo().defaultContent();
		log.info("Clicked on CancelButton and the Object is :>"
				+ CancelButton.toString());
		obj.getScreenShot("PaymentCancelErrorMessage");

	}
}

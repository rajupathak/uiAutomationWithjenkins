package uiAutomationWithjenkins.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiAutomationWithjenkins.TestBase.TestBase;

public class ContactManagementPage extends TestBase {
	TestBase obj = new TestBase();
	public static final Logger log = Logger
			.getLogger(ContactManagementPage.class.getName());
	WebDriver driver;
	@FindBy(xpath = "//div[@class='grid_3 ']/div[@class='nav-secondary']/ul[@id='leftHandNav']/li[@class='current']/ul/li/a[text()='Add someone to your account']")
	WebElement contactManagementLHN;

	@FindBy(xpath = ".//*[@id='T:oc_4078484780region1:0:memorableWord::content']")
	WebElement memorableWord;

	@FindBy(xpath = "//div[@class='formButtonContainer']/button")
	WebElement continueCTA;

	@FindBy(xpath = "//div[@class='portlet_contactManagement']/h2")
	WebElement contactManagementTaskflowText;

	@FindBy(xpath = ".//*[@id='T:oc_4078484780region1:1:showExpandedView']")
	WebElement expandedViewLink;

	@FindBy(xpath = "//div[@class='mod-css-table__row']/div/a/span[text()='Account admin level']")
	WebElement AccountleveladminText;

	@FindBy(xpath = "//div[@class='mod-css-table__row']/div[text()='Ben  Stokes']")
	WebElement OwnerBenStores;

	@FindBy(xpath = "//div[@class='mod-css-table__row']/div[@class='mod-css-table__cell value']/div[@class='formRow radio js jsRadio']/label[@class='owner_contact jsNotMatched']/span[@class='radio']")
	WebElement radio;

	@FindBy(xpath = "//div[@class='mod-css-table__row']/div[text()='Owner']")
	WebElement owner;

	@FindBy(xpath = "//span[@class='x1a']/div[@class='mod-accordion border-top']/div/h3/a[@class='disabled accordionAnchor']")
	WebElement editContactAccordion;

	@FindBy(xpath = "//span[@class='x1a']/div[@class='mod-accordion border-top']/div/h3/a[contains(text(),'assigned')]")
	WebElement asssignedNumberAccordion;

	@FindBy(xpath = "//span[@class='x1a']/div[@class='mod-accordion border-top']/div/h3/a[contains(text(),'billed')]")
	WebElement billedNumberAccordion;
	@FindBy(xpath = "//div[@class='formButtonContainer']/button")
	WebElement addNewcontact;

	@FindBy(xpath = "//div[@class='formRow']/input[@id='T:oc_4078484780region1:firstname']")
	WebElement firstname;

	@FindBy(xpath = "//div[@class='formRow']/input[@id='T:oc_4078484780region1:lastname']")
	WebElement lastname;

	@FindBy(xpath = "//div[@class='formRow']/input[@id='T:oc_4078484780region1:email']")
	WebElement email;

	@FindBy(xpath = "//div[@class='formRow']/input[@id='T:oc_4078484780region1:ConfirmEmail']")
	WebElement ConfirmEmail;

	@FindBy(xpath = ".//*[@id='T:oc_4078484780region1:contactnumber']")
	WebElement contactnumber;

	@FindBy(xpath = ".//*[@id='T:oc_4078484780region1:Confirmcontactnumber']")
	WebElement Confirmcontactnumber;

	@FindBy(xpath = ".//*[@id='T:oc_4078484780region1:createUsername']")
	WebElement createUsername;

	@FindBy(xpath = "//div[@class='formRow']/button[text()='Check availability']")
	WebElement checkAvailability;

	@FindBy(xpath = "//span[@class='x1a']/div[@class='formRow success']/p[text()='Your username is available']")
	WebElement yourUsernameIsAvailableMessage;

	@FindBy(xpath = "//div[@class='formButtonContainer']/button[text()='Save user details']")
	WebElement saveDetailsCTA;

	@FindBy(xpath = "//div[@class='formButtonContainer']/button[text()='Cancel']")
	WebElement CancelCTA;

	public ContactManagementPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	public void navigateToConTactManamentTeaskflow() {

		contactManagementLHN.click();
		log.info("Clicked on LHN and the object is :>"
				+ contactManagementLHN.toString());
	}

	public void enterMemorableWord(String MemorableWord) {
		memorableWord.sendKeys(MemorableWord);
		log.info("Entered the memorable word and the value is :>"
				+ MemorableWord);
	}

	public void clickonContinueCTA() throws InterruptedException {
		continueCTA.click();
		Thread.sleep(5000);
		log.info("Clicked on continueCTA and the object is :>"
				+ continueCTA.toString());
	}

	public String verifyTaskflowText() {
		String text = contactManagementTaskflowText.getText();

		log.info("Successfully navigated to TF and the Text is " + text);
		return text;
	}

	public boolean verifyExpandedViewLink() {
		try {
			expandedViewLink.isDisplayed();
			log.info("expandedViewLink is present and the object is :>"
					+ expandedViewLink.toString());
			return true;
		} catch (Exception e) {
			System.out.println("expandedViewLink is not present");
			return false;
		}
	}

	public void clickOnExpandedLink(WebDriver driver) {
		this.driver = driver;
		obj.highLighteBackground(driver, expandedViewLink);
		expandedViewLink.sendKeys(Keys.ENTER);
		log.info("Clicked on expandedViewLink and the oblect is :>"
				+ expandedViewLink.toString());

	}

	public void verifyOwnerFunctinality() {
		try {
			if (OwnerBenStores.isDisplayed() && owner.isDisplayed()) {
				radio.click();
			}
		} catch (Exception e) {
			System.out.println(OwnerBenStores.toString() + " And "
					+ owner.toString() + " Element are not displayed ");
		}

	}

	public boolean verifyeditContactAccordionForOwnerContact() {
		try {
			editContactAccordion.isEnabled();
			return false;
		}

		catch (Exception e) {
			return true;
		}
	}

	public boolean verifyasssignedNumberAccordionForOwnerContact() {
		try {
			asssignedNumberAccordion.isEnabled();
			return true;
		}

		catch (Exception e) {
			return false;
		}
	}

	public boolean verifybilledNumberAccordionForOwnerContact() {
		try {
			billedNumberAccordion.isEnabled();
			return true;
		}

		catch (Exception e) {
			return false;
		}
	}

	public void clickOnAddNewContactCTA() {
		obj.getScreenShot("Add new Contact Form");
		addNewcontact.click();
	}

	public void enterTheContactDetails(String firstName, String lastName,
			String Email, String Confirmemail, String contactNumber,
			String ConfirmcontactNumber, String createusername) {

		firstname.sendKeys(firstName);
		lastname.sendKeys(lastName);
		email.sendKeys(Email);
		ConfirmEmail.sendKeys(Confirmemail);
		contactnumber.sendKeys(contactNumber);
		Confirmcontactnumber.sendKeys(ConfirmcontactNumber);
		createUsername.sendKeys(createusername);
		log.info("All the valid input are sussessfully entered");
	}

	public String getUsernameAvailabiltyText() {
		String textMesage = null;
		checkAvailability.click();
		try {
			textMesage = yourUsernameIsAvailableMessage.getText();
			return textMesage;
		} catch (Exception e) {
			System.out.println("Username availabilty Message is not displayed");
		}
		return textMesage;

	}

	public void scrollDown() {
		obj.scroolTheWidnow();
	}

	public void clickOnSaveDetailsCTA() {
		saveDetailsCTA.click();

		log.info("Clicked on save Detail CTA and the object is :>"
				+ saveDetailsCTA.toString());
	}
}

package uiAutomationWithjenkins.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import uiAutomationWithjenkins.customListeners.Listener;
import uiAutomationWithjenkins.excelReader.ExcelReader;

public class TestBase {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public static Alert alert = null;
	ExcelReader excelObject;
	 //String url = "https://www.vodafone.co.uk/myvodafone";
	 String url = System.getProperty("url");
	 
	//String browsername = "firefox";
	String browsername = System.getProperty("browserName");

	String log4jConfigPath = "log4j.properties";
	Listener lis;// We have to Initialize the listener object

	public void init() {
		PropertyConfigurator.configure(log4jConfigPath);
		selectBrowser(browsername);
		// lis = new Listener(driver);
		 getURL(url);

	}

	public void selectBrowser(String browsername) {
		log.info("Creating object of " + browsername);
		if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		else if (browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "\\Drivers\\chromedriver.exe");

			driver = new ChromeDriver();
		}

		else if (browsername.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")
							+ "\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		else if (browsername.equalsIgnoreCase("HTML")) {
			driver = new HtmlUnitDriver(true);
		}

	}

	public void getURL(String url) {
		log.info("Navigating to :" + url);
		driver.get(url);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String[][] getData(String excelName, String sheetName) {
		// Since one Excel can have multiple sheet
		// Path contains the Excel Name
		String path = System.getProperty("user.dir")
				+ "\\src\\main\\java\\uiAutomationWithjenkins\\data\\"
				+ excelName;
		excelObject = new ExcelReader(path);
		String data[][] = excelObject.getDataFromSheet(sheetName, excelName);
		return data;
	}

	public String getDatafromSheet(String excelName, String sheetName,
			String colName, int rowNum) {
		// Since one Excel can have multiple sheet
		// Path contains the Excel Name
		String path = System.getProperty("user.dir")
				+ "\\src\\main\\java\\uiAutomationWithjenkins\\data\\"
				+ excelName;
		excelObject = new ExcelReader(path);
		String data = excelObject.getCellData(sheetName, colName, rowNum);
		return data;
	}

	public void getScreenShot(String screenshotName) {

		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		TakesScreenshot tc = (TakesScreenshot) driver;

		File sourceFile = tc.getScreenshotAs(OutputType.FILE);
		System.out.println(sourceFile);

		try {

			String reportDirectory = new File(System.getProperty("user.dir"))
					.getAbsolutePath()
					+ "\\src\\main\\java\\uiAutomationWithjenkins\\screenShots\\";
			System.out.println("Path:=" + reportDirectory);

			File desFile = new File((String) reportDirectory + screenshotName
					+ "_" + formater.format(calender.getTime()) + ".png");
			FileUtils.copyFile(sourceFile, desFile);
			Reporter.log("<a href='" + desFile.getAbsolutePath()
					+ "'><img src='" + desFile.getAbsolutePath()
					+ "' height='100' width='100'/></a>");

		} catch (Exception e) {
			System.out.println("Exception is throwing during screen shot"
					+ e.getMessage());
		}

	}

	public Iterator<String> getAllWindows() {

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;

	}

	public void mouseHover(WebElement element, WebDriver driver) {

		Actions action = new Actions(driver);

		action.moveToElement(element).perform();
	}

	public void waitForTheWebElement(WebElement element, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 40);

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void highLighteBackground(WebDriver driver, WebElement element) {

		js.executeScript("arguments[0].style.border='6px groove yellow'",
				element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].style.border=''", element);

	}

	public void scroolTheWidnow() {
		js.executeScript("scroll(0,1200)");
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}

	}

	public void triggerClickOnWebElement(WebDriver driver, WebElement webElement) {
		if (isAlertPresent(driver)) {
			alert.dismiss();
			waitForTheWebElement(webElement, driver);
			webElement.click();
		} else {
			waitForTheWebElement(webElement, driver);
			webElement.click();
		}
	}

	public void getcookie() throws Exception {

		FileReader file = new FileReader("Cookies.data");
		BufferedReader br = new BufferedReader(file);
		String line;
		while ((line = br.readLine()) != null) {
			StringTokenizer str = new StringTokenizer(line, ";");
			while (str.hasMoreTokens()) {
				String name = str.nextToken();
				String value = str.nextToken();
				String domain = str.nextToken();
				String path = str.nextToken();
				Date expiry = null;
				String dt;
				if (!(dt = str.nextToken()).equals("null")) {

					expiry = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
							.parse(dt);
				}
				boolean isSecure = Boolean.valueOf(str.nextToken())
						.booleanValue();
				Cookie ck = new Cookie(name, value, domain, path, expiry,
						isSecure);

				if (name.equalsIgnoreCase("x-vodafone-solo")) {
					System.out.println("Cookie Name is :" + name);
					driver.get("https://www.vodafone.co.uk/myvodafone/");
					driver.manage().window().maximize();
					// Thread.sleep(1000);
					driver.manage().addCookie(ck);
					// driver.get("https://www.vodafone.co.uk/myvodafone/");
					break;
				}

			}

		}
	}
}

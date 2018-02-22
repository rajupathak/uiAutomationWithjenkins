package uiAutomationWithjenkins.customListeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import uiAutomationWithjenkins.TestBase.TestBase;



public class Listener extends TestBase implements ITestListener {
	/*
	 * WebDriver driver;
	 * 
	 * public Listener(WebDriver driver) { this.driver = driver; }
	 */

	public void onFinish(ITestContext result) {
		//ITestContext is used to get Total Number of passed and Failed script
		Reporter.log("Total Number of passed Script:-" +result.getPassedTests());
		Reporter.log("Total Number of failed Script:-" +result.getFailedTests());
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getName();

		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		if (!result.isSuccess()) {

			File sourceFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			try {

				String reportDirectory = new File(
						System.getProperty("user.dir")).getAbsolutePath()
						+ "/src/main/java/com/test/automation/uiAutomation/";

				File desFile = new File((String) reportDirectory
						+ "/failure_Screenshots/" + methodName + "_"
						+ formater.format(calender.getTime()) + ".png");
				FileUtils.copyFile(sourceFile, desFile);
				Reporter.log("<a href='" + desFile.getAbsolutePath()
						+ "'><img src='" + desFile.getAbsolutePath()
						+ "' height='100' width='100'/></a>");

			} catch (Exception e) {
				System.out.println("Exception is throwing during screen shot"
						+ e.getMessage());
			}
		}

	}

	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test is skipped:" + result.getMethod().getMethodName()+" at: " +result.getStartMillis());

	}

	public void onTestStart(ITestResult result) {
		Reporter.log("Test started running:"
				+ result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getName();

		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		if (result.isSuccess()) {

			File sourceFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			try {

				String reportDirectory = new File(
						System.getProperty("user.dir")).getAbsolutePath()
						+ "/src/main/java/com/test/automation/uiAutomation/";

				File desFile = new File((String) reportDirectory
						+ "/success_Screenshots/" + methodName + "_"
						+ formater.format(calender.getTime()) + ".png");
				FileUtils.copyFile(sourceFile, desFile);
				Reporter.log("<a href='" + desFile.getAbsolutePath()
						+ "'><img src='" + desFile.getAbsolutePath()
						+ "' height='100' width='100'/></a>");

			} catch (Exception e) {
				System.out.println("Exception is throwing during screen shot"
						+ e.getMessage());
			}
		}
	}

}

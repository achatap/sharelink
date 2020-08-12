package test;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CapturingScreenshot
{

	static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public static void startTest()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\link\\ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
		System.setProperty("webdriver.chrome.driver","C:\\Webdrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	public void extentReportsDemo() throws IOException
	{


		driver.get("https://programesecure.com");
		if(driver.getTitle().equals("Google"))
		{
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "Test Failed");
			test.log(LogStatus.PASS, "Navigated to the specified URL");
		}
		else
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
			test.log(LogStatus.FAIL, "Test Failed : For https://youtube.com");
			Assert.assertTrue(false);
		}
	}
	@Test
	public void second() throws IOException
	{

		driver.get("https://facebook.com");
		if(driver.getTitle().equals("Google"))
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
			test.log(LogStatus.FAIL, "Navigated to the specified URL");
		}
		else
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
			test.log(LogStatus.FAIL, "Test Failed : For https://facebook.com");
			Assert.assertTrue(false);
		}
	}

	@Test
	public void third() throws IOException
	{


		driver.get("https://google.com");
		if(driver.getTitle().equals("Google"))
		{
			test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "Test Failed");
			test.log(LogStatus.PASS, "Navigated to the specified URL: https://google.com");
		}
		else
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
			test.log(LogStatus.FAIL, "Test Failed");
			
		}
	}

	@AfterClass
	public static void endTest()
	{
		report.endTest(test);
		report.flush();
		driver.close();
	}


	public static String capture(WebDriver driver) throws IOException {

		String scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		return "data:image/jpg;base64, " + scrFile ;


	}


}
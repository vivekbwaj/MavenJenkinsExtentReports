package com.learnautomation.selenium;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Demo1 
{
    ExtentReports report;
    ExtentTest logger;
    WebDriver driver;
    
	@BeforeTest
	public void setup()
	{
		report=new ExtentReports("H:\\JavaSelenium_Advanced\\mavenJenkinsExtent.html");
		System.setProperty("webdriver.chrome.driver", "H:\\Accessibility_Automation\\driverDependencies\\chromedriver.exe");
	    driver=new ChromeDriver();		
	}

    
    
	@Test
	public void myTest1()
	{
        logger=report.startTest("myTest1");
		driver.get("https://www.gmail.com");
		System.out.println("loaded page 1..."+driver.getTitle());
		logger.log(LogStatus.INFO, "title of the opened page is: "+driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Gmail"));
		logger.log(LogStatus.PASS, "correct page opened");
	}
	
	@Test
	public void myTest2()
	{
		
        logger=report.startTest("myTest2");
		driver.get("https://jenkins.io/");
		System.out.println("loaded page 2..."+driver.getTitle());
		logger.log(LogStatus.INFO, "title of the opened page is: "+driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Jenkinsdsgf"));
		logger.log(LogStatus.PASS, "correct page opened");
		
	}
	
	@AfterMethod
	public void teardown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			System.out.println("shut now");
			String image=logger.addScreenCapture("H:\\JavaSelenium_Advanced\\testImage.jpeg");
			logger.log(LogStatus.FAIL, "Incorrect page loaded",image);
		}
		
	}
	@AfterTest
	public void endExecution()
	{
		report.endTest(logger);
		report.flush();
		driver.get("H:\\JavaSelenium_Advanced\\mavenJenkinsExtent.html");
	}
}

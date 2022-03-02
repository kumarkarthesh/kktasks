package com.flipkart.tasks.util;



import static com.flipkart.tasks.util.ConfigFileReader.getData;

import java.io.File;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest  {
	public  WebDriver driver;
	public ConfigFileReader config = new ConfigFileReader() ;
	
	public static WebDriverWait wait;
	public  WebDriverHelp util;
	protected static ExtentReports report;
	protected static ExtentSparkReporter reports;
	protected static ExtentTest test;
	static {
	    File log4j2File = new File(getData().getProperty("LogFilePath"));
	    System.setProperty("log4j2.configurationFile", log4j2File.toURI().toString());
	}
 
	
	public static final Logger log= LogManager.getLogger(BaseTest.class);
	
	@BeforeClass
	public void createDriver() {
		  
		
		System.setProperty(getData().getProperty("DriverName"), getData().getProperty("DriverPath"));
		 driver= new ChromeDriver();
		util = new WebDriverHelp();
		 driver.get(getData().getProperty("URL"));
		// driver.manage().window().maximize();
		// WebDriverHelp.implicitWait();
		 wait = new WebDriverWait(driver, Duration.ofSeconds(30000));
	}
	
	
	
	@AfterSuite
	public void closeDriver() throws Exception {
		log.info("Quiting Driver.");
		Thread.sleep(50000);
		driver.quit();
	}

		
	
}

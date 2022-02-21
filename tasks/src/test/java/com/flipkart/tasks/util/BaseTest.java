package com.flipkart.tasks.util;



import static com.flipkart.tasks.util.ConfigFileReader.getData;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest  {
	public static WebDriverHelp util;
	public static WebDriver driver;
	
	public ConfigFileReader config = new ConfigFileReader() ;
	public ExtentReports eReports;
	public ExtentTest test;
	public static WebDriverWait wait;
	static {
	    File log4j2File = new File(getData().getProperty("LogFilePath"));
	    System.setProperty("log4j2.configurationFile", log4j2File.toURI().toString());
	}
 
	public static final Logger log= LogManager.getLogger(BaseTest.class);
	
	public static WebDriver getDriver() {  
		return driver;
	}



	@SuppressWarnings("deprecation")
	@BeforeClass
	public void createDriver() {
		  
		
		System.setProperty(getData().getProperty("DriverName"), getData().getProperty("DriverPath"));
		 driver= new ChromeDriver();
		
		 driver.get(getData().getProperty("URL"));
		// driver.manage().window().maximize();
		// WebDriverHelp.implicitWait();
		 wait = new WebDriverWait(driver, 30);
	}
	
	
	
	@AfterSuite
	public void closeDriver() throws Exception {
		log.info("Quiting Driver.");
		Thread.sleep(50000);
		driver.quit();
	}

		
	
}

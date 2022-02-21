package com.flipkart.tasks.test;

import static com.flipkart.tasks.util.ConfigFileReader.getData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.tasks.pages.HomePage2;
import com.flipkart.tasks.util.BaseTest;


public class Test2 {
	public WebDriver driver;
	HomePage2 homePage2;

	@BeforeClass
	public void setup() {
		 System.setProperty(getData().getProperty("DriverName"), getData().getProperty("DriverPath"));
		 driver= new ChromeDriver();
		 driver.get(getData().getProperty("URL"));
		 homePage2= new HomePage2(driver);
	}
	
	@Test 
	public void linkValidation()  {
		
		BaseTest.log.info("Link Validation Method ");
		homePage2.clickLoginclose();
		homePage2.validateLinks();
			
	}
	
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
	
	
}

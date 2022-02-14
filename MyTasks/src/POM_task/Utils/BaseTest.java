package POM_task.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
	
	public static WebDriver getDriver() {  
		return driver;
	}


	@BeforeClass
	public void createDriver() {
		 
		
		System.setProperty(config.prop.getProperty("DriverName"), config.prop.getProperty("DriverPath"));
		 driver = new ChromeDriver();
		 driver.get(config.prop.getProperty("URL"));
		// driver.manage().window().maximize();
		// WebDriverHelp.implicitWait();
		 eReports= new ExtentReports();
		 test = eReports.createTest("hg");
		 
	}
	
	
	
	@AfterSuite
	public void closeDriver() throws Exception {
		//driver.close();
		System.out.println("Quiting driver");
		Thread.sleep(60000);
		driver.quit();
	}

		
	
}

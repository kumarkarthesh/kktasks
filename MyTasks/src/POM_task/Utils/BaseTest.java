package POM_task.Utils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class BaseTest  {
	public static WebDriverHelp util;
	static WebDriver driver;
	ConfigFileReader config= new ConfigFileReader();
	
	public static WebDriver getDriver() {  
		return driver;
	}


	@BeforeClass
	public void createDriver() {
		 
		System.setProperty(config.prop.getProperty("DriverName"), config.prop.getProperty("DriverPath"));
		 driver = new ChromeDriver();
		 driver.get(config.prop.getProperty("URL"));
		 driver.manage().window().maximize();
		// WebDriverHelp.implicitWait();
		 util = new WebDriverHelp();
		
	}
	
	@AfterSuite
	public void closeDriver() throws Exception {
		//driver.close();
		System.out.println("Quiting driver");
		Thread.sleep(30000);
		driver.quit();
	}

}

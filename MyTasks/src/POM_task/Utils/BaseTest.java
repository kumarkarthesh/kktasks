package POM_task.Utils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest  {
	static WebDriver driver;
	public ConfigFileReader config= new ConfigFileReader();
	
	public static WebDriver getDriver() {
		return driver;
	}


	@BeforeClass
	public void createDriver() {
		 
		System.setProperty(config.prop.getProperty("DriverName"), config.prop.getProperty("DriverPath"));
		 driver = new ChromeDriver();
		 driver.get(config.prop.getProperty("URL"));
		 driver.manage().window().maximize();
		 WebDriverHelp.implicitWait();
		
	}
	
	
	public void closeDriver() throws Exception {
		//driver.close();
		driver.quit();
	}

}

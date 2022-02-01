package POM_task.Utils;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest extends ConfigFileReader {
	static WebDriver driver;
	
	
	public static WebDriver getDriver() {
		return driver;
	}


	@BeforeClass
	public void createDriver() {
		 
		System.setProperty(prop.getProperty("DriverName"), prop.getProperty("DriverPath"));
		 driver = new ChromeDriver();
		 driver.get(prop.getProperty("URL"));
		 WebDriverHelp.implicitWait();
		
	}
	
	
	public void closeDriver() {
		//driver.close();
		driver.quit();
	}

}

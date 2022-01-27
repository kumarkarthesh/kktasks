package task1_3;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class DriverSetup extends ConfigFileReader  {
	 ChromeDriver driver ;
	 WebDriverWait wait ; 
	 SoftAssert sa;
	 Actions actions;
	 JavascriptExecutor js;
  
  @BeforeSuite
  public void DriverInitiate() {
	  
				
	  			System.setProperty( prop.getProperty("DriverName") , prop.getProperty("DriverPath") );
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				 wait = new WebDriverWait(driver, 40);
				// actionClick = new Actions(driver);
				 sa = new SoftAssert();
				  js = (JavascriptExecutor) driver;
				//driver.manage().window().maximize();
				driver.get(prop.getProperty("URL"));
				driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
				
  }

}

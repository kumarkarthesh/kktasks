import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;


public class InitializeDriver {
	
	
	 ChromeDriver driver ;
	 WebDriverWait wait ; 
	 @BeforeSuite
		public void navigate(){
				// TODO Auto-generated method stub
				System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\elcot\\eclipse-workspace\\firstday\\src\\resources\\chromedriver_97.0.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				 wait = new WebDriverWait(driver, 5000);
				driver.manage().window().maximize();
				driver.get("https://www.flipkart.com");
  				driver.manage().window().maximize();
				//driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
				
	
}
}
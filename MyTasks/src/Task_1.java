import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task_1 {

	ChromeDriver driver;

	@BeforeTest
	public void navigate() {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\elcot\\eclipse-workspace\\firstday\\src\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com");

	}

	@Test(dataProvider = "login_credentials", priority = 1)
	public void login(String username, String passw) {

		driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passw);
		driver.findElement(By.xpath("//button[@type='submit']//span[text()='Login']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement we = driver.findElement(By.xpath("//div[text()='My Account']"));
		String check = we.getText();
		
		Assert.assertEquals(check, "My Account");
		
	}

	@DataProvider(name = "login_credentials")
	public Object[][] loginData() {
		Object[][] data = { { "9843078389", "flipkart" } };
		return data;
	}

	 @Test(priority=2)
	public void selectProduct() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("realme 8");
		driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();

		driver.findElement(By.xpath("//div[text()='realme 8 (Cyber Black, 128 GB)'][1]")).click();
		driver.findElement(By.xpath("//div[text()='realme 8 (Cyber Silver, 128 GB)'][1]")).click();
		driver.findElement(By.xpath("//div[text()='realme 8 5G (Supersonic Black, 128 GB)'][1]")).click();
		driver.findElement(By.xpath("//div[text()='realme 8i (Space Black, 64 GB)'][1]")).click();

		System.out.println("4 products clicked");
	}

	 @Test(priority=3)
	public void addingToCart() {
		ArrayList<String> newTabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(newTabs.get(1));
		System.out.println(driver.getTitle() + "Added to Cart");
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();

		driver.switchTo().window(newTabs.get(2));
		System.out.println(driver.getTitle() + "Added to Cart");
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();

		driver.switchTo().window(newTabs.get(3));
		System.out.println(driver.getTitle() + "Added to Cart");
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();

		driver.switchTo().window(newTabs.get(4));
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
		System.out.println(driver.getTitle() + "Added to Cart");

		
	}
	

	@Test(priority = 4)
	public void cart() {
		ArrayList<String> newTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTabs.get(0));
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='zab8Yh _10k93p']//span[@class='_2-ut7f _1WpvJ7']"));

		  List<Integer> priceAll = new ArrayList<Integer>();
		  for (int i=0; i<products.size(); i++) { 
			  if(Integer.parseInt(products.get(i).getText().replaceAll("[\\u20B9,]",""))!=0) {
			  priceAll.add(Integer.parseInt(products.get(i).getText().replaceAll("[\\u20B9,]","")));
			  }
		  		}
		  	Collections.sort(priceAll);
		  	System.out.println("Lowest value in cart is "+ priceAll.get(0));
		  	
}

}

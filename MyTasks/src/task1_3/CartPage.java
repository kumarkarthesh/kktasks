package task1_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartPage extends DriverSetup {
	
	@Test(dataProvider = "login_credentials",priority=0)
	public void login(String username, String passw) {
		
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passw);
		driver.findElement(By.xpath("//button[@type='submit']//span[text()='Login']")).click();
		WebElement we = driver.findElement(By.xpath("//div[text()='My Account']"));
		String check = we.getText();
		Assert.assertEquals(check, "My Account");
	}

	@DataProvider(name = "login_credentials")
	public Object[][] loginData(){
		Object input[][]=new Object[1][2];
		input[0][0] = "9843078389";
		input[0][1] = "flipkart";
		return input;
	}
	
	@Test(priority=1)
	public void Cart() throws InterruptedException {
		
		ArrayList<String> newTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTabs.get(0));
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='zab8Yh _10k93p']//span[@class='_2-ut7f _1WpvJ7']"));

		  List<Integer> priceAll = new ArrayList<Integer>();
		  List<WebElement> removeItem = driver.findElements(By.xpath("//div[text()='Remove']"));
		  for (int i=0; i<products.size(); i++) { 
			  if(Integer.parseInt(products.get(i).getText().replaceAll("[\\u20B9,]",""))!=0) {
			  priceAll.add(Integer.parseInt(products.get(i).getText().replaceAll("[\\u20B9,]","")));
			  }
		  	}
		  
		  for (int i=0; i<priceAll.size(); i++)
			  for (int j=i+1 ; j<priceAll.size() ; j++) {
				  if(priceAll.get(i)>priceAll.get(j)) {
					  removeItem.get(i).click();
					  Thread.sleep(5000);
					  driver.findElement(By.xpath("//div[@class='_3dsJAO _24d-qY FhkMJZ']")).click();
					  Thread.sleep(5000);
				  }
				  
			  }
		  Collections.sort(priceAll);
		  System.out.println("Smallest : "+ priceAll.get(0));
		  
		/*  
		  HashMap<Integer, Integer> hm = new HashMap <Integer, Integer>();
		  for (int i=0; i<products.size(); i++) {
			  	hm.put(i, priceAll.get(i));
		  }
		  
		  
		  Iterator itr = hm.entrySet().iterator();
		  
		  int i=0;
		  while (itr.hasNext()) {
			  Map.Entry me = (Map.Entry) itr.next();
			  
			  		  }
			  
		  
		  for (int i=0; i<products.size(); i++) {
			  priceAll.get(i)
		  }
		  
		  	*/
		  	System.out.println("Lowest value in cart is "+ priceAll.get(0));
	}
}

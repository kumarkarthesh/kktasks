package task1_3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Tesp;

public class CartPage extends DriverSetup {
	
	
	
	@Test(priority=2)
	public void Cart() {
		
		ArrayList<String> newTabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTabs.get(0));
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		
		List<WebElement> products_Title = driver.findElements(By.xpath("//div[@class='zab8Yh _10k93p']/div/div//a"));
		List<WebElement> productsRate_non_Int = driver.findElements(By.xpath("//div[@class='zab8Yh _10k93p']//span[@class='_2-ut7f _1WpvJ7']"));

		List<Integer> priceAll = new ArrayList<Integer>();
		List<WebElement> removeItem = driver.findElements(By.xpath("//div[text()='Remove']"));
		  
		for (int i=0; i<productsRate_non_Int.size(); i++) { 
			  if(Integer.parseInt(productsRate_non_Int.get(i).getText().replaceAll("[\\u20B9,]",""))!=0) {
			  priceAll.add(Integer.parseInt(productsRate_non_Int.get(i).getText().replaceAll("[\\u20B9,]","")));
			  }
		  	}
		
		System.out.println("Price of all : " +priceAll);
		System.out.println("Total Remove Items buttons : " + removeItem.size());
		System.out.println("Total Products : " + products_Title.size());
		Assert.assertEquals(removeItem.size(), products_Title.size());
		
		for (int i=0; i<removeItem.size(); i++) { 
			  if ( i != priceAll.indexOf(Collections.min(priceAll))) { //If not the minimum value
				  try {
						 js.executeScript("arguments[0].click()", removeItem.get(i));
						 WebElement removeButton = driver.findElement(By.xpath("//div[@class='row _1lPa3S']/div/div[text()='Remove']"));
						 js.executeScript("arguments[0].click()",removeButton);
						 String message = driver.findElement(By.xpath("//div[@class='_2sKwjB']")).getText();
						 System.out.println(message);
				  	} 
				  
				  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }  
		  }
	
	}

}





/*
* @Test(dataProvider = "login_credentials",priority=0)
public void login(String username, String passw) {
	
	driver.findElement(By.xpath("//a[text()='Login']")).click();
	driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passw);
	driver.findElement(By.xpath("//button[@type='submit']//span[text()='Login']")).click();
	WebElement we = driver.findElement(By.xpath("//div[text()='My Account']"));
	String check = we.getText();
	//Assert.assertEquals(check, "My Account");
}

@DataProvider(name = "login_credentials")
public Object[][] loginData(){
	Object input[][]=new Object[1][2];
	input[0][0] = "9843078389";
	input[0][1] = "flipkart";
	return input;
}

*/




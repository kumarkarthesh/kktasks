 

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Tests extends InitializeDriver{
	 
	
	@Test(priority=0)
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
	public Object[][] loginData() throws EncryptedDocumentException, IOException {
			FileInputStream inp = new FileInputStream("C:\\Users\\elcot\\eclipse-workspace\\Project Data\\login_details.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheet("Data2");
			int RowCount = sheet.getLastRowNum();
			int ColumnCount = sheet.getRow(0).getLastCellNum();
			System.out.println("Row Count  :"+RowCount+"  Column Count : "+ColumnCount);
			Object input[][]=new Object[RowCount][ColumnCount];
			
	    
			DataFormatter formatter = new DataFormatter();
			
			/*
			 * input[0][0]= formatter.formatCellValue(sheet.getRow(1).getCell(0));
			 * input[0][1] = formatter.formatCellValue(sheet.getRow(1).getCell(1));
			 * System.out.println(input[0][0]); System.out.println(input[0][1]);
			 */
			for(int i= 1; i<=RowCount; i++){ 
					  for(int j= 0;j<ColumnCount;j++){ 
						  Row row =sheet.getRow(i);
						  Cell cell= row.getCell(j);
						  input[i-1][j] = formatter.formatCellValue(cell) ; 
						  System.out.print(input[i-1][j]+ "  ");
					  }
				  System.out.println("");
		  }
		  
		 return input;
	    
	}
	
	
	
	
	@Test(priority=3, enabled =true)
	public void selectProduct1() {
		//ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		
	//Erasing Previous searches
		Actions action= new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
		
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("mobiles");
		driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();
		
		//Search results
		List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
		List<WebElement> productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		String product1= "" ;
		System.out.println("Total elements : "+searchResults.size());
		
		for(int i=0 ; i<searchResults.size(); i++) {
			//Re initiating 
			 searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
			 productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
				
			 String s= searchResults.get(i).getText();
				  if ( s.contains("SAMSUNG") && s.contains("4 GB RAM")	 && s.contains("64 GB ROM")&& s.contains("Black")) {
						  searchResults.get(i).click();
						  product1 =  productTitle.get(i).getText();
						  System.out.println("Product name : "+ product1);
						  break;
				  }
				  else if ( s.contains("realme") && s.contains("4 GB RAM")	 && s.contains("64 GB ROM")&& s.contains("Black")) {
					  searchResults.get(i).click();
					  product1 =  productTitle.get(i).getText();
					  System.out.println("Product name : "+ product1);
					  break;
			  }
				  
				  // Moving to Next Page
				  try {		
				  if (i == (searchResults.size()-1)) {
					  driver.findElement(By.xpath("//span[text()='Next']")).click();
					  i= -1;
				  }}
				  catch ( Exception e) {
					  assertFalse(false, e.toString());
					  break;
				  }
				  
		}
		
		
		Set<String> windows= driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		String parent= itr.next();
		String secondwind = itr.next();
		driver.switchTo().window(secondwind);
		String actualProduct = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
		assertTrue(actualProduct.contains(product1));
		
		try { 		//Adding to Cart
		
			driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
			Thread.sleep(5000);
			
		} 
		catch (Exception e) {
				assertFalse(false, e.toString());
		}
		finally {
			driver.close();
			driver.switchTo().window(parent);
		}
		
	}                
	
	@Test(priority=4, enabled = true)
	public void selectProduct2() {
		
		//Erasing Previous searches
			Actions action= new Actions(driver);
			action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
			
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys("TV");
			driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();
			
			//Search results
			List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
			List<WebElement> productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
			String product2= "" ;
			System.out.println("Total elements : "+searchResults.size());
			
			for(int i=0 ; i<searchResults.size(); i++) {
				//Re initiating 
				 searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
				 productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
					
				 String s= searchResults.get(i).getText();
					  if ( s.contains("Vu") && s.contains("55 inch")&& s.contains("Android")) {
							  searchResults.get(i).click();
							  product2 =  productTitle.get(i).getText();
							  System.out.println("Product name : "+ product2);
							  break;
					  }
					  
					  // Moving to Next Page
					  try {		
					  if (i == (searchResults.size()-1)) {
						  driver.findElement(By.xpath("//span[text()='Next']")).click();
						  i= -1;
					  }}
					  catch ( Exception e) {
						  assertFalse(false, e.toString());
						  break;
					  }
					  
			}
			
			Set<String> windows= driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			String parent= itr.next();
			String secondwind = itr.next();
			driver.switchTo().window(secondwind);
			
			String actualProduct = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
			assertTrue(actualProduct.contains(product2));
			
			try { 		//Adding to Cart
			
				driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
				Thread.sleep(5000);
				
			} 
			catch (Exception e) {
					assertFalse(false, e.toString());
			}
			finally {
				driver.close();
				driver.switchTo().window(parent);
			}
			
		}             
	@Test(priority=5, enabled=true)
	public void selectProduct3() {
		
		
		//Erasing Previous searches
			Actions action= new Actions(driver);
			action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
			
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys("fridge");
			driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();
			
			//Search results
			List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
			List<WebElement> productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
			String product3= "" ;
			System.out.println("Total elements : "+searchResults.size());
			
			for(int i=0 ; i<searchResults.size(); i++) {
				//Re initiating 
				 searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
				 productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
					
				 String s= searchResults.get(i).getText();
					  if ( s.contains("SAMSUNG") && s.contains("192 L")	 && s.contains("3 Star")) {
							  searchResults.get(i).click();
							  product3 =  productTitle.get(i).getText();
							  System.out.println("Product name : "+ product3);
							  break;
					  }
					  
					  // Moving to Next Page
					  try {		
					  if (i == (searchResults.size()-1)) {
						  driver.findElement(By.xpath("//span[text()='Next']")).click();
						  i= -1;
					  }}
					  catch ( Exception e) {
						  assertFalse(false, e.toString());
						  break;
					  }
					  
			}
			
			Set<String> windows= driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			String parent= itr.next();
			String secondwind = itr.next();
			driver.switchTo().window(secondwind);
			
			String actualProduct = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
			assertTrue(actualProduct.contains(product3));
			
			try { 		//Adding to Cart
			
				driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
				Thread.sleep(5000);
				
			} 
			catch (Exception e) {
					assertFalse(false, e.toString());
			}
			finally {
				driver.close();
				driver.switchTo().window(parent);
			}
			
		}          
	
	
	@Test(priority=6, enabled= true)
	public void selectProduct4() {
		driver.navigate().refresh();
		
		//Erasing Previous searches
			Actions action= new Actions(driver);
			action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
			
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys("washing machine");
			driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();
			
			//Search results
			List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
			List<WebElement> productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
			String product4= "" ;
			System.out.println("Total elements : "+searchResults.size());
			
			for(int i=0 ; i<searchResults.size(); i++) {
				//Re initiating 
				 searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
				 productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
					
				 String s= searchResults.get(i).getText();
					  if ( s.contains("IFB") && s.contains("8 kg")	 || s.contains("5 Star")) {
							  searchResults.get(i).click();
							  product4 =  productTitle.get(i).getText();
							  System.out.println("Product name : "+ product4);
							  break;
					  }
					  
					  // Moving to Next Page
					  try {		
					  if (i == (searchResults.size()-1)) {
						  driver.findElement(By.xpath("//span[text()='Next']")).click();
						  i= -1;
					  }}
					  catch ( Exception e) {
						  assertFalse(false, e.toString());
						  break;
					  }
					  
			}
			
			Set<String> windows= driver.getWindowHandles();
			Iterator<String> itr = windows.iterator();
			String parent= itr.next();
			String secondwind = itr.next();
			driver.switchTo().window(secondwind);
			
			String actualProduct = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
			assertTrue(actualProduct.contains(product4));
			
			try { 		//Adding to Cart
			
				driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
				Thread.sleep(5000);
				                                                                                                               
			} 
			catch (Exception e) {
					assertFalse(false, e.toString());
			}
			finally {
				driver.close();
				driver.switchTo().window(parent);
			}
			
		}  
	
	@AfterClass(enabled=true )
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

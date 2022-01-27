package task1_3;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllTests extends DriverSetup {
	
	//Login
	@Test(dataProvider = "login_credentials", priority=0, alwaysRun=true)
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
	public Object[][] loginData() throws EncryptedDocumentException, IOException {
			FileInputStream inp = new FileInputStream("C:\\Users\\elcot\\eclipse-workspace\\Project Data\\login_details.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheet("Data2");
			int RowCount = sheet.getLastRowNum();
			int ColumnCount = sheet.getRow(0).getLastCellNum();
			Object input[][]=new Object[RowCount][ColumnCount];
			
			DataFormatter formatter = new DataFormatter();
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
	
	//Selecting Product and adding to Cart
	@Test (dataProvider = "searchData", priority=1)
	public void selectProduct(String searchQuery, String Brand, String criteria1, String criteria2) throws InterruptedException {
		System.out.println("====================================================================");
		System.out.println("Entering : "+ "  " + searchQuery+" " + criteria1);
	
		// action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
		
		try {
			
		 new Actions(driver).doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
			
			driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchQuery);
			driver.findElement(By.xpath("//button[@class='L0Z3Pu']")).click();

		} 
		 catch (Exception e1) {
			 System.out.println("Exception clicking submit button");
			 e1.printStackTrace();
			 throw new SkipException("");
		}
		 
		 
		
		 List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
		 String selectedProduct= "" ;
		 System.out.println("serachresults added : ");
		 Boolean productClicked=false;
		 
				for(int i=0 ; i<searchResults.size(); i++) {
			
						searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
						List<WebElement> productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
						String product = searchResults.get(i).getText();
							if ( product.contains(Brand)&& product.contains(criteria1)) {
									try {
										searchResults.get(i).click();
										productClicked = true;
										selectedProduct =  productTitle.get(i).getText();
										System.out.println("Product name : "+ selectedProduct );
										break;
										}
									catch(StaleElementReferenceException e) {
										System.out.println("Stale element exception while cllcking the product");
									}	
								}
								
							if (i == (searchResults.size()-1)) {
									try {
									  driver.findElement(By.xpath("//span[text()='Next']")).click();
									  i= -1;
								  }
							catch ( Exception e) {
									  System.out.println("NEXT page : Eception in clicking next page");; 
									  break;
								 }	
							} 
						}
				
				
			if (productClicked==true) {	
				//Getting Window Handle
				Set<String> windows= driver.getWindowHandles();
				Iterator<String> itr = windows.iterator();
				String parent= itr.next();
				String secondwind = itr.next();
				driver.switchTo().window(secondwind);
				String actualProduct = driver.findElement(By.xpath("//span[@class='B_NuCI']")).getText();
				assertTrue(actualProduct.contains(selectedProduct));
				//driver.switchTo().def
				
				
			//Adding to Cart
				try { 		 			
					driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
					Boolean failedToAddinCart = driver.findElement(By.xpath("//div[text()='Unable to add to Cart. Please try in a few minutes.']")).isDisplayed();
					if (failedToAddinCart ) {
						sa.assertFalse(!failedToAddinCart);
						throw new SkipException("Unable to Add in Cart");
					}
					else
						wait.until(ExpectedConditions.urlToBe("https://www.flipkart.com/viewcart?otracker=PP_GoToCart"))	;
				} 
				catch (SkipException e) {
						System.out.println("Failed Adding to Cart  ");
				}
				catch (StaleElementReferenceException  e) {
					System.out.println("adding in cart :  StaleElementReferenceException  ");
				}
				finally {
					driver.close();
					driver.switchTo().window(parent);
				}
				
			}
			System.out.println("End of product");
			System.out.println("====================================================================");
			sa.assertAll();
	}

	
	
	
	@DataProvider(name = "searchData")
	public Object[][] searchData() {
		
		Workbook wb = null;
		
		try
		{
			wb = WorkbookFactory.create(new FileInputStream(prop.getProperty("SearchDataPath")));
		} 
		catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		
		int RowCount =  wb.getSheet("searchData").getLastRowNum();
		int ColumnCount =  wb.getSheet("searchData").getRow(0).getLastCellNum();
		System.out.println("Row Count  :"+RowCount+"  Column Count : "+ColumnCount);
		Object input[][]=new Object[RowCount][ColumnCount];
		
		for(int i= 1; i<=RowCount; i++)
			  for(int j= 0;j<ColumnCount;j++) 
				  input[i-1][j] = wb.getSheet("searchData").getRow(i).getCell(j).getStringCellValue() ; 
			  
		return input;
	}
	
	
	// Removing large value items in Cart
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

	
	/*
	public WebElement searchTab() {
		System.out.println("Entered search method");
		WebElement we = driver.findElement(By.xpath("//input[@name='q']"));
		Actions action = new Actions(driver); 
		action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
		return we;
		
	}
	*/
	
}

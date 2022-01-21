package task1_3;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestClass extends DriverSetup {
	
	@Test(dataProvider = "login_credentials")
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
	
	@Test (dataProvider = "searchData")
	public void selectProduct(String searchQuery, String Brand, String criteria1, String criteria2) throws InterruptedException {
		System.out.println("====================================================================");
		System.out.println("Entering : "+ "  " + searchQuery+" " + criteria1);
	
		// action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
		
		
		 
		 try {
			  searchTab().sendKeys(searchQuery);
			  driver.findElement(By.xpath("//button[@type='submit']")).click();
			  System.out.println("Searched  : "+ "  " );
		} 
		 catch (Exception e1) {
			  System.out.println("Exception clicking submit button");
		}
		 
		 
		
		 List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
		 String selectedProduct= "" ;
		 System.out.println("serachresults added : ");
		 Boolean productClicked=false;
		 
				for(int i=0 ; i<searchResults.size(); i++) {
			
						searchResults = driver.findElements(By.xpath("//div[@class='col col-7-12']"));
						List<WebElement> productTitle = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
						System.out.println("producttile listed inside for");
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
				
				
				
			//Adding to Cart
				try { 		 			
					driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
					Boolean failedToAddinCart = driver.findElement(By.xpath("//div[text()='Unable to add to Cart. Please try in a few minutes.']")).isDisplayed();
					if (failedToAddinCart ) {
						sa.assertFalse(failedToAddinCart);
						throw new TimeoutException("Unable to Add in Cart");
					}
					else
						wait.until(ExpectedConditions.urlToBe("https://www.flipkart.com/viewcart?otracker=PP_GoToCart"))	;
				} 
				catch (TimeoutException e) {
						System.out.println("Failed Adding to Cart : Time out in adding to cart ");
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
	
	
		
	
	
	public WebElement searchTab() {
		System.out.println("Entered search method");
		WebElement we = driver.findElement(By.xpath("//input[@name='q']"));
		Actions action = new Actions(driver); 
		action.doubleClick(driver.findElement(By.xpath("//input[@name='q']"))).perform();
		return we;
		
	}
	
	
}

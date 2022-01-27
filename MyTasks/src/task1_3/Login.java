package task1_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login extends DriverSetup {
	

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
	@Test(dataProvider = "login_credentials", priority=0, alwaysRun=true)
	public void login(String username, String passw) throws IOException {
		
		driver.findElement(By.xpath("//a[text()='Login']")).click();
		driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passw);
		driver.findElement(By.xpath("//button[@type='submit']//span[text()='Login']")).click();
		driver.navigate().refresh();
		
		
		String actualName;
		String expectedName;
		try {
			actualName = driver.findElement(By.xpath("//div[@class='_1cmsER']/following-sibling::div[1]//div[@class='exehdJ']")).getText();
			expectedName = "Karthesh";
			Assert.assertEquals(actualName, expectedName);
			
		} catch (Exception e) {
			takeSnap(driver, "D:\\Kk\\Selenium screenshot\\Login_screenshot.png");
			Assert.assertTrue(false, "Login failed");
		}	
		
	}
	
	
	
	
	
	public  void takeSnap(WebDriver wdrive, String filePath) throws IOException {
		
			TakesScreenshot scrnsct = ((TakesScreenshot)wdrive);
			File srcFile = scrnsct.getScreenshotAs(OutputType.FILE);
			File dest = new File(filePath);
			FileUtils.copyFile(srcFile, dest);
		
		
	}

	

	public void links() throws Exception {
			actions =  new Actions(driver);
		  ArrayList<WebElement> all_Links = (ArrayList<WebElement>) driver.findElements(By.tagName("a")); 
		  System.out.println("Total links : "+ all_Links.size());
		  int i= 0;
		  System.out.println("Name : "+ all_Links.get(i).getText());
		  System.out.println("Href         -->    : "+ all_Links.get(i).getAttribute("href"));
		  String ctrlClk = Keys.chord( Keys.CONTROL,Keys.ENTER);
		  actions.keyUp(Keys.LEFT_CONTROL) 
	       .click(all_Links.get(i)) 
	       .keyDown(Keys.LEFT_CONTROL) 
	       .build() 
	       .perform(); 
		 // actionClick.key
		 
		 //actionClick.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).perform();
		 // actionClick.sendKeys(all_Links.get(i), Keys.CONTROL,Keys.ENTER).click().perform();
		 // all_Links.get(i).click();
		  System.out.println("After click   -->   : "+driver.getCurrentUrl());
		 // driver.navigate().back();
		 /*
		  try {
			System.out.println("Total links : "+ all_Links.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Caught Exception ");
		}
		  i= 16;
		  System.out.println("Name : "+ all_Links.get(i).getText());
		  System.out.println("Href -->    : "+ all_Links.get(i).getAttribute("href"));
		  all_Links.get(i).click();
		  System.out.println("After c;lick  -->  "+driver.getCurrentUrl());
		  
		  
		  */
		  
		  
		  
	}
}
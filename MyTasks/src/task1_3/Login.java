package task1_3;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login extends DriverSetup {
	
	
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
	public Object[][] loginData() throws EncryptedDocumentException, IOException {
			FileInputStream inp = new FileInputStream("C:\\Users\\elcot\\eclipse-workspace\\Project Data\\login_details.xlsx");
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheet("Data2");
			int RowCount = sheet.getLastRowNum();
			int ColumnCount = sheet.getRow(0).getLastCellNum();
			System.out.println("Row Count  :"+RowCount+"  Column Count : "+ColumnCount);
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

}

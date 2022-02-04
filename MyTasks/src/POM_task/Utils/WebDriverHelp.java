package POM_task.Utils;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WebDriverHelp extends BaseTest {
	
	
	public static void implicitWait() {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void explicitWait_Clickable(WebElement element) {
		new WebDriverWait(getDriver() ,30000)
					.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void explicitWait_visible(WebElement element) {
		new WebDriverWait(getDriver() ,10000)
					.until(ExpectedConditions.visibilityOf(element));
	}
	
	@Test
	public  void takeSnap() throws IOException {
		
		try {
			TakesScreenshot scrnsct = ((TakesScreenshot)getDriver());
			File srcFile = scrnsct.getScreenshotAs(OutputType.FILE);
			System.out.println(config.prop.getProperty("URL"));
			File dest = new File(config.prop.getProperty("SnapshotPath"));
			FileUtils.copyFile(srcFile, dest);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

	public static void explicitWait_invisible(WebElement element) {
		// TODO Auto-generated method stub
		new WebDriverWait(getDriver(), 10000).until(ExpectedConditions.visibilityOf(element));
	
	
	}
	
	public static void explicitWait_stale(WebElement element) {
		new WebDriverWait(getDriver(), 10000).until(ExpectedConditions.stalenessOf(element));
		
	}
	
	
}

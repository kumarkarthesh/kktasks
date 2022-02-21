package com.flipkart.tasks.util;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import static com.flipkart.tasks.util.ConfigFileReader.getData;

public class WebDriverHelp extends BaseTest {
	
	
	@SuppressWarnings("deprecation")
	public static void implicitWait() {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@SuppressWarnings("deprecation")
	public static void explicitWait_Clickable(WebElement element) {
		new WebDriverWait(getDriver() ,30000)
					.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	@SuppressWarnings("deprecation")
	public static void explicitWait_visible(WebElement element) {
		new WebDriverWait(getDriver() ,10000)
					.until(ExpectedConditions.visibilityOf(element));
	}
	
	@Test
	public  void takeSnap() throws IOException {
		
		try {
			TakesScreenshot scrnsct = ((TakesScreenshot)getDriver());
			File srcFile = scrnsct.getScreenshotAs(OutputType.FILE);
			
			File dest = new File(getData().getProperty("SnapshotPath"));
			FileUtils.copyFile(srcFile, dest);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

	@SuppressWarnings("deprecation")
	public static void explicitWait_invisible(WebElement element) {
		// TODO Auto-generated method stub
		new WebDriverWait(getDriver(), 10000).until(ExpectedConditions.visibilityOf(element));
	
	
	}
	
	@SuppressWarnings("deprecation")
	public static void explicitWait_stale(WebElement element) {
		new WebDriverWait(getDriver(), 10000).until(ExpectedConditions.stalenessOf(element));
		
	}

	@SuppressWarnings("deprecation")
	public static void explicitWait_visible(List<WebElement> element) {
		// TODO Auto-generated method stub
		new WebDriverWait(getDriver() ,10000)
		.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	
}

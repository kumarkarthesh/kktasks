package com.flipkart.tasks.util;


import static com.flipkart.tasks.util.ConfigFileReader.getData;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelp extends BaseTest {
	
	
	public  void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
	}
	
	
	public  void explicitWait_Clickable(WebElement element, WebDriver driver) {
		new WebDriverWait(driver ,Duration.ofSeconds(10000))
					.until(ExpectedConditions.elementToBeClickable(element));
	}
	

	public  void explicitWait_visible(WebElement element, WebDriver driver) {
		new WebDriverWait(driver ,Duration.ofSeconds(10000))
					.until(ExpectedConditions.visibilityOf(element));
	}

	public  void takeSnap() throws IOException {
		
		try {
			TakesScreenshot scrnsct = ((TakesScreenshot)driver);
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

	
	public  void explicitWait_invisible(WebElement element) {
		// TODO Auto-generated method stub
		new WebDriverWait(driver, Duration.ofSeconds(10000)).until(ExpectedConditions.visibilityOf(element));
	
	
	}
	
	
	public  void explicitWait_stale(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(10000)).until(ExpectedConditions.stalenessOf(element));
		
	}

	
	public  void explicitWait_visible(List<WebElement> element, WebDriver driver) {
		// TODO Auto-generated method stub
		new WebDriverWait(driver ,Duration.ofSeconds(10000))
		.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public  List<Integer> priceToInt(List<WebElement> get_allPrices, WebDriver driver) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public boolean isElementDisplayed(WebElement element) {
		boolean flag = false;
		try {
			
			flag = element.isDisplayed();
			
		} catch (NoSuchElementException e) {
			flag = false;
		}
		return flag;
	}
	



	
	
}

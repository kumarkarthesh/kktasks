package POM_task.Utils;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelp extends BaseTest {
	
	public static void implicitWait() {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void explicitWait(WebElement element) {
		new WebDriverWait(getDriver() ,10000)
					.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public void takeSnap() throws IOException {
		
		TakesScreenshot scrnsct = ((TakesScreenshot)getDriver());
		File srcFile = scrnsct.getScreenshotAs(OutputType.FILE);
		File dest = new File(config.prop.getProperty("SnapshotPath"));
		FileUtils.copyFile(srcFile, dest);
	
	
	}
	
	
}

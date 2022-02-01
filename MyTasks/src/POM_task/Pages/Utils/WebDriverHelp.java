package POM_task.Pages.Utils;


import java.util.concurrent.TimeUnit;

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
}

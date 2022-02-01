package POM_task.Pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage {

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	
	@FindBy(xpath="/html/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]")
	private WebElement _item;
	
	public ItemdetailsPage itemSelect() {
		_item.click();
		
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		
		while ( itr.hasNext()) {
			String childWind= itr.next();
			if ( ! parent.equals(childWind))
				driver.switchTo().window(childWind);
			}
		
		return new ItemdetailsPage(driver);
		
	}
	
}
                                                                                        
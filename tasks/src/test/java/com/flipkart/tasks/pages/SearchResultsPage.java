package com.flipkart.tasks.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flipkart.tasks.util.WebDriverHelp;

public class SearchResultsPage extends BasePage {
	public String parent;
	WebDriver driver;
	public SearchResultsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	
	@FindBy(xpath="//div[@class='col col-7-12']")
	private List<WebElement> _items;
	
	public List<WebElement> get_items() {
		util.explicitWait_visible(_items,driver);
		return _items;
	}

	public ItemDetailsPage itemSelect() {
		
		
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		
		while ( itr.hasNext()) {
			String childWind= itr.next();
			if ( ! parent.equals(childWind))
				driver.switchTo().window(childWind);
			}
		
		return new ItemDetailsPage(driver);
		
	}

	public void selectItem(int i) {
		util.waitForSearchResults(driver);
		System.out.println("Selected item is : " + _items.get(i).getText());
		System.out.println("First line is : "+ _items.get(i).getText().split("\n",2).toString());
		_items.get(i).click();
		
	}

	
	
}
                                                                                        
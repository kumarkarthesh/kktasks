package com.flipkart.tasks.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
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
	
	String markedHeart_className = "_2hVSre _3nq8ih _21GqcY";
	
	@FindBy(xpath="//div[@class='col col-7-12']")
	private List<WebElement> _items;

	@FindBy(xpath="//div[@class='col col-7-12']//div[@class='_4rR01T']")
	private List<WebElement> _itemsTitle;
	
	@FindBy(xpath = "//div[@class='_36FSn5']")
	private List<WebElement> _heartButtons;
	
	@FindBy(xpath = "//div[@class='MIXNux']")
	private List<WebElement> _productTabs;
	
	@FindBy(xpath = "//div[@class='_2hVSre _3nq8ih _21GqcY']")
	private List<WebElement> _markedHeartButtons;
	
	@FindBy(xpath = "//div[@class='_4rR01T']")
	private List<WebElement> _productNameList;
	
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

	public String selectItem(int i) {
		//util.waitForSearchResults(driver);
		util.explicitWait_visible(_items, driver);
		String productName =  _itemsTitle.get(i).getText();
		System.out.println("Selected item is : " + productName);
		_items.get(i).click();
		return productName;
		
	}

	public boolean isProductAlreadyInWishList(int i) {
		util.explicitWait_visible(_heartButtons, driver);
		Boolean check = _heartButtons.get(i).findElement(By.xpath("parent::div")).getAttribute("class").equals(markedHeart_className);
		return check;
	}

	public void addToWishList(int i) {
		util.explicitWait_visible(_heartButtons, driver);
		_heartButtons.get(i).click();
	}

	

	public String getProductName(int i) {
		util.explicitWait_visible(_productNameList, driver);
		String name =_productNameList.get(i).getText();
		return name;
	}

	
	
}
                                                                                        
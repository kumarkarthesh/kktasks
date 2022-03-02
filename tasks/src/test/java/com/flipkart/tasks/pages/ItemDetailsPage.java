package com.flipkart.tasks.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flipkart.tasks.util.WebDriverHelp;

public class ItemDetailsPage extends BasePage {
	WebDriver driver;
	String parent="";
	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span")
	private WebElement _itemText;
	
	@FindBy(xpath="//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	private WebElement _addToCartButton;
	
	public String itemText() {

		return _itemText.getText();
		
	}
	
	@FindBy(xpath= "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
	private WebElement _addToCartBtn;

	public WebElement addToCartBtn() {
		util.explicitWait_visible(_addToCartBtn,driver);
		return _addToCartBtn;
	}

	public boolean isPageOpened() {
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Windows opened : "+ windows.size());
		Boolean isPageOpened = (windows.size()==2) ? true : false;
		System.out.println("Page opened : " +isPageOpened);
		return isPageOpened;
	}

	public boolean isProductAvailable() {
		util.explicitWait_visible(_addToCartButton, driver);
		return _addToCartButton.getText().equalsIgnoreCase("add to cart");
	}
	
	public void gotoItemPageWindow() {
		parent= parent + driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		Iterator<String> I1= s.iterator();
		
		while(I1.hasNext()){
			String child_window = I1.next();
			if(!parent.equals(child_window))
				driver.switchTo().window(child_window);
			}
	}

	public void clickAddToCart() {
		// TODO Auto-generated method stub
		_addToCartButton.click();
		util.waitForPageLoad(driver);
		driver.close();
		driver.switchTo().window(parent);
		
	}

	

	
	
	
	
}

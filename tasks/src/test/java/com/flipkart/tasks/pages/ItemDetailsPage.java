package com.flipkart.tasks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flipkart.tasks.util.WebDriverHelp;

public class ItemDetailsPage extends BasePage {
	WebDriver driver;
	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span")
	private WebElement _itemText;
	
	public String itemText() {

		return _itemText.getText();
		
	}
	
	@FindBy(xpath= "//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
	private WebElement _addToCartBtn;

	public WebElement addToCartBtn() {
		util.explicitWait_visible(_addToCartBtn);
		return _addToCartBtn;
	}
	
	
	
}

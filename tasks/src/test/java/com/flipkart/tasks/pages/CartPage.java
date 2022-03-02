package com.flipkart.tasks.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(xpath="//span[@class='_2-ut7f _1WpvJ7']")
	private List<WebElement> _allPrices;

	public List<Integer> g_allPrices() {
		util.explicitWait_visible(_allPrices, driver);
		List<Integer> priceInInt = new ArrayList<Integer>();
		System.out.println(_allPrices.size());
		for (int i = 0; i < _allPrices.size(); i++) {
			 System.out.println(_allPrices.get(i).getText().replaceAll("[^0-9]",""));
			 if((Integer.parseInt(_allPrices.get(i).getText().replaceAll("[\\u20B9,]","")))	!= 0	) {
				  priceInInt.add(Integer.parseInt(_allPrices.get(i).getText().replaceAll("[\\u20B9,]","")));
			 	
			 }
			 
			}
		return priceInInt;
	}
	
	@FindBy(xpath="//div[@class='_2-uG6-']/a")
	private List<WebElement> _allProducts;
	
	public List<WebElement>  get_allProducts() {
		util.explicitWait_visible(_allProducts, driver);
		return _allProducts;
	}

	public List<WebElement> get_allPrices() {
		util.explicitWait_visible(_allPrices, driver);
		return _allPrices;
	}
	
	
	@FindBy(xpath="//*[contains(text(),'empty')]")
	private WebElement _cartEmpty;
	
	public Boolean isCartEmpty() {
		// TODO Auto-generated method stub
		Boolean displayed = util.isElementDisplayed(_cartEmpty);
		return displayed;
		
	}
	@FindBy(xpath = "//div[contains(text(),'Total Amount')]/parent::div/following-sibling::span//span")
	private WebElement _cartValue;

	public WebElement getCartValue() {
		// TODO Auto-generated method stub
		return _cartValue;
	}
	
	@FindBy(xpath = "//div[@class='_2nQDXZ']//span[@class='_2-ut7f _1WpvJ7']")
	private List<WebElement> _prices;

	
	public List<Integer> getPrices() {
		util.explicitWait_visible(_prices, driver);
		List<Integer> pricesInInteger= new ArrayList<Integer>();
		for (int i = 0; i < _prices.size(); i++) {
			pricesInInteger.add(Integer.parseInt(_prices.get(i).getText().replaceAll("[^0-9]", "")));
		}
		  return pricesInInteger;
	}
	
	@FindBy(xpath = "//div[@class='zab8Yh _10k93p']//input")
	private List<WebElement> _quantity;
	
	public List<Integer> getQuantity() {
		util.explicitWait_visible(_quantity, driver);
		List<Integer> quantity = new ArrayList<Integer>();
		  for (int i = 0; i < _quantity.size(); i++) {
			quantity.add(Integer.parseInt(_quantity.get(i).getAttribute("value")));
		}
		  return quantity;
	}
	
}

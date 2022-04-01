package com.flipkart.tasks.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemDetailsPage extends BasePage {
	WebDriver driver;
	//String parent="";
	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	String markedHeart_className = "_2hVSre _3nq8ih _21GqcY";
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span")
	private WebElement _itemText;
	
	@FindBy(xpath="//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")
	private WebElement _addToCartButton;
	
	@FindBy(className="_36FSn5")
	private WebElement _heartIcon;
	
	@FindBy(xpath = "//div[@class='_2hVSre _25_uYi _21GqcY']")
	private WebElement _markedHeartIcon;
	
	@FindBy(xpath = "//div[text()='Similar Products']/parent::div")
	private WebElement _similarproductSection;
	
	@FindBy(xpath="//div[text()='Similar Products']/parent::div/div[2]//div[@class='_36FSn5']")
	private List<WebElement> _similarProductsHeartIcon; 
	
	@FindBy(xpath = "//div[text()='Similar Products']/parent::div/div[2]//*[@class='s1Q9rs']")
	private List<WebElement> _similarProductNames;
	
	
	@FindBy(xpath = "//div[text()='You might be interested in']")
	private WebElement _youMightInterestedSection;
	
	
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
		//System.out.println("Windows opened : "+ windows.size());
		Boolean isPageOpened = (windows.size()==2) ? true : false;
		//System.out.println("Page opened : " +isPageOpened);
		return isPageOpened;
	}

	public boolean isProductAvailable() {
		util.explicitWait_visible(_addToCartButton, driver);
		return _addToCartButton.getText().equalsIgnoreCase("add to cart");
	}
	
	public void gotoItemPageWindow() {
		
		String parent= driver.getWindowHandle();
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

	public void addToWishlist() {
		
		util.explicitWait_visible(_heartIcon, driver);
		_heartIcon.click();
	}

	public boolean isProductAlreadyAddedToWishList() {
		// TODO Auto-generated method stub
		Boolean check = util.isElementDisplayed(_markedHeartIcon);
		return check;
	}

	public void goToSimilarProductSection() {
		/*
		 * util.explicitWait_visible(_similarproductSection, driver); actions = new
		 * Actions(driver); actions.moveToElement(_similarproductSection).perform();
		 */
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", _youMightInterestedSection);
	
	}

	public boolean isProductAlreadyInWishList(int i) {
		util.explicitWait_visible(_similarproductSection, driver);
		Boolean check = _similarProductsHeartIcon.get(i).findElement(By.xpath("parent::div")).getAttribute("class").equals(markedHeart_className);
		return check;
		
	}

	public void addToWishList(int i) {
		// TODO Auto-generated method stub
		//util.explicitWait_visible(_similarProductsHeartIcon, driver);
		_similarProductsHeartIcon.get(i).click();
	}

	public String getProductName(int i) {
		//util.explicitWait_visible(_similarProductNames, driver);
		String name = _similarProductNames.get(i).getText();
		return name;
	}

	

	
	
	
	
}

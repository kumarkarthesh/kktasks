package com.flipkart.tasks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.flipkart.tasks.util.WebDriverHelp;



public class HomePage extends BasePage {
	
	WebDriver driver;
	public Actions actions;
	//Constructor, Elements & Actions
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	} 
	
	//Elements
	
	@FindBy(xpath="/html/body/div[2]/div/div/button")
	private WebElement _loginClose;

	//Action
	public void clickLoginclose() {
		util.explicitWait_Clickable(_loginClose, driver);
		_loginClose.click();
	}
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")
	private WebElement _searchBox;
	
	public void inputSearchtext(String s) {
		util.explicitWait_Clickable(_searchBox, driver);
		_searchBox.click();
		_searchBox.sendKeys(s);
	}
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement _buttonSearch;
	
	public void clickSearchButton() {
		 util.explicitWait_Clickable(_buttonSearch, driver);
		_buttonSearch.click();
	}

	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div/div/a")
		private WebElement _loginButton;
	
	public LoginPage clickLoginButton() {
		util.explicitWait_Clickable(_buttonSearch, driver);
		_loginButton.click();
		return new LoginPage(driver);
		
	}
	
	//@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div/div/div")
	
	public void moveOverMenu() {
		util.explicitWait_visible(get_profileMenu(), driver);
		actions = new  Actions(driver);
		actions.moveToElement(get_profileMenu()).perform();
		}
	
	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement _myProfile;
	

	public ProfilePage clickMyProfile() {
		util.explicitWait_Clickable(_myProfile, driver);
		_myProfile.click();
		return new ProfilePage(driver);
	}

	
	@FindBy(xpath = "//div[text()='Logout']")
	private WebElement _logout;
	
	public WebElement clickLogout() {
		return _logout;
	}
	
	@FindBy(xpath="//div[@class='_1psGvi _3BvnxG']/div/a")
	private WebElement _loginElement;
	
	public WebElement getLoginElement() {
		util.explicitWait_visible(_loginElement, driver);
		return  _loginElement;
	}
	
	@FindBy(xpath="//*[@id='container']/div/div[1]/div[1]/div[2]/div[3]/div/div//div[@class='exehdJ']")
	private WebElement _profileName;
	
	@FindBy(xpath = "//div[text()='Wishlist']")
	private WebElement _wishList;
	
	
	
	
	public WebElement getProfileName() {
		util.explicitWait_visible(_profileName, driver);
		return _profileName;
	}

	public void clickWishList() {
		// TODO Auto-generated method stub
		util.explicitWait_visible(_wishList, driver);
		_wishList.click();
	}

	
	

		/*
		 * for (WebElement webElement : links) {
		 * WebDriverHelp.explicitWait_visible(webElement);
		 * System.out.println(webElement.getText()); }
		 */
	
	
	
	/*
	 * @FindBy(tagName="a") private List<WebElement> _allLinks;
	 * 
	 * 
	 * public void getLinks() { // TODO Auto-generated method stub`
	 * 
	 * System.out.println("All Links ..."); for (WebElement webElement : _allLinks)
	 * { WebDriverHelp.explicitWait_visible(webElement);
	 * System.out.println(webElement); System.out.println(webElement.getText()); }
	 * 
	 * }
	 * 
	 */
	
	
}



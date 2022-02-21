package com.flipkart.tasks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.flipkart.tasks.util.WebDriverHelp;



public class HomePage extends BasePage {
	
	
	Actions actions;
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
		WebDriverHelp.explicitWait_Clickable(_loginClose);
		_loginClose.click();
	}
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")
	private WebElement _searchBox;
	
	public void inputSearchtext(String s) {
		WebDriverHelp.explicitWait_Clickable(_searchBox);
		_searchBox.click();
		_searchBox.sendKeys(s);
	}
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/button")
	private WebElement _buttonSearch;
	
	public SearchResultsPage clickSearchButton() {
		 WebDriverHelp.explicitWait_Clickable(_buttonSearch);
		_buttonSearch.click();
		return new SearchResultsPage(driver); 
	}

	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div/div/a")
		private WebElement _loginButton;
	
	public LoginPage clickLoginButton() {
		WebDriverHelp.explicitWait_Clickable(_buttonSearch);
		_loginButton.click();
		return new LoginPage(driver);
		
	}
	
	//@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div/div/div")
	
	public void moveOverMenu() {
		WebDriverHelp.explicitWait_visible(get_profileMenu());
		actions = new  Actions(driver);
		actions.moveToElement(get_profileMenu()).perform();
		}
	
	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement _myProfile;
	

	public ProfilePage clickMyProfile() {
		WebDriverHelp.explicitWait_Clickable(_myProfile);
		_myProfile.click();
		return new ProfilePage(driver);
	}

	
	@FindBy(xpath = "//div[text()='Logout']")
	private WebElement _logout;
	
	public WebElement clickLogout() {
		return _logout;
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



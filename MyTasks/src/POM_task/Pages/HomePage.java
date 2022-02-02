package POM_task.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import POM_task.Utils.WebDriverHelp;

public class HomePage extends BasePage {
	
	WebDriver driver;
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
		WebDriverHelp.explicitWait(_loginClose);
		_loginClose.click();
	}
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")
	private WebElement _searchBox;
	
	public void inputSearchtext(String s) {
		WebDriverHelp.explicitWait(_searchBox);
		_searchBox.click();
		_searchBox.sendKeys(s);
	}
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/button")
	private WebElement _buttonSearch;
	
	public SearchResultsPage clickSearchButton() {
		WebDriverHelp.explicitWait(_buttonSearch);
		_buttonSearch.click();
		return new SearchResultsPage(driver); 
	}

	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div/div/a")
		private WebElement _loginButton;
	
	public LoginPage clickLoginButton() {
		WebDriverHelp.explicitWait(_buttonSearch);
		_loginButton.click();
		return new LoginPage(driver);
		
	}
	
	@FindBy(xpath="//div[@class='_1cmsER']/following-sibling::div[1]//div[@class='exehdJ']")
	private WebElement _profileMenu;
	
	
	public void moveOverMenu() {
	
		actions = new  Actions(driver);
		actions.moveToElement(_profileMenu).perform();
		}
	
	@FindBy(xpath="//div[text()='My Profile']")
	private WebElement _myProfile;
	

	public ProfilePage clickMyProfile() {
		WebDriverHelp.explicitWait(_myProfile);
		_myProfile.click();
		return new ProfilePage(driver);
	}
	
	

}



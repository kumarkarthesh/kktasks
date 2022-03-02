package com.flipkart.tasks.pages;

import static com.flipkart.tasks.util.ConfigFileReader.getData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flipkart.tasks.util.WebDriverHelp;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	
				   

	@FindBy(xpath="//div/label/preceding-sibling::input[@type='text']")
		private WebElement _userName;
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")
	private WebElement _password;

	@FindBy(xpath="//button/span[text()='Login']")
	private WebElement _loginButton;
	
	@FindBy(xpath = "//div[@class='_2QfC02']")
	private WebElement _loginFrame;

	@FindBy(xpath="//div/label/preceding-sibling::input[@type='text']")
	private WebElement _username;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement _passwrd;
	
	
	
	
	
	public WebElement click_Username() {
		util.explicitWait_visible(_username, driver);
		return _username;
	}
	

	public WebElement click_Password() {
		util.explicitWait_visible(_passwrd, driver);
		return _passwrd;
	}

	
	public void clickLoginButton() {
		_loginButton.click();
	}
	
	
	
	public HomePage loginButtonClick() {
		util.explicitWait_Clickable(_loginButton,driver);
		_loginButton.click();
		util.explicitWait_invisible(_loginButton);
		//util.explicitWait_stale(get_profileMenu());
		return new HomePage(driver);
	}
	
	
	public boolean isLoginPageDisplayed() {
		util.explicitWait_visible(_loginFrame, driver);
		return true;
	}
	
	
	
	
	
	
	
	public void username() {
		System.out.println(util.toString());
		util.explicitWait_Clickable(_userName, driver);
		System.out.println("UserName : "+getData().getProperty("Username"));
		_userName.click();
		_userName.sendKeys(getData().getProperty("Username"));
	}
	

	
	public void password() {
		util.explicitWait_Clickable(_password, driver);
		_password.click();
		System.out.println("Password : "+getData().getProperty("Password"));
		_password.sendKeys(getData().getProperty("Password"));
	}
	
	public void loginButtonClic(int i) {
		//WebDriverHelp.explicitWait_Clickable(_loginButton);
		_loginButton.click();
		//util.explicitWait_invisible(_loginButton);
		//util.explicitWait_stale(get_profileMenu());
	}
	
	
}

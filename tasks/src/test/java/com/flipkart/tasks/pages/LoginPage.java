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
	
	public void username() {
		System.out.println(util.toString());
		util.explicitWait_Clickable(_userName, driver);
		System.out.println("UserName : "+getData().getProperty("Username"));
		_userName.click();
		_userName.sendKeys(getData().getProperty("Username"));
	}
	

	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")
		private WebElement _password;
	
	public void password() {
		util.explicitWait_Clickable(_password, driver);
		_password.click();
		System.out.println("Password : "+getData().getProperty("Password"));
		_password.sendKeys(getData().getProperty("Password"));
	}
	
	@FindBy(xpath="//button/span[text()='Login']")
		private WebElement _loginButton;
	
	public void loginButtonClic(int i) {
		//WebDriverHelp.explicitWait_Clickable(_loginButton);
		_loginButton.click();
		//util.explicitWait_invisible(_loginButton);
		//util.explicitWait_stale(get_profileMenu());
		
	}
	public HomePage loginButtonClick() {
		//WebDriverHelp.explicitWait_Clickable(_loginButton);
		_loginButton.click();
		util.explicitWait_invisible(_loginButton);
		//util.explicitWait_stale(get_profileMenu());
		return new HomePage(driver);
	}
	
	

	
	
	
}

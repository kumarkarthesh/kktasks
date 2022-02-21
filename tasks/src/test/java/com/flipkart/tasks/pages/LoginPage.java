package com.flipkart.tasks.pages;

import static com.flipkart.tasks.util.ConfigFileReader.getData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.flipkart.tasks.util.WebDriverHelp;
import com.flipkart.tasks.util.ConfigFileReader;

public class LoginPage extends BasePage {
	
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
				   
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")
		private WebElement _userName;
	
	public void username() {
		WebDriverHelp.explicitWait_Clickable(_userName);
		_userName.click();
		System.out.println("UserName : "+getData().getProperty("Username"));
		_userName.sendKeys(getData().getProperty("Username"));
	}
	

	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")
		private WebElement _password;
	
	public void password() {
		//WebDriverHelp.explicitWait_Clickable(_password);
		_password.click();
		System.out.println("Password : "+getData().getProperty("Password"));
		_password.sendKeys(getData().getProperty("Password"));
	}
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[4]/button/span")
		private WebElement _loginButton;
	
	public HomePage loginButtonClick() {
		//WebDriverHelp.explicitWait_Clickable(_loginButton);
		_loginButton.click();
		WebDriverHelp.explicitWait_invisible(_loginButton);
		//WebDriverHelp.explicitWait_stale(get_profileMenu());
		return new HomePage(driver);
	}
	
	

	
	
	
}

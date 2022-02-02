package POM_task.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import POM_task.Utils.WebDriverHelp;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
				   
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")
		private WebElement _userName;
	
	public void username() {
		WebDriverHelp.explicitWait(_userName);
		_userName.click();
		System.out.println("UserName : "+ config.prop.getProperty("Username"));
		_userName.sendKeys(config.prop.getProperty("Username"));
	}
	

	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")
		private WebElement _password;
	
	public void password() {
		WebDriverHelp.explicitWait(_password);
		_password.click();
		System.out.println("Password : "+config.prop.getProperty("Password"));
		_password.sendKeys(config.prop.getProperty("Password"));
	}
	
	@FindBy(xpath="/html/body/div[2]/div/div/div/div/div[2]/div/form/div[4]/button/span")
		private WebElement _loginButton;
	
	public HomePage loginButtonClick() {
		WebDriverHelp.explicitWait(_loginButton);
		_loginButton.click();
		return new HomePage(driver);
	}
	
	

	
	
	
}

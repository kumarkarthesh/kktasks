package POM_task.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import POM_task.Utils.WebDriverHelp;

public class ProfilePage extends BasePage {

	public ProfilePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/div/div[2]")
	private WebElement _profileNmae;
	
	public String profileName() {
		WebDriverHelp.explicitWait(_profileNmae);
		String s = _profileNmae.getText();
		return s;
	}
	
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[1]/div/a[1]/img")
	private WebElement _homeButton;
	
	public HomePage clickHome() {
		_homeButton.click();
		return new HomePage(driver);
		
	}
	
	
}

package POM_task.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemDetailsPage extends BasePage {

	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath="//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span")
	private WebElement _itemText;
	
	public String itemText() {

		return _itemText.getText();
		
	}
}

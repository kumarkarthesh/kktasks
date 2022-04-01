package com.flipkart.tasks.pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends BasePage {
	WebDriver driver;
	public WishListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
	}
	
	
	
	
	
	@FindBy(xpath = "//div[@class='_3hscEA']")
	private List<WebElement> _productNameList;
	
	@FindBy(xpath = "//div[@class='Bv11UC _2GI1Le Bh0FCW']")
	private List<WebElement> _deleteButton;
	
	@FindBy (xpath = "//div/button[@class='_2KpZ6l _3S58wp']")
	private WebElement _deleteConfirmation;
	
	@FindBy(xpath = "//div[@class='_2sKwjB']")
	private WebElement _alertMessage;
	
	
	public List<WebElement> getName(){
		util.explicitWait_visible(_productNameList, driver);
		return _productNameList;
	}

	public int getLength() {
		// TODO Auto-generated method stub
		util.explicitWait_visible(_productNameList, driver);
		
		return _productNameList.size();
	}

	public List<WebElement> getList() {
		util.explicitWait_visible(_productNameList, driver);
		return _productNameList;
	}

	public String getAllNames() {
		// TODO Auto-generated method stub
		util.explicitWait_visible(_productNameList, driver);
		String allNamesInList ="";
		for (WebElement webElement : _productNameList) {
			allNamesInList = allNamesInList + webElement.getText();
			//System.out.println(webElement.getText());
		}
		System.out.println("Length : "+allNamesInList.length());
		return allNamesInList;
	}

	public void clickDeleteButton() {
		util.explicitWait_visible(_deleteButton, driver);
		_deleteButton.get(0).click();
	}

	public void clickConfirmButton() {
		util.explicitWait_visible(_deleteConfirmation,driver);
		_deleteConfirmation.click();
		
	}

	public boolean isMessageDisplayed() {
		util.explicitWait_visible(_alertMessage, driver);
		Boolean check = util.isElementDisplayed(_alertMessage);
		return check;
	}
	
}

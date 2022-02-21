package com.flipkart.tasks.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.flipkart.tasks.util.BaseTest;
import com.flipkart.tasks.util.WebDriverHelp;



public class HomePage2  {

	Actions actions;
	public WebDriver driver;
	SoftAssert sa = new SoftAssert();
	
	
	public HomePage2(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	

	@FindBy(xpath="/html/body/div[2]/div/div/button")
	private WebElement _loginXClose;
	public void clickLoginclose() {
		_loginXClose.click();
	}
	
	@FindBy(tagName="a")
	private List<WebElement> links;
	
	public void validateLinks() {
		
		actions= new Actions(driver);
		System.out.println("Link toatl : "+ links.size());
		
		System.out.println("=====\nlinks click");
		
		for (int i = 20; i < 25; i++) {
				System.out.println("=======\n" +i+"\n"+ links.get(i).getText());
				String expectedURL = links.get(i).getAttribute("href") ;
				System.out.println("href= --> "+ expectedURL );
				
				if(!(links.get(i).getAttribute("href")==null)) {
					try {				//Opening in new Tab using CTRL + Click
						actions.keyDown(Keys.LEFT_CONTROL) 
						   .click(links.get(i)) 
						   		.keyUp(Keys.LEFT_CONTROL) 
						   				.build().perform();
							} 
						catch (Exception e) {
							
								BaseTest.log.info(e.toString());
								}
					getWindowControl(i,expectedURL);
				}
				
			
		} 
		sa.assertAll();
	}

	private void getWindowControl(int i, String expectedURL) {
		
		String parent=driver.getWindowHandle();
		//String expectedURL = url;
		Set<String>s=driver.getWindowHandles();
		Iterator<String> I1= s.iterator();
		
		
		while(I1.hasNext()){
		
			String child_window = I1.next();
			if(!parent.equals(child_window)){
				driver.switchTo().window(child_window);
				String actualURL = driver.getCurrentUrl();
				
				sa.assertEquals(expectedURL , actualURL);
				System.out.println("Child ---> " + actualURL );
				driver.close();
				}
				 
			}
		driver.switchTo().window(parent);
		
	}
	
}



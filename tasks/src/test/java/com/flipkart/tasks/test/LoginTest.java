package com.flipkart.tasks.test;

import static com.flipkart.tasks.util.ConfigFileReader.getData;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.tasks.pages.HomePage;
import com.flipkart.tasks.pages.LoginPage;
import com.flipkart.tasks.util.BaseTest;

public class LoginTest extends BaseTest {
	 LoginPage lp;
	 HomePage hp;
	 Actions actions;
	 
	 @BeforeClass
	 public void setup() {
		 lp = new LoginPage(driver);
		 
	 }
	 
	@Test
	public void loginTest() {
			
		lp.username();
		
		lp.password();
		lp.loginButtonClic(0);
		log.info("Login button clicked");
		hp= new HomePage(driver);
		hp.pageRefresh();
		String actualName = hp.get_profileMenu().getText();
		if (!	(actualName.equals(  getData().getProperty("ProfileName")))	) {
			log.warn("UserName does not matches with data");
			assertTrue(false);	
		}
		
		actions = new Actions(driver);
		actions.moveToElement(hp.get_profileMenu()).perform();
		actions.moveToElement(hp.clickLogout()).click().perform();
		
		Assert.assertEquals(hp.getLoginElement().getText(), "Login");
		
	}
	
	
}

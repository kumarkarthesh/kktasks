package POM_task.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import POM_task.Pages.HomePage;
import POM_task.Pages.ItemdetailsPage;
import POM_task.Pages.LoginPage;
import POM_task.Pages.ProfilePage;
import POM_task.Pages.SearchResultsPage;
import POM_task.Utils.BaseTest;
import POM_task.Utils.WebDriverHelp;


public class SmokeTest extends BaseTest {
	
	
	LoginPage loginPage;
	HomePage homePage;
	ProfilePage profilePage;
	SearchResultsPage searchResultsPage;
	ItemdetailsPage itemDetailsPage;
	
	
	
	@BeforeClass
	public void setup() {
		loginPage = new LoginPage(getDriver());
		
	}
	
	
	@Test
	public void verifyHomepage() throws IOException  {
		 
		System.out.println("Test Started..... ");
		//Login
		loginPage.username();
		loginPage.password();
		homePage = loginPage.loginButtonClick();
		System.out.println("handed to hmompeage .");
		
		//Validating Login
		homePage.inputSearchtext("mobiles");
		homePage.moveOverMenu();
		profilePage = homePage.clickMyProfile();
		System.out.println("profile Page ");
		String actualName = profilePage.profileName();
		new WebDriverHelp().takeSnap();
		Assert.assertEquals(actualName, "Karthesh");
		
		
		homePage = profilePage.clickHome();
		
		
		
		
		//System.out.println(profilePage.profileName());
		//homePage.moveOverMenu();
		//homePage.clickMyProfile();
	
		//Assert.assertEquals(homePage.loginName(), "Karthesh");
		
		//homePage.inputSearchtext("mobiles");
		//getDriver().findElement(By.xpath("//div[@class='_1cmsER']/following-sibling::div[1]//div[@class='exehdJ']")).click();
		//System.out.println(	homePage.loginName());
		//searchResultsPage = homePage.clickSearchButton();
		//itemDetailsPage = searchResultsPage.itemSelect();
		//System.out.println(itemDetailsPage.itemText());
		
	}
	
	
}

package POM_task.Test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor; 
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import POM_task.Pages.HomePage;
import POM_task.Pages.ItemDetailsPage;
import POM_task.Pages.LoginPage;
import POM_task.Pages.ProfilePage;
import POM_task.Pages.SearchResultsPage;
import POM_task.Utils.BaseTest;
import POM_task.Utils.ConfigFileReader;
import POM_task.Utils.WebDriverHelp;

public class SmokeTest extends BaseTest {
	
	JavascriptExecutor js;
	LoginPage loginPage;
	HomePage homePage;
	ProfilePage profilePage;
	SearchResultsPage searchResultsPage;
	ItemDetailsPage itemDetailsPage;

	
	
	@BeforeClass
	public void setup() {
		
		loginPage = new LoginPage(getDriver());
		util = new WebDriverHelp();
		config = new ConfigFileReader();
	
	}
	
	@Test
	public void login() throws IOException, InterruptedException {
		
			
		//Login
		loginPage.username();
		loginPage.password();
		homePage = loginPage.loginButtonClick();
		 
		//Login validation
		getDriver().navigate().refresh();
		String actualName = homePage.get_profileMenu().getText();
		System.out.println(ConfigFileReader.prop.getProperty("ProfileName"));
		 
		if (!	(actualName.equals(ConfigFileReader.prop.getProperty("ProfileName")))	) {

			util.takeSnap();
			assertTrue(false);
			
		}
		
		
	}
	
	-
	@Test(dependsOnMethods= {"login"})
	
	public void linkValidation() throws IOException  {
		
		if(getDriver().getCurrentUrl()!="")
		System.out.println("========\n Link Validation Method ");
		
		homePage.validateLinks();
			
	}
	
	
	
	
}

package POM_task.Test;

import static POM_task.Utils.ConfigFileReader.prop;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

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
		
		
		System.out.println(prop.getProperty("ReportPath"));
		
		System.out.println("Login test Started..... ");
		
		//Login
		loginPage.username();
		loginPage.password();
		homePage = loginPage.loginButtonClick();
		 
		//Login validation
		getDriver().navigate().refresh();
		System.out.println("=========\nHanded to hmompeage .");
		System.out.println("Profile name : " + homePage.get_profileMenu().getText() + "\nName from config file : "+ prop.getProperty("ProfileName")) ;
		String actualName = homePage.get_profileMenu().getText();
		
		
		if (actualName.equals(ConfigFileReader.prop.getProperty("ProfileName"))) {
			test.log(Status.PASS, "Passed ttest nowe");
			//test.log(LogStatus.PASS, "Login Test Passed");
			assertTrue(true);
		}
		else {
			util.takeSnap();
			//test.fail(MediaEntityBuilder.createScreenCaptureFromPath);
			test.log(Status.FAIL, "login test failed");
			assertTrue(false);
	
		}
		eReports.flush();
		
		
		//homePage.moveOverMenu();
		//profilePage = homePage.clickMyProfile(); 
		
		//System.out.println("=========\n Profile Page ");
		//String actualName = profilePage.profileName();
		
		
		
		//System.out.println(we);
		//System.out.println("By javasript : " +we.getText());
		
		
		//homePage.moveOverMenu();
		/*
		 * profilePage = homePage.clickMyProfile(); System.out.println("profile Page ");
		 * String actualName = profilePage.profileName(); new
		 * WebDriverHelp().takeSnap(); Assert.assertEquals(actualName, "Karthesh");
		 * 
		 * homePage = profilePage.clickHome();
		 * 
		 * 
		 */
		//Thread.sleep(10000);
				//new WebDriverWait(getDriver(), 30).until(ExpectedConditions.invisibilityOf(getDriver().findElement(By.xpath("/html/body/div[2]/div/div/div"))));
				//WebDriverHelp.explicitWait_stale(getDriver().findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div")));
				//homePage = new HomePage(getDriver());
	
		//WebElement we = getDriver().findElement(By.xpath("//div[@class='_1cmsER']/following-sibling::div[1]//div[@class='exehdJ']"));
		//String name = "document.evaluate('//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div/div/div',document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;";
		//WebElement we = (WebElement) js.executeScript( "document.evaluate('//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div/div/div',document,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;");
	
		
	}
	
	
	@Test(dependsOnMethods= {"login"})
	//@Test
	public void linlValidation() throws IOException  {
			
		 
		System.out.println("========\n Link Validation Method ");

		homePage.validateLinks();
		
			
		
	}
	
	
	
}

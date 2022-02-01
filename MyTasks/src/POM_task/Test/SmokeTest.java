package POM_task.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import POM_task.Pages.HomePage;
import POM_task.Pages.ItemdetailsPage;
import POM_task.Pages.SearchResultsPage;
import POM_task.Utils.BaseTest;


public class SmokeTest extends BaseTest {
	
	HomePage homePage;
	SearchResultsPage searchResultsPage;
	ItemdetailsPage itemDetailsPage;
	
	@BeforeClass
	public void setup() {
		homePage = new HomePage(getDriver());
		
	}
	
	
	@Test
	public void verifyHomepage() throws InterruptedException {
		
		System.out.println("Test Started..... ");
		homePage.clickLoginclose();
		homePage.inputSearchtext("mobiles");
		searchResultsPage = homePage.clickSearchButton();
		itemDetailsPage = searchResultsPage.itemSelect();
		System.out.println(itemDetailsPage.itemText());
		Thread.sleep(10000);
		getDriver().quit();
		
		
	}
	
	
}

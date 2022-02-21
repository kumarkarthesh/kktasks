package com.flipkart.tasks.test;

import static com.flipkart.tasks.util.ConfigFileReader.getData;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.tasks.pages.HomePage;
import com.flipkart.tasks.pages.ItemDetailsPage;
import com.flipkart.tasks.pages.LoginPage;
import com.flipkart.tasks.pages.ProfilePage;
import com.flipkart.tasks.pages.SearchResultsPage;
import com.flipkart.tasks.util.BaseTest;
import com.flipkart.tasks.util.ExcelReader;
import com.flipkart.tasks.util.WebDriverHelp;

public class SmokeTest extends BaseTest {
	
	
	LoginPage loginPage;
	HomePage homePage;
	ProfilePage profilePage;
	SearchResultsPage searchResultsPage;
	ItemDetailsPage itemDetailsPage;

	
	
	@BeforeClass
	public void setup() {
	
		loginPage = new LoginPage(getDriver());
		homePage = new HomePage(getDriver());
		util = new WebDriverHelp();

	}

	
	@Test (priority=0)
	public void login() throws IOException, InterruptedException {
			
		//Login
		loginPage.username();
		loginPage.password();
		homePage = loginPage.loginButtonClick();
		log.info("Login button clicked");
		
		//Login validation
		homePage.pageRefresh();
		String actualName = homePage.get_profileMenu().getText();
		if (!	(actualName.equals(getData().getProperty("ProfileName")))	) {
			log.warn("UserName does not matches with data");
			util.takeSnap();
			assertTrue(false);
			
		}
		
		
	}
	
	
	
	@DataProvider(name = "searchData")
	public Object[][] searchData() {
		log.info("Reading excel ");
		return ExcelReader.excelData(getData().getProperty("SearchDataPath"));
	}
	
	
	@Test (dataProvider="searchData", dependsOnMethods= {"login"} )
	
	public void addToCart(String query, String Brand, String criteria1, String criteria2) throws InterruptedException {
		
		String parentWindow="";
		homePage.inputSearch(query);
		searchResultsPage = new SearchResultsPage(getDriver());
		List<WebElement> allItems =searchResultsPage.get_items();
		
		for (int i = 0; i < allItems.size(); i++) {
			String product = allItems.get(i).getText();
			//System.out.println(product);
			if (product.contains(Brand)	&& product.contains(criteria1)) {
				String productName = allItems.get(i).getText();
				allItems.get(i).click();
				log.info("Selected Product to add in Cart.");
				System.out.println("Product clicked :"+ productName);
				parentWindow = searchResultsPage.getWindowHandle();
				break;
			} 
			
		}
	
		itemDetailsPage = new ItemDetailsPage(getDriver());
		itemDetailsPage.addToCartBtn().click();
		log.info("Added to Cart");
		wait.until(ExpectedConditions.urlToBe("https://www.flipkart.com/viewcart?otracker=PP_GoToCart"));
		driver.close();
		getDriver().switchTo().window(parentWindow);	
		
	}
	
}

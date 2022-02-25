package com.flipkart.tasks.test;

import static com.flipkart.tasks.util.ConfigFileReader.getData;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.tasks.pages.CartPage;
import com.flipkart.tasks.pages.HomePage;
import com.flipkart.tasks.pages.ItemDetailsPage;
import com.flipkart.tasks.pages.LoginPage;
import com.flipkart.tasks.pages.ProfilePage;
import com.flipkart.tasks.pages.SearchResultsPage;
import com.flipkart.tasks.util.BaseTest;
import com.flipkart.tasks.util.ExcelReader;

public class SmokeTest extends BaseTest {
	
	
	LoginPage loginPage;
	HomePage homePage;
	ProfilePage profilePage;
	SearchResultsPage searchResultsPage;
	ItemDetailsPage itemDetailsPage;
	CartPage cartPage;
	
	
	
	@BeforeClass
	public void setup() {
	
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	

	}

	
	@Test (priority=0)
	public void login() throws IOException, InterruptedException {
	
		//Login
		
		loginPage.username();
		loginPage.password();
		loginPage.loginButtonClic(0);
		//homePage = loginPage.loginButtonClick();
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
	
	
	@Test (dataProvider="searchData", dependsOnMethods= {"login"}, enabled=false )
	
	public void addToCart(String query, String Brand, String criteria1, String criteria2) throws InterruptedException {
		
		String parentWindow="";
		homePage.inputSearch(query);
		searchResultsPage = new SearchResultsPage(driver);
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
	
		itemDetailsPage = new ItemDetailsPage(driver);
		itemDetailsPage.addToCartBtn().click();
		log.info("Added to Cart");
		wait.until(ExpectedConditions.urlToBe("https://www.flipkart.com/viewcart?otracker=PP_GoToCart"));
		driver.close();
		driver.switchTo().window(parentWindow);	
		
	}
	
	@Test (dependsOnMethods= {"login"}, enabled=false)
	public void linkValidation()  {
		
		BaseTest.log.info("Link Validation Method ");
		homePage.clickLoginclose();
		homePage.validateLinks();
			
	}
	
	
	@Test(dependsOnMethods= {"login"})
	public void cartTest() throws InterruptedException {
	
		
		if	(driver.getCurrentUrl() != getData().getProperty("CartURL")	)
			driver.get(getData().getProperty("CartURL"));
		
		cartPage = new CartPage(driver);
		
		//Checking for Empty Cart 
		Assert.assertFalse(cartPage.isCartEmpty(),"Cart is Empty");
		
		List<WebElement> productsInCart = cartPage.get_allProducts();
		Assert.assertFalse(productsInCart.isEmpty(), "Cart is Empty");
		
		//Getting all Prices
		List<Integer> prices = cartPage.g_allPrices();
		List<WebElement> pricesElement =  cartPage.get_allPrices();
		
		List<Integer> pricesInInteger = new ArrayList<Integer>();
		int cartValueSum=0;
		for (int i = 0; i < pricesElement.size(); i++) {
			pricesInInteger.add(Integer.parseInt(pricesElement.get(i).getText().replaceAll("[^0-9]", "")));
			cartValueSum  += pricesInInteger.get(i);
		}
		
		System.out.println("Prices from regex : "+ pricesInInteger);
		System.out.println("All prices items in cart : " +prices);
		
		//Checking Total Cart Value
		int actualCartValue = Integer.parseInt(cartPage.getCartValue().getText().replaceAll("[^0-9]", ""));
		System.out.println("Total Cart Value : "+ cartValueSum);
		System.out.println("Actual Cart value sum : "  +actualCartValue);
		Assert.assertEquals(actualCartValue, cartValueSum);
		
	}
	
	
}

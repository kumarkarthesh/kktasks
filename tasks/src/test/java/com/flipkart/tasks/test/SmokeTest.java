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

import com.flipkart.tasks.pages.BasePage;
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
	@Test
	public void login() throws IOException {
	
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
		loginPage.click_Username().sendKeys(getData().getProperty("Username"));
		loginPage.click_Password().sendKeys(getData().getProperty("Password"));
		//Assert.assertTrue(test.info("from assert not eqaul to null")==null);
		loginPage.clickLoginButton();
		
		//Login Validation
		String actualName = homePage.getProfileName().getText();
		System.out.println(actualName);
		if (!	(actualName.equals(getData().getProperty("ProfileName")))	) {
			log.warn("UserName does not matches with data");
			util.takeSnap("Login_Failed.png",driver);
			Assert.assertTrue(false);
		}
		log.info("Login passed");
		 
	}
	
	
	
	@DataProvider(name = "searchData")
	public Object[][] searchData() {
		log.info("Reading excel ");
		return ExcelReader.excelData(getData().getProperty("SearchDataPath"));
	}
	
	
	@Test (dataProvider="searchData", dependsOnMethods= {"login"}, enabled=true )
	public void addToCart(String query, String brand,String criteria1, String criteria2) throws InterruptedException {
		homePage.goToHomePage();
		//homePage.inputSearch(query);
		homePage.searchFor(query);
		searchResultsPage = new SearchResultsPage(driver);
		String productName = searchResultsPage.selectItem(2);
		itemDetailsPage = new ItemDetailsPage(driver);
		Assert.assertTrue(itemDetailsPage.isPageOpened());
		itemDetailsPage.gotoItemPageWindow();
		Assert.assertTrue(itemDetailsPage.isProductAvailable(),"Product Unavailable" );
		itemDetailsPage.clickAddToCart();
		
		
	}
	
	
	
	
	@Test (dependsOnMethods= {"login"}, enabled=false)
	public void linkValidation()  {
		
		BaseTest.log.info("Link Validation Method ");
		homePage.clickLoginclose();
		homePage.validateLinks();
			
	}
	
	
	@Test(enabled = true, dependsOnMethods= {"login"})
	public void cartTest() throws InterruptedException {
		
		
	
		
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
	
	@Test(enabled = true, dependsOnMethods= {"login"})
	public void cart() {
		driver.get(getData().getProperty("CartURL"));
		//checking Total cart Value
		cartPage = new CartPage(driver);
		List <Integer> prices = cartPage.getPrices();
		List <Integer> quantity = cartPage.getQuantity();
		System.out.println("prices ---> : " +prices + "\n quanity ---> : "+quantity);
		int totalPrice = 0;		
			for (int i = 0; i < prices.size(); i++) {
				totalPrice +=  prices.get(i) * quantity.get(i) ;
			}
		System.out.println("Total price : " + totalPrice);
	}
	

	
		
		
		
		
	
}

package com.flipkart.tasks.test;

import static com.flipkart.tasks.util.ConfigFileReader.getData;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.flipkart.tasks.pages.HomePage;
import com.flipkart.tasks.pages.ItemDetailsPage;
import com.flipkart.tasks.pages.LoginPage;
import com.flipkart.tasks.pages.SearchResultsPage;
import com.flipkart.tasks.pages.WishListPage;
import com.flipkart.tasks.util.BaseTest;

public class WishListTest extends BaseTest {
	
	LoginPage loginPage;
	HomePage homePage;
	SearchResultsPage searchResults;
	ItemDetailsPage itemDetails;
	WishListPage wishList;
	
	@Test
	public void login() throws IOException {
		//Initializing the login pge
		loginPage = new LoginPage(driver);
		
		//Validating login page displayed or not
		Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login window not displayed");
		
		
		loginPage.click_Username().sendKeys(getData().getProperty("Username"));
		loginPage.click_Password().sendKeys(getData().getProperty("Password"));
		//Assert.assertTrue(test.info("from assert not eqaul to null")==null);
		loginPage.clickLoginButton();
		
		homePage = new HomePage(driver);
		//Validating login
		String actualName = homePage.getProfileName().getText();
		System.out.println(actualName);
		if (!	(actualName.equals(getData().getProperty("ProfileName")))	) {
			log.warn("UserName does not matches with data");
			util.takeSnap("Login_Failed.png",driver);
			Assert.assertTrue(false);
		}
		log.info("Login passed");
	}
	
	
	
	
	@Test(dependsOnMethods = "login")
	public void addingToWishList() {	//TestCase ID : TC_WL_01
		
		//Searching for a product
		homePage.searchFor(getData().getProperty("SearchText"));
		//homePage.searchFor("mobiles");
		searchResults = new SearchResultsPage(driver);
		
		//Selecting the product
		String productName = searchResults.selectItem(1); 
		itemDetails = new ItemDetailsPage(driver);
		Assert.assertTrue(itemDetails.isPageOpened(),"Could not load Item Details Page.");
		itemDetails.gotoItemPageWindow();
		
		//Checking the Product is already added to Cart
		Assert.assertFalse(itemDetails.isProductAlreadyAddedToWishList(), "Product already Added to WishList");
		
		//Adding to Wish list
		itemDetails.addToWishlist();
		Assert.assertTrue(itemDetails.isAddedMessageDisplayed(), "Added to WIshList is not Displayed");
		
		
		//Navigating to WishList 
		homePage.moveOverMenu();
		homePage.clickWishList();
		wishList = new WishListPage(driver);
		String allProductNamesInWishList = wishList.getAllNames();
		
		//Validation of product in WishList
		Assert.assertTrue(allProductNamesInWishList.contains(productName), "Product name is not the same");
	}
	
	
	@Test (dependsOnMethods="login")
	public void removingFromWishList() {   //TC_WL_10
		
		//Navigating to WishList 
		homePage.moveOverMenu();
		homePage.clickWishList();
		wishList = new WishListPage(driver);
		String allProductNamesInWishList	=	 wishList.getAllNames();
		
		//Removing the Product
		String productNameInList = wishList.getName().get(0).getText(); 
		wishList.clickDeleteButton();
		wishList.clickConfirmButton();
		
		//Validation 
		Assert.assertTrue(wishList.isMessageDisplayed(), "Alert Message not Displayed");
		String productsNameAfterRemove = wishList.getAllNames();
		Assert.assertNotEquals	(	allProductNamesInWishList	, 	productsNameAfterRemove );
		Assert.assertEquals	(	productNameInList.concat(productsNameAfterRemove),	allProductNamesInWishList );
		
	}
	
	@Test(dependsOnMethods = "login")
	public void addToWishListFromSearchResults() {
		
		//Searching for product
			homePage.searchFor(getData().getProperty("SearchText"));
			searchResults = new  SearchResultsPage(driver);
			String productName="";
		
		//Adding to Wish List
			for(int i=0; i<10; i++) {
				if(! searchResults.isProductAlreadyInWishList(i)) {
				searchResults.addToWishList(i);
				productName = searchResults.getProductName(i);
				break;
				}	
			}
		
		softAssert.assertTrue(searchResults.isAddedMessageDisplayed(), "Added to WishList is not Displayed");
		
		//Navigating to WishList 
			homePage.moveOverMenu();
			homePage.clickWishList();
			wishList = new WishListPage(driver);
			String allProductNamesInWishList = wishList.getAllNames();
		
		//Validation of product in WishList
			Assert.assertTrue(allProductNamesInWishList.contains(productName), "Product name is not the same");
			softAssert.assertAll();
		
	}
	
	@Test(dependsOnMethods = "login")
	public void addToWishListFromSimilarProducts() throws InterruptedException {
			
			//Searching for a product
			homePage.searchFor(getData().getProperty("SearchText"));
		
			
			//Selecting the product
			searchResults = new SearchResultsPage(driver);
			searchResults.selectItem(1); 
			itemDetails = new ItemDetailsPage(driver);
			Assert.assertTrue(itemDetails.isPageOpened(),"Could not load Item Details Page.");
			itemDetails.gotoItemPageWindow();
			
			//Move to Similar Products
			itemDetails.goToSimilarProductSection();
			System.out.println("Gone to similat prodfuct .");
			
			//Adding to Wish List
			String productName="";
			for(int i=0; i<19; i++) {
				if(! itemDetails.isProductAlreadyInWishList(i)) {
					Thread.sleep(20000);
				itemDetails.addToWishList(i);
				productName = itemDetails.getProductName(i);
				break;
				}	
			}
			
			//Validating Alert message
			softAssert.assertTrue(searchResults.isAddedMessageDisplayed(), "Added to WishList is not Displayed");
			
			//Navigating to WishList 
			homePage.moveOverMenu();
			homePage.clickWishList();
			wishList = new WishListPage(driver);
			String allProductNamesInWishList = wishList.getAllNames();
			System.out.println("Product Name in Similar Product section : " + productName);

			//Validation of product in WishList
			Assert.assertTrue(allProductNamesInWishList.contains(productName), "Product name is not the same");
			softAssert.assertAll();
			
	}
	
	
	
}

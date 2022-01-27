package task1_3;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class TestLinks extends DriverSetup {
	
	 List<WebElement> all_Links;
	 Set<String> windows;

		
	public  Boolean verifyLinksz(String name, String link ) {
				try {
					URL url = new URL(link);
					HttpURLConnection huc = (HttpURLConnection)url.openConnection();
					huc.setConnectTimeout(5000);
					huc.connect();
					if(huc.getResponseCode()>=400) {
						System.out.println("Broken Link "+name + " --> "+ huc.getResponseMessage());
						sa.assertFalse(false, name);
						return false;
					}
					else  {
						System.out.println(name + " --> "+ huc.getResponseMessage() );
						
						return true;
					}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Exception caught Verifying Link "+ name +"  "+ e.getMessage());
				}
				return false;
		}
	 
	@Test
	public void links() throws Exception {
		
		  all_Links = driver.findElements(By.tagName("a")); 
		  System.out.println("Total links : "+ all_Links.size());
		  Boolean verifyLink;
		  actions = new Actions(driver);
		  System.out.println(all_Links);
		  
		  for (int i = 0; i < 50  ; i++) {
			  String urlName = all_Links.get(i).getText();
			  String expectedURL =  all_Links.get(i).getAttribute("href");
			  verifyLink = verifyLinksz(urlName, all_Links.get(i).getAttribute("href"));
				try {
					if ( (!(all_Links.get(i).getAttribute("href") == null )) && verifyLink ) {
						// Clicking with Control key to open in new window
						actions.keyDown(Keys.LEFT_CONTROL) 
					       .click(all_Links.get(i)) 
					       .keyUp(Keys.LEFT_CONTROL) 
					       .build() 
					       .perform();
						
						//Getting Window Handles
						windows	= driver.getWindowHandles();
						Iterator<String> itr = windows.iterator();
						String parent= itr.next();
						System.out.println("Windows size : "+ windows.size());
							if (!(windows.size()==1)) { //Checking new opened opened or not
								String secondWin = itr.next();
								driver.switchTo().window(secondWin);
								String actualURL = driver.getCurrentUrl();
								sa.assertEquals(actualURL, expectedURL);  //Checking URL is same.
								
								System.out.println(urlName + " is Passed. " );
								driver.close();
								driver.switchTo().window(parent);
								}
							else {
							System.out.println("Window not opened for " +all_Links.get(i).getText() );
							sa.assertTrue(false, urlName);
							}
					}
					catch (Exception e) {
					
					System.out.println("exception occured in : "+  urlName + e.getMessage());
				} 
			
		}	  
		  sa.assertAll();
		 
		
	}


	
	}
	

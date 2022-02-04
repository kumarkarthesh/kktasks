package POM_task.Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import POM_task.Utils.ConfigFileReader;



public class BasePage  {
	Actions actions;
	public WebDriver driver;
	SoftAssert sa = new SoftAssert();
	public ConfigFileReader config = new ConfigFileReader();
	
	public BasePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]/div/div")
	private WebElement _profileMenu;
	
	
	public  WebElement get_profileMenu() {
		return _profileMenu;
	}
	
	@FindBy(tagName="a")
	private List<WebElement> links;
	
	public void getLinks() {
		
		actions= new Actions(driver);
		System.out.println("Link toatl : "+ links.size());
		/*
		 * System.out.println(links.get(10));
		 * System.out.println(links.get(10).getText()); for (int i = 0; i < 5; i++) {
		 * System.out.println(links.get(i).getAttribute("href")); }
		 */
		System.out.println("=====\nlinks click");
		
		for (int i = 0; i < links.size() ; i++) {
				System.out.println("=======\n" +i+"\n"+ links.get(i).getText());
				String expectedURL = links.get(i).getAttribute("href") ;
				System.out.println("href= --> "+ expectedURL );
				
				if(!(links.get(i).getAttribute("href")==null)) {
					try {
						actions.keyDown(Keys.LEFT_CONTROL) 
						   .click(links.get(i)) 
						   		.keyUp(Keys.LEFT_CONTROL) 
						   				.build().perform();
							} 
						catch (Exception e) {
						
								System.out.println("exception caught");
								}
					getWindowControl(i,expectedURL);
				}
				
			
		} 
		sa.assertAll();
	}

	private void getWindowControl(int i, String expectedURL) {
		
		String parent=driver.getWindowHandle();
		//String expectedURL = url;
		Set<String>s=driver.getWindowHandles();
		Iterator<String> I1= s.iterator();
		
		
		while(I1.hasNext()){
		
			String child_window = I1.next();
			if(!parent.equals(child_window)){
				driver.switchTo().window(child_window);
				String actualURL = driver.getCurrentUrl();
				sa.assertEquals(expectedURL , actualURL);
				System.out.println("Child ---> " + actualURL );
				driver.close();
				}
				 
			}
		driver.switchTo().window(parent);
		
	
	}
}
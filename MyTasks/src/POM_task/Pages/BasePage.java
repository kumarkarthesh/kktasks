package POM_task.Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import POM_task.Utils.ConfigFileReader;



public class BasePage  {
	
	public WebDriver driver;
	public ConfigFileReader config = new ConfigFileReader();
	
	public BasePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
		
		
}

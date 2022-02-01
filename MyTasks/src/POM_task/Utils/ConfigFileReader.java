package POM_task.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader  {
	
	 Properties prop;
	
	public ConfigFileReader() {
		File file = new File ("C:\\Users\\elcot\\git\\repository\\MyTasks\\src\\task1_3\\Configurations\\config.properties");
		FileInputStream fileInput = null;
		try {
		
			 fileInput =  new FileInputStream(file);
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		prop = new Properties();
		
		try {
			prop.load(fileInput);;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}

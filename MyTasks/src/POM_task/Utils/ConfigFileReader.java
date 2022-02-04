package POM_task.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	public Properties prop;

	public ConfigFileReader() {
		File file = new File ("C:\\Users\\elcot\\git\\repository\\MyTasks\\src\\POM_task\\Utils\\config.properties");
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

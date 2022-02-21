package com.flipkart.tasks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	

	
	public static Properties prop2;
	
	
	public static Properties getData() {
		
		File file = new File ("Configs//config.properties");
		FileInputStream fileInput = null;
		try {
		
			 fileInput =  new FileInputStream(file);
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		prop2 = new Properties();
		
		try {
			prop2.load(fileInput);;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop2;
	}
	

}

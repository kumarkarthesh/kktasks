package com.flipkart.tasks.util;

import static com.flipkart.tasks.util.ConfigFileReader.getData;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Chexck {
	
	 static {
		    File log4j2File = new File("C:\\Users\\elcot\\eclipse-workspace\\tasks\\Configs\\log4j2.xml");
		    System.setProperty("log4j2.configurationFile", log4j2File.toURI().toString());
		}
	 
	private static final Logger logger = LogManager.getLogger(App.class);
 public static void main( String[] args )
 {
     System.out.println( "Hello World!" );
    
    
         logger.debug("This is a debug message");
         logger.info("This is an info message");
         logger.warn("This is a warn message");
         logger.error("This is an error message");
         logger.fatal("This is a fatal message");
         logger.trace("trace ");
         
         System.out.println("From config filr " + getData().getProperty("ProfileName"));
     }
     
	
}

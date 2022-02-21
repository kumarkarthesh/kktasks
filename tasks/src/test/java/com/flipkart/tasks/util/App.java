package com.flipkart.tasks.util;

import static com.flipkart.tasks.util.ConfigFileReader.getData;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Hello world!
 *
 */
public class App {
	
	/*
	 * static { try { InputStream inputStream = new
	 * FileInputStream("C:\\Users\\elcot\\eclipse-workspace\\log4j2.properties");
	 * ConfigurationSource source = new ConfigurationSource(inputStream);
	 * Configurator.initialize(null, source); } catch (Exception ex) { // Handle
	 * here System.out.println("exception"); } }
	 */
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

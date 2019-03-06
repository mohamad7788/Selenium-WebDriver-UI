package main.java.com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WebDriverConfig {
	
	public static String BROWSER_DRIVER_PROPERTY_NAME="";  
	public static String BROWSER_DRIVER_PROPERTY_VALUE="";
	public static String BROWSER_DRIVER_NAME="";
	public static String SEARCH_ENGINE="";
	public static String SEARCH_FOR_PAGE="";
	public static String SELECT_CITY="";

	static {
		Properties prop = new Properties();
		FileInputStream input;
		try {
			input = new FileInputStream("resources\\config.properties");
			prop.load(input);
			BROWSER_DRIVER_PROPERTY_NAME=prop.getProperty("browser_driver_property_name");
			BROWSER_DRIVER_PROPERTY_VALUE=prop.getProperty("browser_driver_property_value");
			BROWSER_DRIVER_NAME=prop.getProperty("browser_driver_name");
			SEARCH_ENGINE=prop.getProperty("search_site");
			SEARCH_FOR_PAGE=prop.getProperty("search_for_page");
			SELECT_CITY=prop.getProperty("select_city");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

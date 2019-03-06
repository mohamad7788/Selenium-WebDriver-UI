package main.java.com.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {
	
	public static WebDriver create(WebDriverType type) {
		WebDriver object = null;
		
		System.out.println("create webdriver with : "+type);
		//System.setProperty("webdriver.chrome.driver","resources\\chromedriver.exe");
		System.setProperty(WebDriverConfig.BROWSER_DRIVER_PROPERTY_NAME,WebDriverConfig.BROWSER_DRIVER_PROPERTY_VALUE);
		switch (type) {

		case CHROME :
			object = new ChromeDriver();
			break;
			
		case FIREFOX : 
			object = new FirefoxDriver();
			break;
			
		case IE : 
			object = new InternetExplorerDriver();
			break;
			
		case OPERA : 
			object = new OperaDriver();
			break;
			
		case SAFARI : 
			object = new SafariDriver();
			break;
			
		default:
			System.out.println("WebDriver type "+type+" isn't supported, return null object");
			break;
		}
		return object;
	}
}

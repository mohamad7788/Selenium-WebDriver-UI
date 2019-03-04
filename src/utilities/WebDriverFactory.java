package utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {




	public static WebDriver create(WebDriverType type) {
		WebDriver object = null;
		
		System.out.println("create webdriver with : "+type);
		
		switch (type) {

		case CHROME :
			//System.setProperty("webdriver.chrome.driver","resources\\chromedriver.exe");
			System.setProperty(WebDrierConfig.CHROME_DRIVER_NAME,WebDrierConfig.CHROME_DRIVER_PATH);
			object = new ChromeDriver();
			break;
			
		case EDGE :
			System.setProperty(WebDrierConfig.EDGE_DRIVER_NAME,WebDrierConfig.EDGE_DRIVER_PATH);
			object = new EdgeDriver();
			break ;
			
		case FIREFOX : 
			System.setProperty(WebDrierConfig.FIREFOX_DRIVER_NAME,WebDrierConfig.FIREFOX_DRIVER_PATH);
			object = new FirefoxDriver();
			break;
			
		case IE : 
			System.setProperty(WebDrierConfig.IE_DRIVER_NAME,WebDrierConfig.IE_DRIVER_PATH);
			object = new InternetExplorerDriver();
			break;
			
		case OPERA : 
			System.setProperty(WebDrierConfig.OPERA_DRIVER_NAME,WebDrierConfig.OPERA_DRIVER_PATH);
			object = new OperaDriver();
			break;
			
		case SAFARI : 
			System.setProperty(WebDrierConfig.SAFARI_DRIVER_NAME,WebDrierConfig.SAFARI_DRIVER_PATH);
			object = new SafariDriver();
			break;
			
		default:
			System.out.println("WebDriver type "+type+" isn't supported, return null object");
			break;
		}
		return object;
	}
}

package main.java.com.pageObjects;

import org.openqa.selenium.WebDriver;

import main.java.com.utilities.WebDriverConfig;




public class BasePageObject {

	protected WebDriver webDriver;

	public BasePageObject(WebDriver webDriver){
		this.webDriver = webDriver;
		webDriver.get(WebDriverConfig.SEARCH_ENGINE);
	}
	public BasePageObject(WebDriver webDriver,String URL){
		this.webDriver = webDriver;
		webDriver.get(URL);
	}


	public WebDriver getWebDriver() {
		return this.webDriver;
	}


	public void quit()   {
		webDriver.quit();
	}


}

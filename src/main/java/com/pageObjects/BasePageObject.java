package main.java.com.pageObjects;

import org.openqa.selenium.WebDriver;

import utilities.WebDrierConfig;


public class BasePageObject {

    protected WebDriver webDriver;

    public BasePageObject(WebDriver webDriver){
        this.webDriver = webDriver;
        //webDriver.get("https://www.google.com");
      webDriver.get(WebDrierConfig.SEARCH_ENGINE);
    }

    
    public WebDriver getWebDriver() {
    	return this.webDriver;
    }
    
    
    public void quit()   {
    	webDriver.quit();
    }
    

}

package main.java.com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomeSearchPage extends BasePageObject {

    @FindBy(name = "q")
    private WebElement searchBox;
    

    public HomeSearchPage(WebDriver webDriver){
      super(webDriver);  
    }

    public SearchResultsPage searchWebSite(String searchWebPage){
    	// Search
    	searchBox=webDriver.findElement(By.name("q"));
    	searchBox.clear();
    	searchBox.sendKeys(searchWebPage);
    	searchBox.sendKeys(Keys.ENTER);
    	SearchResultsPage resultPage = PageFactory.initElements(this.webDriver, SearchResultsPage.class);
    	resultPage.waitForPage();
        return resultPage;
    }
    
    public WebDriver getWebDriver() {
    	return this.webDriver;
    }
    
    
    public void quit()   {
    	webDriver.quit();
    }

}

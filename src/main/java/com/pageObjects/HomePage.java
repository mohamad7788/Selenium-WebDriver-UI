package main.java.com.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by draicu on 8/1/2016.
 */
public class HomePage {

    @FindBy(name = "q")
    private WebElement searchBox;
    
    private WebDriver webDriver;

    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public ResultsPage searchWebSite(){
		// Search 
    	searchBox.clear();
    	searchBox.sendKeys("ynetnews");
    	searchBox.sendKeys(Keys.ENTER);
    	//ResultsPage resultPage= (ResultsPage) PageFactory.initElements(webDriver, ResultsPage.class);
    	ResultsPage resultPage = PageFactory.initElements(webDriver, ResultsPage.class);
    	resultPage.waitForPage();
        return resultPage;
    }
    
    public WebDriver getWebDriver() {
    	return this.webDriver;
    }
}

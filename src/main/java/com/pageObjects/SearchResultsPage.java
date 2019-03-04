package main.java.com.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {

  
    @FindBy(xpath = "//h3[@class='LC20lb']")
    private List<WebElement> results;

    private WebDriver webDriver;
    public SearchResultsPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    
    public int getResultsCount(){
        return results.size();
    }

    public WebsiteTargetPage selectTargetPage(){	
    	results.get(0).click();
    	WebsiteTargetPage targetPage = PageFactory.initElements(webDriver, WebsiteTargetPage.class);
        return targetPage;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOfAllElements(results));
    }


}
package main.java.com.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by draicu on 8/1/2016.
 */
public class ResultsPage {

  
    @FindBy(xpath = "//h3[@class='LC20lb']")
    private List<WebElement> results;

    private WebDriver webDriver;
    public ResultsPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    
    public int getResultsCount(){
        return results.size();
    }

    public TargetPage selectFirstResult(){	
    	results.get(0).click();
    	TargetPage targetPage = PageFactory.initElements(webDriver, TargetPage.class);
        return targetPage;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(webDriver,10);
        wait.until(ExpectedConditions.visibilityOfAllElements(results));
    }


}
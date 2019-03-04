import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.WebDriverCommandProcessor;

import main.java.com.pageObjects.ArticlePage;
import main.java.com.pageObjects.HomeSearchPage;
import main.java.com.pageObjects.SearchResultsPage;
import main.java.com.pageObjects.WebsiteTargetPage;
import utilities.WebDriverFactory;
import utilities.WebDriverType;

public class Main {

	
	
	public static void main(String[] args) {
		
		HomeSearchPage homePage = new HomeSearchPage(WebDriverFactory.create(WebDriverType.CHROME));
		SearchResultsPage resultsPage = homePage.searchWebSite("ynetnews");
		WebsiteTargetPage targetPage = resultsPage.selectTargetPage();
		targetPage.isPageOpened();
		targetPage.printCurrentWeather();
		targetPage.selectCityForWeather("Elat");
		targetPage.printCurrentWeather();
		targetPage.changeWindowSize(new Dimension(1920, 1080));
		ArticlePage articlePage = targetPage.openArticlePage();
		articlePage.sendToFriendForm();
		targetPage.checkUpdateMovment();
	
		targetPage.quit();
	}
}

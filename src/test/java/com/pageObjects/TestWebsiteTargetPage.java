package test.java.com.pageObjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import main.java.com.pageObjects.HomeSearchPage;
import main.java.com.pageObjects.SearchResultsPage;
import main.java.com.pageObjects.WebsiteTargetPage;
import utilities.WebDriverFactory;
import utilities.WebDriverType;

class TestWebsiteTargetPage  {

	private static SearchResultsPage resultsPage;
	private static WebDriver webDriver ; 
	private static HomeSearchPage homeSearchPage;
	private static WebsiteTargetPage websiteTargetPage;
	

	@Before
	static void setUp() throws Exception {
		homeSearchPage = new HomeSearchPage(WebDriverFactory.create(WebDriverType.CHROME));
		webDriver = homeSearchPage.getWebDriver();
		resultsPage = homeSearchPage.searchWebSite("ynetnews");
		websiteTargetPage = resultsPage.selectTargetPage();
	}

	@After
	void tearDown() throws Exception {
		webDriver.quit();
	}


	@Test
	void testIsPageOpened() {
		assertTrue(websiteTargetPage.isPageOpened());
	}

	@Test
	void testGetWeatherTempruture() {
		String tempruture = websiteTargetPage.getWeatherTempruture() ;  
		System.out.println("Tempruture="+tempruture);
		assertNotNull(tempruture);
	}

	@Test
	void testGetWeatherCity() {
		String city = websiteTargetPage.getWeatherCity() ;
		System.out.println("city="+city);
		assertNotNull(city);
	}

	@Test
	void testGetSelectedCity() {
		String city = websiteTargetPage.getSelectedCity();
		System.out.println("city="+city);
		assertNotNull(city);
	}

	@Test
	void testSetSelectedCity() {
		String city1 = websiteTargetPage.getSelectedCity();
		websiteTargetPage.selectCityForWeather("Elat");
		String city2 = websiteTargetPage.getSelectedCity();
		assertFalse(city1.equals(city2));
	}

	@Test
	void testOpenArticel() {
		assertNotNull(websiteTargetPage.getArticaleElement());
	}

	@Test
	void testMouseMovment() {
		WebElement webElement = webDriver.findElement(By.xpath("//iframe[contains(@src,'/Ticker/')]"));
		WebDriver currentFrame = webDriver.switchTo().frame(webElement);
		WebElement ticker = webDriver.findElement(By.id("aTicker"));
		String ticketAttr1 = ticker.getAttribute("style");
		String ticketAttr2 = ticker.getAttribute("style");
		assertNotEquals(ticketAttr1, ticketAttr2);
		
		WebElement mouseOnElement = webDriver.findElement(By.xpath("//*/table/tbody/tr[8]/td/a[contains(@href,'/articles')]"));
		Action action = new Actions(webDriver).moveToElement(mouseOnElement).build() ;  
		Point staPoint = mouseOnElement.getLocation();			
		action.perform();
		Point endPoint = mouseOnElement.getLocation();
		assertEquals(staPoint.getX(), endPoint.getX());
		assertTrue(endPoint.getY()-staPoint.getY()<10);
	}

	@Test
	void testChangeWindowSize() {
		Dimension dimension = new Dimension(1920, 1080);
		webDriver.manage().window().setSize(dimension);
		Dimension actualDimension = webDriver.manage().window().getSize();
		assertEquals(dimension, actualDimension);
	}
}

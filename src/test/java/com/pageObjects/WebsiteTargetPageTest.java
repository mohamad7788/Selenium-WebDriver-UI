package test.java.com.pageObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import main.java.com.pageObjects.WebsiteTargetPage;
import main.java.com.utilities.WebDriverConfig;
import main.java.com.utilities.WebDriverFactory;
import main.java.com.utilities.WebDriverType;


public class WebsiteTargetPageTest  {

	private static WebDriver webDriver ; 
	private static WebsiteTargetPage websiteTargetPage;
	

	@BeforeClass
	public static void setUp() throws Exception {
		//homeSearchPage = new HomeSearchPage(WebDriverFactory.create(WebDriverType.CHROME));
		//webDriver = homeSearchPage.getWebDriver();
		//resultsPage = homeSearchPage.searchWebSite("ynetnews");
		webDriver=WebDriverFactory.create(WebDriverType.CHROME);
		websiteTargetPage = new WebsiteTargetPage(webDriver,"http://www.ynetnews.com");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		webDriver.quit();
	}


	@Test
	public void testIsPageOpened() {
		assertTrue(websiteTargetPage.isPageOpened());
	}

	@Test
	public void testGetWeatherTemperature() {
		String tempruture = websiteTargetPage.getWeatherTemperature() ;  
		System.out.println("Tempruture="+tempruture);
		assertNotNull(tempruture);
	}

	@Test
	public void testGetWeatherCity() {
		String city = websiteTargetPage.getWeatherCity() ;
		System.out.println("city="+city);
		assertNotNull(city);
	}

	@Test
	public void testGetSelectedCity() {
		String city = websiteTargetPage.getSelectedCity();
		System.out.println("city="+city);
		assertNotNull(city);
	}

	@Test
	public void testSetSelectedCity() {
		String city1 = websiteTargetPage.getSelectedCity();
		websiteTargetPage.selectCityForWeather(WebDriverConfig.SELECT_CITY);
		String city2 = websiteTargetPage.getSelectedCity();
		assertEquals("Eilat", city2);
		assertFalse(city1.equals(city2));
	}

	@Test
	public void testOpenArticel() {
		assertNotNull(websiteTargetPage.getArticaleElement());
	}

	@Test
	public void testMouseMovment() {
		WebElement webElement = webDriver.findElement(By.xpath("//iframe[contains(@src,'/Ticker/')]"));
		WebDriver currentFrame = webDriver.switchTo().frame(webElement);
		WebElement ticker = webDriver.findElement(By.id("aTicker"));
		String ticketAttr1 = ticker.getAttribute("style");
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void testChangeWindowSize() {
		Dimension dimension = new Dimension(1920, 1080);
		webDriver.manage().window().setSize(dimension);
		Dimension actualDimension = webDriver.manage().window().getSize();
		assertEquals(dimension, actualDimension);
	}
}

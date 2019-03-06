package test.java.com.pageObjects;



import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import main.java.com.pageObjects.HomeSearchPage;
import main.java.com.utilities.WebDriverConfig;
import main.java.com.utilities.WebDriverFactory;
import main.java.com.utilities.WebDriverType;


public class HomePageTest {

	private static  WebDriver webDriver;
	private static  HomeSearchPage homePage;

	@BeforeClass
	public static void setUp() throws Exception {
		webDriver = WebDriverFactory.create(WebDriverType.CHROME);
		webDriver.get(WebDriverConfig.SEARCH_ENGINE);
		homePage = PageFactory.initElements(webDriver, HomeSearchPage.class);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		webDriver.quit();
	}


	@Test
	public void testSearchWebSite() {
		assertNotNull(homePage.searchWebSite(WebDriverConfig.SEARCH_FOR_PAGE));
	}

}
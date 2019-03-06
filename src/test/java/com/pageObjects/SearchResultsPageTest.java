package test.java.com.pageObjects;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import main.java.com.pageObjects.HomeSearchPage;
import main.java.com.pageObjects.SearchResultsPage;
import main.java.com.utilities.WebDriverConfig;
import main.java.com.utilities.WebDriverFactory;
import main.java.com.utilities.WebDriverType;


public class SearchResultsPageTest {

	private static HomeSearchPage homePage ;
	private static SearchResultsPage resultsPage;
	
	@BeforeClass
	public static void setUp() throws Exception {
		homePage = new HomeSearchPage(WebDriverFactory.create(WebDriverType.CHROME));
		resultsPage = homePage.searchWebSite(WebDriverConfig.SEARCH_FOR_PAGE);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		homePage.getWebDriver().quit();
		
	}

	@Test
	public void testGetResultsCount() {
		assertNotEquals(0, resultsPage.getResultsCount());
	}

	@Test
	public void testSelectTargetPage() {
		List<WebElement> elements = resultsPage.getSearchResults();
		WebElement firstResult = elements.get(0);
		String hrefValue=firstResult.getAttribute("href");
		System.out.println("hrefValue="+hrefValue);
		assertTrue(hrefValue.contains(WebDriverConfig.SEARCH_FOR_PAGE));
	}

}
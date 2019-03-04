package test.java.com.pageObjects;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import main.java.com.pageObjects.HomeSearchPage;
import main.java.com.pageObjects.SearchResultsPage;
import utilities.WebDrierConfig;
import utilities.WebDriverFactory;
import utilities.WebDriverType;

class TestSearchResultsPage {

	private HomeSearchPage homePage ;
	private SearchResultsPage resultsPage;
	
	@Before
	void setUp() throws Exception {
		homePage = new HomeSearchPage(WebDriverFactory.create(WebDriverType.CHROME));
		resultsPage = homePage.searchWebSite(WebDrierConfig.SEARCH_FOR_PAGE);
	}

	@After
	void tearDown() throws Exception {
		homePage.getWebDriver().quit();
		
	}

	@Test
	void testGetResultsCount() {
		assertNotEquals(0, resultsPage.getResultsCount());
	}

	@Test
	void testSelectTargetPage() {
		assertNotNull(resultsPage.selectTargetPage());
	}

}

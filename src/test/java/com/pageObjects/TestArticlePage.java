package test.java.com.pageObjects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import main.java.com.pageObjects.ArticlePage;
import main.java.com.pageObjects.HomeSearchPage;
import main.java.com.pageObjects.SearchResultsPage;
import main.java.com.pageObjects.WebsiteTargetPage;
import utilities.WebDrierConfig;
import utilities.WebDriverFactory;
import utilities.WebDriverType;



class TestArticlePage  {

	private static SearchResultsPage resultsPage;
	private static WebDriver webDriver ; 
	private static HomeSearchPage homeSearchPage;
	private static WebsiteTargetPage websiteTargetPage;
	private static ArticlePage articlePage;
	
	@BeforeEach
	void setUp() throws Exception {
		homeSearchPage = new HomeSearchPage(WebDriverFactory.create(WebDriverType.CHROME));
		webDriver = homeSearchPage.getWebDriver();
		resultsPage = homeSearchPage.searchWebSite(WebDrierConfig.SEARCH_FOR_PAGE);
		websiteTargetPage = resultsPage.selectTargetPage();
		articlePage = websiteTargetPage.openArticlePage();
	}

	@AfterEach
	void tearDown() throws Exception {
		webDriver.quit();
	}

	@Test
	void testArticlePage() {
		String currentUrl = webDriver.getCurrentUrl();
		assertTrue(currentUrl.contains("www.ynetnews"));
		assertTrue(currentUrl.contains("articles"));
		assertNotNull(articlePage);
	}

	@Test
	void testFormCaptcha() {
		assertTrue(articlePage.sendToFriendForm());
	}

}

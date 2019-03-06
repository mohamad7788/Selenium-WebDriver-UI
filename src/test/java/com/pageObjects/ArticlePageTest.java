package test.java.com.pageObjects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import main.java.com.pageObjects.ArticlePage;
import main.java.com.pageObjects.HomeSearchPage;
import main.java.com.pageObjects.SearchResultsPage;
import main.java.com.pageObjects.WebsiteTargetPage;
import main.java.com.utilities.WebDriverConfig;
import main.java.com.utilities.WebDriverFactory;
import main.java.com.utilities.WebDriverType;




public class ArticlePageTest  {

	private static SearchResultsPage resultsPage;
	private static WebDriver webDriver ; 
	private static HomeSearchPage homeSearchPage;
	private static WebsiteTargetPage websiteTargetPage;
	private static ArticlePage articlePage;
	
	@BeforeClass
	public static void setUp() throws Exception {
		homeSearchPage = new HomeSearchPage(WebDriverFactory.create(WebDriverType.CHROME));
		webDriver = homeSearchPage.getWebDriver();
		resultsPage = homeSearchPage.searchWebSite(WebDriverConfig.SEARCH_FOR_PAGE);
		websiteTargetPage = resultsPage.selectTargetPage();
		articlePage = websiteTargetPage.openArticlePage();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		webDriver.quit();
	}

	@Test
	public void testArticlePage() {
		String currentUrl = webDriver.getCurrentUrl();
		assertTrue(currentUrl.contains("www.ynetnews"));
		System.out.println("currentUrl="+currentUrl);
		assertTrue(currentUrl.contains("Article"));
		assertNotNull(articlePage);
	}

	@Test
	public void testFormCaptcha() {
		assertTrue(articlePage.sendToFriendForm());
	}

}

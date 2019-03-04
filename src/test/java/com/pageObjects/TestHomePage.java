package test.java.com.pageObjects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import main.java.com.pageObjects.HomeSearchPage;
import utilities.WebDrierConfig;
import utilities.WebDriverFactory;
import utilities.WebDriverType;

class TestHomePage {

	protected WebDriver webDriver;
    protected  HomeSearchPage homePage;

	@Before
	void setUp() throws Exception {
		webDriver = WebDriverFactory.create(WebDriverType.CHROME);
		webDriver.get(WebDrierConfig.SEARCH_ENGINE);
		homePage = PageFactory.initElements(webDriver, HomeSearchPage.class);
	}

	@After
	void tearDown() throws Exception {
		webDriver.quit();
	}


	@Test
	@DisplayName("testSearchWebSite")
	void testSearchWebSite() {
		assertNotNull(homePage.searchWebSite(WebDrierConfig.SEARCH_FOR_PAGE));
	}

}

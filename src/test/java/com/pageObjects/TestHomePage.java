package test.java.com.pageObjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import main.java.com.pageObjects.HomePage;
import utilities.WebDriverFactory;
import utilities.WebDriverType;

class TestHomePage {
	private WebDriver webDriver;
    protected  HomePage homePage;

	@BeforeEach
	void setUp() throws Exception {
		webDriver = WebDriverFactory.create(WebDriverType.CHROME);
		webDriver.get("https://www.google.com");
		homePage = PageFactory.initElements(webDriver, HomePage.class);
	}

	@AfterEach
	void tearDown() throws Exception {
		webDriver.quit();
	}


	@Test
	@DisplayName("testSearchWebSite")
	void testSearchWebSite() {
		assertNotNull(homePage.searchWebSite());
	}

}

package test.java.com.pageObjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import main.java.com.pageObjects.HomePage;
import main.java.com.pageObjects.ResultsPage;

class TestResultsPage extends TestHomePage{
	
	private WebDriver webDriver;
    protected  ResultsPage resultsPage;

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		webDriver = homePage.getWebDriver();
		resultsPage = homePage.searchWebSite();
	}

	@AfterEach
	void tearDown() throws Exception {
		super.tearDown();
	}

	

	@Test
	void testGetTotalJobs() {
		assertNotEquals(0, resultsPage.getResultsCount());
	}

	@Test
	void testGetResultsCount() {
		assertNotEquals(0, resultsPage.getResultsCount());
	}

	@Test
	void testSelectFirstResult() {
		assertNotNull(resultsPage.selectFirstResult());
	}

	@Test
	void testWaitForPage() {
		assertTrue(true);
	}

}

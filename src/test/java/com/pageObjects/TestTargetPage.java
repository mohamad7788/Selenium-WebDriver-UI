package test.java.com.pageObjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import main.java.com.pageObjects.ResultsPage;
import main.java.com.pageObjects.TargetPage;

class TestTargetPage extends TestResultsPage {
	
	private WebDriver webDriver;
    protected  TargetPage targetPage;

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		targetPage = resultsPage.selectFirstResult();
	}

	@AfterEach
	void tearDown() throws Exception {
		super.tearDown();
	}


	@Test
	void testIsPageOpened() {
		assertTrue(targetPage.isPageOpened());
	}

	@Test
	void testGetWeatherTempruture() {
		String tempruture = targetPage.getWeatherTempruture() ;  
		System.out.println("Tempruture="+tempruture);
		assertNotNull(tempruture);
	}

	@Test
	void testGetWeatherCity() {
		String city = targetPage.getWeatherCity() ;
		System.out.println("city="+city);
		assertNotNull(city);
	}

	@Test
	void testGetSelectedCity() {
		String city = targetPage.getSelectedCity();
		System.out.println("city="+city);
		assertNotNull(city);
	}

	@Test
	void testSetSelectedCity() {
		String city1 = targetPage.getSelectedCity();
		targetPage.setSelectedCity("Elat");
		String city2 = targetPage.getSelectedCity();
		
		assertFalse(city1.equals(city2));
	}

	@Test
	void testOpenArticel() {
		// TODO 
	}

	@Test
	void testCheckUpdateMovment() {
		 // TODO
	}

	@Test
	void testGetWebDriver() {
		 // TODO
	}

}

package main.java.com.pageObjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TargetPage {
	private WebDriver driver;

	@FindBy(id="cdanwmansrch_weathertemps")
	WebElement weatherTempruture;

	@FindBy(id="cdanwmansrch_weathercitieselect")
	WebElement weatherCity;

	@FindBy(id = "cdanwmansrch_weathercitieselect")
	WebElement selectedCity;

	@FindBy(xpath = "//a[starts-with(@href, '/articles/')]")
	WebElement article; 

	@FindBy(xpath = "//iframe[contains(@src,'/Ticker/')]")
	WebElement tickerFrame;

	//Constructor
	public TargetPage(WebDriver driver){
		this.driver=driver;

		//Initialise Elements
		PageFactory.initElements(driver, this);
	}

	public boolean isPageOpened(){
		//Assertion
		//return heading.getText().toString().contains("Apply to join our network as a developer");
		return true;
	}


	public String getWeatherTempruture() {
		return this.weatherTempruture.getText();
	}


	public String getWeatherCity() {
		return this.weatherCity.getText();
	}

	public String getSelectedCity() {
		Select select = new Select(selectedCity);
		WebElement selectedCityOption = select.getFirstSelectedOption();
		return selectedCityOption.getText();
	}


	public void setSelectedCity(String city) {
		Select select = new Select(selectedCity); 
		select.selectByValue(city);
	}

	public void openArticel() {

		article.click();

		// check if send to frined exisit 
		String str = driver.findElement(By.xpath("//span[contains(@class, 'ASLtextIcon') and text()='send to friend']")).getText();
		System.out.println("Text="+str);

		driver.findElement(By.xpath("//span[contains(@class, 'ASLtextIcon') and text()='send to friend']")).click();

		Set<String> handlers = driver.getWindowHandles();

		//Get the parent window handle and store it in a variable for future use
		String parentWindow = driver.getWindowHandle();

		//Iterate through the collection of all available window handles
		for (String childWindowHandle : driver.getWindowHandles()) {
			//Ignore which handle is equal to the parent handle
			if(!childWindowHandle.equalsIgnoreCase(parentWindow)){
				//Switch to the child window handle
				driver.switchTo().window(childWindowHandle);
				//Perform required operations on the current child window....
				driver.findElement(By.id("txtTo")).sendKeys("to_email@hotmail.com");
				driver.findElement(By.id("txtFromName")).sendKeys("from_email");
				driver.findElement(By.id("txtFromAddress")).sendKeys("from_email@hotmail.com");
				driver.findElement(By.id("txtRemarks")).sendKeys("my comments");
				driver.findElement(By.xpath("//a[contains(@href, 'javascript:document.frmSendto.submit()') and text()='Send']")).click();
				// TODO 
				//Verify there is a validation on the empty “captcha”
				//Thread.currentThread().sleep(5000);
				driver.close();
				//Switch back to the parent handle
				driver.switchTo().window(parentWindow);
				//Thread.currentThread().sleep(5000);
			}
		}


		driver.navigate().back();

		/*
		driver.findElement(By.xpath("//*[@id='top-story-1']/div[1]/div/a")).click();
		String updateTittle="ynetnews - News - Breaking News";
		System.out.println("Updates tittle = "+driver.getTitle());
		driver.navigate().back();
		 */
	}


	public void checkUpdateMovment() {

		try {
			
			WebDriver currentFrame = driver.switchTo().frame(tickerFrame);
			WebElement ticker = driver.findElement(By.id("aTicker"));
			String currentAtt1 = ticker.getAttribute("style");
			Thread.currentThread().sleep(10000);
			String currentAtt2 = ticker.getAttribute("style");
			System.out.println(currentAtt1);
			System.out.println(currentAtt2);


			// now which mouse - object change 
			//WebElement mouseOnElement = driver.findElement(By.id("aTicker"));
			WebElement mouseOnElement = driver.findElement(By.xpath("//*/table/tbody/tr[8]/td/a[contains(@href,'/articles')]"));
			Action action = new Actions(driver).moveToElement(mouseOnElement).build() ;  
			System.out.println("Style now : " + mouseOnElement.getLocation());			
			//action.perform();
			long start = System.currentTimeMillis();
			action.perform();
			long end = System.currentTimeMillis();
			Thread.currentThread().sleep(10000);
			System.out.println("Style now : " + mouseOnElement.getLocation());
			System.out.println("");


			driver.switchTo().defaultContent();
			driver.switchTo().parentFrame();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WebDriver getWebDriver() {
		return this.driver;
	}


}

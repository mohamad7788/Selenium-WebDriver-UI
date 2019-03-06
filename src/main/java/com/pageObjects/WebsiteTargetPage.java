package main.java.com.pageObjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class WebsiteTargetPage  extends BasePageObject{
	//private WebDriver driver;

	@FindBy(id="cdanwmansrch_weathertemps")
	WebElement weatherTemperature;

	@FindBy(id="cdanwmansrch_weathercitieselect")
	WebElement weatherCity;

	@FindBy(id = "cdanwmansrch_weathercitieselect")
	WebElement selectedCity;

	@FindBy(xpath = "//a[starts-with(@href, '/articles/')]")
	WebElement article; 

	@FindBy(xpath = "//iframe[contains(@src,'/Ticker/')]")
	WebElement tickerFrame;

	//Constructor
	public WebsiteTargetPage(WebDriver driver){
		super(driver);

		//Initialise Elements
		PageFactory.initElements(driver, this);
	}
	//Constructor
	public WebsiteTargetPage(WebDriver driver,String URL){
		super(driver,URL);
		//Initialise Elements
		PageFactory.initElements(driver, this);
	}

	public boolean isPageOpened(){
		boolean result = webDriver.getTitle().equals("ynetnews - Homepage") ;
		if(result)
			System.out.println("Correct Website opened");
		else
			System.out.println("In-Correct Website");
		return result;
	}


	public String getWeatherTemperature() {
		return this.weatherTemperature.getText();
	}


	public String getWeatherCity() {
		return this.weatherCity.getText();
	}

	public String getSelectedCity() {
		Select select = new Select(selectedCity);
		WebElement selectedCityOption = select.getFirstSelectedOption();
		return selectedCityOption.getText();
	}


	public void selectCityForWeather(String city) {
		Select select = new Select(selectedCity);
		select.selectByVisibleText(city);
	}

	public WebElement getArticaleElement() {
		return article;
	}

	public void sendToFriend() {
		article.click();
		// check if send to friend exist 
		String str = webDriver.findElement(By.xpath("//span[contains(@class, 'ASLtextIcon') and text()='send to friend']")).getText();
		System.out.println("Text="+str);

		webDriver.findElement(By.xpath("//span[contains(@class, 'ASLtextIcon') and text()='send to friend']")).click();


		Set<String> handlers = webDriver.getWindowHandles();


		//Get the parent window handle and store it in a variable for future use
		String parentWindow = webDriver.getWindowHandle();

		//Iterate through the collection of all available window handles
		for (String childWindowHandle : webDriver.getWindowHandles()) {

			//Ignore which handle is equal to the parent handle
			if(!childWindowHandle.equalsIgnoreCase(parentWindow)){
				//Switch to the child window handle
				webDriver.switchTo().window(childWindowHandle);

				WebElement scriptElement = webDriver.findElement(By.xpath("//script"));
				//scriptElement.getText().toLowerCase().contains("captcha");
				System.out.println(scriptElement.getText().toLowerCase());


				//*[@id="recapthcaMessage"]
				WebElement txtTo=webDriver.findElement(By.id("txtTo"));
				WebElement txtFromName=webDriver.findElement(By.id("txtFromName"));
				WebElement txtFromAddress=webDriver.findElement(By.id("txtFromAddress"));
				WebElement txtRemarks=webDriver.findElement(By.id("txtRemarks"));
				WebElement formSubmit=webDriver.findElement(By.xpath("//a[contains(@href, 'javascript:document.frmSendto.submit()') and text()='Send']"));

				txtTo.sendKeys("");
				txtFromName.sendKeys("");
				txtFromAddress.sendKeys("");
				txtRemarks.sendKeys("");
				formSubmit.click();
				WebElement captchaMessage=webDriver.findElement(By.xpath("//div[@id='recapthcaMessage']"));
				System.out.println(captchaMessage.getText());

				//Perform required operations on the current child window....
				webDriver.findElement(By.id("txtTo")).sendKeys("to_email@hotmail.com");
				webDriver.findElement(By.id("txtFromName")).sendKeys("from_email");
				webDriver.findElement(By.id("txtFromAddress")).sendKeys("from_email@hotmail.com");
				webDriver.findElement(By.id("txtRemarks")).sendKeys("my comments");
				webDriver.findElement(By.xpath("//a[contains(@href, 'javascript:document.frmSendto.submit()') and text()='Send']")).click();
				webDriver.close();
				//Switch back to the parent handle
				webDriver.switchTo().window(parentWindow);
				//Thread.currentThread().sleep(5000);
			}
		}
		webDriver.navigate().back();
	}


	public void checkUpdateMovment() {

		try {

			WebDriver currentFrame = webDriver.switchTo().frame(tickerFrame);
			WebElement ticker = webDriver.findElement(By.id("aTicker"));
			String currentAtt1 = ticker.getAttribute("style");
			Thread.currentThread().sleep(10000);
			String currentAtt2 = ticker.getAttribute("style");
			System.out.println(currentAtt1);
			System.out.println(currentAtt2);

			// now which mouse - object change 
			//WebElement mouseOnElement = driver.findElement(By.id("aTicker"));
			WebElement mouseOnElement = webDriver.findElement(By.xpath("//*/table/tbody/tr[8]/td/a[contains(@href,'/articles')]"));
			Action action = new Actions(webDriver).moveToElement(mouseOnElement).build() ;  
			System.out.println("Style now : " + mouseOnElement.getLocation());			
			//action.perform();
			long start = System.currentTimeMillis();
			action.perform();
			long end = System.currentTimeMillis();
			Thread.currentThread().sleep(10000);
			System.out.println("Style now : " + mouseOnElement.getLocation());
			System.out.println("");


			webDriver.switchTo().defaultContent();
			webDriver.switchTo().parentFrame();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ArticlePage openArticlePage(){	
		System.out.println("Openning article");
		article.click();
		ArticlePage articlePage = PageFactory.initElements(webDriver, ArticlePage.class);
		return articlePage;
	}

	public void changeWindowSize(Dimension dimension) {
		//driver.manage().window().setSize(new Dimension(1920,1080));
		webDriver.manage().window().setSize(dimension);
	}

	public void printCurrentWeather() {
		System.out.println("Weather in city "+getSelectedCity()+" is "+getWeatherTemperature());
	}

	public void quit() {
		try {
			System.out.println("closing the app..");
			Thread.currentThread().sleep(3000);
			webDriver.quit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

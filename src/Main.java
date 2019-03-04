import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WebDriverFactory;
import utilities.WebDriverType;

public class Main {

	private static String expectedTittle="ynetnews - Homepage" ;
	private static String expectedURl="https://www.ynetnews.com/home";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//System.setProperty("webdriver.chrome.driver","resources\\chromedriver.exe");

			WebDriver driver = WebDriverFactory.create(WebDriverType.CHROME);

			// And now use this to visit Google
			driver.get("https://www.google.com/");	

			String homeWindowHandler = driver.getWindowHandle();

			// Search 
			WebElement searchElement = driver.findElement(By.name("q"));
			searchElement.clear();
			searchElement.sendKeys("ynetnews");
			searchElement.sendKeys(Keys.ENTER);


			WebElement firstSearchResult = driver.findElement(By.xpath("//h3[@class='LC20lb']"));
			firstSearchResult.click();


			WebElement titleElement = driver.findElement(By.xpath("//meta[@content='ynetnews - Homepage']"));
			System.out.println(titleElement.getAttribute("content"));
			
			if(driver.getTitle().contains(expectedTittle) && driver.getCurrentUrl().contains(expectedURl))
				System.out.println("All ok");
			else
				System.out.println("Website not ok");

			
			// Get the weather for the default selected city
			String tempreture=driver.findElement(By.id("cdanwmansrch_weathertemps")).getText();
			String currentCity=driver.findElement(By.id("cdanwmansrch_weathercitieselect")).getText();

			//Select select = new Select(driver.findElement(By.xpath("select[@id="cdanwmansrch_weathercitieselect"]")));
			Select select = new Select(driver.findElement(By.xpath("//select")));
			WebElement option = select.getFirstSelectedOption();
			currentCity = option.getText();



			System.out.println("Temp="+tempreture+"\n city="+currentCity);


			// Get the weather after change city 
			select.selectByValue("Elat");
			tempreture=driver.findElement(By.id("cdanwmansrch_weathertemps")).getText();
			select = new Select(driver.findElement(By.xpath("//select")));
			option = select.getFirstSelectedOption();
			currentCity = option.getText();



			System.out.println("Temp="+tempreture+"\n city="+currentCity);

			// change resolution to 1920*1080
			driver.manage().window().setSize(new Dimension(1920,1080));

			// open articale 
			driver.findElement(By.xpath("//a[starts-with(@href, '/articles/')]")).click();

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

			driver.findElement(By.xpath("//*[@id='top-story-1']/div[1]/div/a")).click();
			String updateTittle="ynetnews - News - Breaking News";

			System.out.println("Updates tittle = "+driver.getTitle());

			driver.navigate().back();

			/*<div class="news_ticker_iframe">
			 * <iframe src="/Ext/Comp/Ticker/Dhtml_Flash_Ticker/0,12114,L-3089-253-150,00.html?js=1" onload="yq(this).contents().find('#getJSContent').css({'position':'static','display':'block'});" width="131" height="253" frameborder="0" marginheight="0" marginwidth="0" scrolling="no">
			 * </iframe>
			 * </div>
			 */

			WebElement webElement = driver.findElement(By.xpath("//iframe[contains(@src,'/Ticker/')]"));
			WebDriver currentFrame = driver.switchTo().frame(webElement);


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








			//driver.navigate().back();








			closeChildWindows(driver);
			driver.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Closing all opened child & main window
	private static void closeChildWindows(WebDriver driver) {
		try {
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> iter = windows.iterator();
			String[] winNames=new String[windows.size()];
			int i=0;
			while (iter.hasNext()) {
				winNames[i]=iter.next();
				i++;
			}
			if(winNames.length > 1) {
				for(i = winNames.length; i > 1; i--) {
					driver.switchTo().window(winNames[i - 1]);
					driver.close();
				}
			}
			driver.switchTo().window(winNames[0]);
		}
		catch(Exception e){         
			e.printStackTrace();
		}

	}

}

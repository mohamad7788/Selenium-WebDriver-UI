package main.java.com.pageObjects;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePage {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(@class, 'ASLtextIcon') and text()='send to friend']")
	WebElement sendToFriend;

	//Constructor
	public ArticlePage(WebDriver driver){
		this.driver=driver;
		//Initialise Elements
		PageFactory.initElements(driver, this);
	}


	public void sendToFriend() {


		sendToFriend.click();


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
	}



	public void waitForPage() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		//wait.until(ExpectedConditions.visibilityOfAllElements(results));

	}
	
	public void backToTarget() {
		driver.navigate().back();
	}






}

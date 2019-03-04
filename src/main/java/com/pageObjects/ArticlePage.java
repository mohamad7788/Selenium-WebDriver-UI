package main.java.com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePage {

	private WebDriver driver;

	@FindBy(xpath = "//span[contains(@class, 'ASLtextIcon') and text()='send to friend']")
	WebElement sendToFriend;

	public ArticlePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public boolean sendToFriendForm() {
		boolean resultFlag=true;
		sendToFriend.click();
		driver.getWindowHandles();

		//Get the parent window handle and store it in a variable for future use
		String parentWindow = driver.getWindowHandle();
		//Iterate through the collection of all available window handles
		for (String childWindowHandle : driver.getWindowHandles()) {
			//Ignore which handle is equal to the parent handle
			if(!childWindowHandle.equalsIgnoreCase(parentWindow)){
				//Switch to the child window handle
				driver.switchTo().window(childWindowHandle);
				WebElement txtTo=driver.findElement(By.id("txtTo"));
				WebElement txtFromName=driver.findElement(By.id("txtFromName"));
				WebElement txtFromAddress=driver.findElement(By.id("txtFromAddress"));
				WebElement txtRemarks=driver.findElement(By.id("txtRemarks"));
				WebElement formSubmit=driver.findElement(By.xpath("//a[contains(@href, 'javascript:document.frmSendto.submit()') and text()='Send']"));
				txtTo.sendKeys("");
				txtFromName.sendKeys("");
				txtFromAddress.sendKeys("");
				txtRemarks.sendKeys("");
				formSubmit.click();
				WebElement captchaMessage=driver.findElement(By.xpath("//div[@id='recapthcaMessage']"));
				System.out.println(captchaMessage.getText());
				resultFlag=resultFlag&&!captchaMessage.getText().isEmpty();
				//Perform required operations on the current child window....
				txtTo.sendKeys("to_email@hotmail.com");
				txtFromName.sendKeys("from_email");
				txtFromAddress.sendKeys("from_email@hotmail.com");
				txtRemarks.sendKeys("my comments");
				formSubmit.click();
				driver.close();
				//Switch back to the parent handle
				driver.switchTo().window(parentWindow);
			}
		}
		driver.navigate().back();
		return resultFlag;
	}


	public void waitForPage() {
		new WebDriverWait(driver,10);
	}

	public void backToTarget() {
		driver.navigate().back();
	}

	
	public WebElement getSendToFriend() {
		return sendToFriend;
	}
	
	public void back() {
		driver.navigate().back();
	}




}

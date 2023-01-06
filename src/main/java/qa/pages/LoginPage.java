package qa.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.reactivex.rxjava3.functions.Action;

public class LoginPage {

	WebDriver ldriver;
	WaitHelper waitHelper;
	
////fluentwaitt//////
	
	public WebElement fluentlywait(WebElement ele) {
	
	Wait<WebDriver> wait = new FluentWait<WebDriver>(ldriver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
	
	wait.until(ExpectedConditions.elementToBeClickable(ele));
	return ele;
	}
	
	////fluentwaitt//////
	
	
	@FindBy(xpath="//input[@placeholder='Username']") WebElement username;
	@FindBy(xpath="//input[@placeholder='Password']") WebElement password;
	@FindBy(xpath="//button[normalize-space()='Login']") WebElement loginbtn;
	
	public LoginPage(WebDriver rdriver) {
		
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		waitHelper =new WaitHelper(ldriver);
	}
	
	public void doLogin(String uname,String passwod) {
		waitHelper.forThisElementWait(username, 10).sendKeys(uname);
		waitHelper.forThisElementWait(password, 10).sendKeys(passwod);
		waitHelper.forThisElementWait(loginbtn, 10).click();
		
		
//		fluentlywait(username).sendKeys(uname);
//		fluentlywait(password).sendKeys(passwod);
//		fluentlywait(loginbtn).click();
		
		
	}
	
	
}

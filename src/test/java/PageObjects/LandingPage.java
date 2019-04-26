package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

	public LandingPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	public @FindBy(xpath = ".//div[@id='loginPanel']/p[2]/a") WebElement registerBtn;
	public @FindBy(xpath = ".//*[@id='loginPanel']/form/div[1]/input") WebElement loginField;
	public @FindBy(xpath = ".//*[@id='loginPanel']/form/div[2]/input") WebElement passwordField;
	public @FindBy(xpath = ".//*[@id='loginPanel']/form/div[3]/input") WebElement loginBtn;

	
	
	
	public LandingPage enterUsername(String username) throws Exception {
		
		sendKeysToWebElement(loginField, username);
		
		return new LandingPage();
	}
	
	public LandingPage enterPassword(String password) throws Exception {
		
		sendKeysToWebElement(passwordField, password);

		return new LandingPage();
	}
	
	
	public LandingPage clickLoginBtn() throws IOException, InterruptedException {
		
		//clickOnElementUsingCustomTimeout(loginBtn, driver, 10);
		waitAndClickElement(loginBtn);

		return new LandingPage();
	}
	
	public LandingPage clickRegisterbtn() throws InterruptedException, IOException {
		
		//clickOnElementUsingCustomTimeout(registerBtn, driver, 15);
		waitAndClickElement(registerBtn);
		return new LandingPage();
	}
	
	public WelcomePage login(String username, String password) throws Exception {
		
		sendKeysToWebElement(loginField, username);
		sendKeysToWebElement(passwordField, password);
		//clickOnElementUsingCustomTimeout(loginBtn, driver, 10);
		waitAndClickElement(loginBtn);

		return new WelcomePage();
	}
	
}

package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import WebDriverManager.WebDriverManager;

public class WelcomePage extends WebDriverManager {

	// welcome page is the page the server send you too after you registering a new account

	public WelcomePage() throws IOException {
		super();
	}

	public static @FindBy(xpath = ".//*[@id='rightPanel']/p") WebElement successfulRegistrationMessageTxt;

	public static @FindBy(xpath = ".//*[@id='rightPanel']/h1") WebElement welcomeLinkUsernameTxt;

	public static @FindBy(xpath = ".//*[@id='leftPanel']/p") WebElement welcomelinkFirstlastnameTxt;

	public void validateSuccessfulRegistrationMessage() {

		String actualText = successfulRegistrationMessageTxt.getText();

		String expectedText = "Your account was created successfully. You are now logged in.";

		
		if (actualText.equals(expectedText)) {
			System.out.println("validateSuccessfulRegistrationMessage : PASSED");
		} else {
			System.out.println("validateSuccessfulRegistrationMessage : FAILED");
		}

	}
	
	public void validate_Welcome_Text_With_First_and_lastName(String firstname, String lastname) {
		
		String firstnameLastname_WelcomeTxt = welcomelinkFirstlastnameTxt.getText();
		
		System.out.println("firstnameLastname_WelcomeTxt : "+firstnameLastname_WelcomeTxt);
	}

	
	
	
}

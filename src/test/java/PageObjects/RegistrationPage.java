package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Util.Lib;

public class RegistrationPage extends BasePage {

	public RegistrationPage() throws IOException {
		super();
	}

	// elements

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[1]/td[2]/input") WebElement firstnameField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[2]/td[2]/input") WebElement lastnameField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[3]/td[2]/input") WebElement addressField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[4]/td[2]/input") WebElement cityField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[5]/td[2]/input") WebElement stateField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[6]/td[2]/input") WebElement zipcodeField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[7]/td[2]/input") WebElement phoneField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[8]/td[2]/input") WebElement ssnField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[10]/td[2]/input") WebElement usernameField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[11]/td[2]/input") WebElement passwordField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[12]/td[2]/input") WebElement confirmField;

	public static @FindBy(xpath = ".//table[@class='form2']/tbody/tr[13]/td[2]/input") WebElement registerBtn;

	// ~~~~~~~~~~~~ reusable action methods~~~~~~~~~

	public OverviewPage fillAndSubmitRegistrationForm() throws Exception {


		Lib.generateRandomUsername();
		sendKeysToWebElement(firstnameField, FIRSTNAME);
		sendKeysToWebElement(lastnameField, LASTNAME);
		sendKeysToWebElement(addressField, ADDRESS);
		sendKeysToWebElement(cityField, CITY);
		sendKeysToWebElement(stateField, STATE);
		sendKeysToWebElement(zipcodeField, ZIPCODE);
		sendKeysToWebElement(phoneField, PHONENUM);
		sendKeysToWebElement(ssnField, SSN);
		sendKeysToWebElement(usernameField, RANDOMUSERNAME);
		sendKeysToWebElement(passwordField, PASSWORD);
		sendKeysToWebElement(confirmField, PASSWORD);

		return new OverviewPage();

	}

	public RegistrationPage enterFirstname(String firstname) throws Exception {

		sendKeysToWebElement(firstnameField, firstname);

		return new RegistrationPage();
	}

	public void enterLastName(String lastname) throws Exception {

		sendKeysToWebElement(lastnameField, lastname);

	}

	public void enterAddress(String address) throws Exception {

		sendKeysToWebElement(addressField, address);

	}

	public void enterCity(String city) throws Exception {

		sendKeysToWebElement(cityField, city);

	}

	public void enterState(String state) throws Exception {

		sendKeysToWebElement(stateField, state);

	}

	public void enterZipCode(String zipCode) throws Exception {

		sendKeysToWebElement(zipcodeField, zipCode);

	}

	public void enterPhoneNum(String phoneNum) throws Exception {

		sendKeysToWebElement(phoneField, phoneNum);

	}

	public void enterSSN(String ssn) throws Exception {

		sendKeysToWebElement(ssnField, ssn);

	}

	public void enterUsername(String username) throws Exception {

		sendKeysToWebElement(usernameField, username);

	}

	public void enterPassword(String password) throws Exception {

		sendKeysToWebElement(passwordField, password);

	}

	public void enterConfirmPassword(String password) throws Exception {

		sendKeysToWebElement(confirmField, password);

	}

	public void clickRegisterBtn() throws InterruptedException, IOException {

		waitAndClickElement(registerBtn);
	}

}











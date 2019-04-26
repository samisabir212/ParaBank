package Steps;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.xml.sax.SAXException;

import Constants.Constants;
import PageObjects.ActivityPage;
import PageObjects.BasePage;
import PageObjects.LandingPage;
import PageObjects.LoggedInPage;
import PageObjects.OverviewPage;
import PageObjects.RegistrationPage;
import PageObjects.TransactionDetailsPage;
import PageObjects.WelcomePage;
import Services.GetAccount;
import Services.GetTransaction;
import Services.Withdraw;
import Util.Lib;
import Util.Logger;
import WebDriverManager.WebDriverManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps extends WebDriverManager {

	public Steps() throws IOException {
		super();
	}


	// ~~~~~~~~~~testing env~~~~~~~~~
	@Then("^launch locally$")
	public void launchLocally() throws InterruptedException, IOException {

		System.out.println("hello ");
		// driver.get(Lib.getEndpointProperties("STAGING_PARABANK"));
		//
		// String title = driver.getTitle();
		// System.out.println("title is " + title);
		// Thread.sleep(7000);
		//
		// driver.close();
		// driver.quit();

	}

	@Then("^launch AWS cloud$")
	public void launch_AWS_cloud() {

		System.out.println("hello");

	}

	@Then("^launch browserStack$")
	public void launch_browserStack() {

	}

	@Then("^launch SauceLabs$")
	public void launch_SauceLabs() {

	}
	
	

	
	

	
	
}

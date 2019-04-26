package Steps;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;

import Constants.Constants;
import PageObjects.ActivityPage;
import PageObjects.BasePage;
import PageObjects.LandingPage;
import PageObjects.LoggedInPage;
import PageObjects.OverviewPage;
import PageObjects.RegistrationPage;
import PageObjects.TransactionDetailsPage;
import PageObjects.WelcomePage;
import Util.Lib;
import Util.Logger;
import WebDriverManager.WebDriverManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class RegistrationSteps extends WebDriverManager{

	public RegistrationSteps() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	//first initialize page classes elements using pagefactory
	static LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
	static RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
	static WelcomePage welcomePage = PageFactory.initElements(driver, WelcomePage.class);
	static LoggedInPage loggedinPage = PageFactory.initElements(driver, LoggedInPage.class);
	static OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
	static ActivityPage activityPage = PageFactory.initElements(driver, ActivityPage.class);
	static TransactionDetailsPage transactionDetailsPage = PageFactory.initElements(driver,
			TransactionDetailsPage.class);

	
	// **********************************************************************\\
		// *******************************REGISTRATION
		// **********************************************************************\\
		@Given("^user fills out the registration form$")
		public void user_fills_out_the_registration_form() throws Exception {

			System.out.println("STARTED FILLING REGISTRATION");
			BasePage.loadUrl("https://parabank.parasoft.com/parabank/index.htm");
			Logger.log("current url :" + BasePage.getCurrentURL(), "Registration", "Successful_Registration");
			
			
			landingPage.clickRegisterbtn();
			

			registrationPage.fillAndSubmitRegistrationForm();
			
		}

		@And("^clicks register$")
		public void clicks_register() throws InterruptedException, IOException {

			registrationPage.clickRegisterBtn();

		}

		@Then("^user should be logged in after submitting the registration form$")
		public void user_should_be_logged_in_after_submitting_the_registration_form() {

			welcomePage.validateSuccessfulRegistrationMessage();
			welcomePage.validate_Welcome_Text_With_First_and_lastName(FIRSTNAME, LASTNAME);
			loggedinPage.validateAllAccountservicesLinks();

		}

		// validate account services links
		@Then("^user logs out and logs back in$")
		public void user_logs_out_and_logs_back_in() throws Exception {

			loggedinPage.clickLogout();
			landingPage.enterUsername(RANDOMUSERNAME);
			landingPage.enterPassword(PASSWORD);
			System.out.println("getting ready to click landingPage.clickLoginBtn(); line 139");
			landingPage.clickLoginBtn();

		}

		// logged in page should appear and validate account overview details
		@Then("^the logged in overviw page should appear and validate account over view details \"([^\"]*)\"$")
		public void the_logged_in_overview_page_should_appear_and_validate_account_over_view_details(String testname)
				throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {

			/**
			 * 1. validate over view details elements
			 * 
			 * 
			 */
			String expectedBalance = "$515.50";
			overviewPage.validateBalance_BalanceTotal_AvailbleAmount(expectedBalance);
			loggedinPage.clickLogout();

			Lib.excelwrite(Constants.runResultsFileName,
					new Object[] { Lib.getcurrentdate(), Environment, "Parabank", Constants.ACCOUNTSTATUS, "REGISTRATION",
							testname, Status, "n/a", "n/a", "n/a", "RC-8989", "1.0", "COMMENT HERE" });

		}

	
	
}

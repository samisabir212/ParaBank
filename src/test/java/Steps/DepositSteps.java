package Steps;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

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
import Services.GetTransaction;
import Util.Lib;
import Util.Logger;
import WebDriverManager.WebDriverManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class DepositSteps extends WebDriverManager {

	public DepositSteps() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	static LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
	static RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
	static WelcomePage welcomePage = PageFactory.initElements(driver, WelcomePage.class);
	static LoggedInPage loggedinPage = PageFactory.initElements(driver, LoggedInPage.class);
	static OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
	static ActivityPage activityPage = PageFactory.initElements(driver, ActivityPage.class);
	static TransactionDetailsPage transactionDetailsPage = PageFactory.initElements(driver,
			TransactionDetailsPage.class);
	
	// **********************************************************************
		// DepositSteps
		// **********************************************************************

		@Given("new user is registered")
		public void createNewAccount_For_Successful_Deposit_Scenario() throws Exception {
			
			
			BasePage.loadUrl("https://parabank.parasoft.com/parabank/index.htm");
	
			landingPage.clickRegisterbtn();
			registrationPage.fillAndSubmitRegistrationForm();
			registrationPage.clickRegisterBtn();
			loggedinPage.clickLogout();
			landingPage.login(RANDOMUSERNAME, PASSWORD);
			
		}
		
		@And("the account Balance should be (.*) dollars")
		public void validateAccountBalance_For_Successful_Deposit_Scenario(String expectedAccountBalance) throws IOException {
			
			String expectedBalance = "$"+expectedAccountBalance;
			overviewPage.validateBalance_BalanceTotal_AvailbleAmount(expectedBalance);
			
		}
		
		@When("the customer Deposits (.*) dollars")
		public void validateDeposit_For_Successful_Deposit_Scenario(String expectedDepositAmount) {
			
			
		}
		
		@Then("the account Balance must be (.*) dollars")
		public void validateAccountBalanceAfterDeposit_For_Successful_Deposit_Scenario(String expectedDepositAmount) {
			
			
		}
		
		@Then("a new transaction should be Recorded \"([^\"]*)\"$")
		public void validateNewTransaction_For_Successful_Deposit_Scenario(String testname) throws IOException, InterruptedException, SAXException, ParserConfigurationException, EncryptedDocumentException, InvalidFormatException {
			
					overviewPage.clickAccountIDLink();
					activityPage.validateWithdrawlDebitAmount(expectedDespositAmount);
	
					activityPage.clickFirstTransactionLink();
	
					String actualDepositAmount = transactionDetailsPage.getAmountTxtValue();
					Assert.assertEquals(actualDepositAmount, expectedDespositAmount);
	
					transactionID = transactionDetailsPage.getTransactionID();
					Logger.log("transactionID : " + transactionID, "Withdraw", "Successful_Withdraw");
	
					getTransaction_ResponseMap = GetTransaction.getTransaction_Request(transactionID);
					Assert.assertEquals(getTransaction_ResponseMap.get("amount"), expectedDespositAmount.replace("$", ""));
					
					
					if (getTransaction_ResponseMap.get("amount").equalsIgnoreCase(expectedDespositAmount.replace("$", ""))) {
						Status = "Passed";
					} else {
						Status = "Failed";
						Comment = "failed to validate transaction";
					}
	
	
					// write to excel write to log
					Lib.excelwrite(Constants.runResultsFileName,
							new Object[] { Lib.getcurrentdate(), Environment, "Parabank", Constants.ACCOUNTSTATUS, "DEPOSIT",
									testname, Status, "n/a", "n/a", "n/a", "RC-8989", "1.0",  });
	
					driver.close();
					// driver.quit();
			
			
		}

}

package Steps;

import java.io.IOException;

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

public class WithdrawSteps extends WebDriverManager {
	
	public WithdrawSteps() throws IOException {
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
		// WITHDRAWSTEPS
		// **********************************************************************

		

		@Given("a customer has an account")
		public void createNewAccount() throws Exception {

			
			BasePage.loadUrl("https://parabank.parasoft.com/parabank/index.htm");
			
			Logger.log("current url :" + BasePage.getCurrentURL(), "Withdraw", "Successful_Withdraw");

			landingPage.clickRegisterbtn();
			registrationPage.fillAndSubmitRegistrationForm();
			registrationPage.clickRegisterBtn();
			
			Logger.log("successfully created account", "Withdraw", "Successful_Withdraw");
			// click log out
			loggedinPage.clickLogout();
			
			Logger.log("successfully logged out", "Withdraw", "Successful_Withdraw");

			// log back in
			System.out.println(RANDOMUSERNAME);
			System.out.println(PASSWORD);

			landingPage.login(RANDOMUSERNAME, PASSWORD);
			Logger.log("successfully logged in", "Withdraw", "Successful_Withdraw");
			// Thread.sleep(7000);

		}

		@And("^the account balance is (.*) dollars$")
		public void getAccountBalance(String expectedBalance) throws ClientProtocolException, IOException, SAXException,
				ParserConfigurationException, InterruptedException {

			System.out.println("Current url : " + driver.getCurrentUrl());
			Logger.log("current url :" + driver.getCurrentUrl(), "Withdraw", "Successful_Withdraw");

			// validate UI balance , available amount, and total
			actualUIbalance = overviewPage
					.validateBalance_BalanceTotal_AvailbleAmount("$"+expectedBalance);
			Logger.log("actualUIbalance :" + actualUIbalance, "Withdraw", "Successful_Withdraw");

			// send request to get account
			accountID = overviewPage.getAccountID();
			getAccount_ResponseMap = GetAccount.getAccount_Request(accountID);
			Logger.log("successfully got the account details using getAccount Webservice", "Withdraw",
					"Successful_Withdraw");

			// Validate id value vs get account response id using TestNG assertion
			Assert.assertEquals(accountID, getAccount_ResponseMap.get("id"));
			Logger.log("Validate ID Passed", "Withdraw", "Successful_Withdraw");

			// validate if balance on ui matches back end result
			actualUIbalance = actualUIbalance.replace("$", "");
			Assert.assertEquals(actualUIbalance, getAccount_ResponseMap.get("balance"));
			Logger.log("Validate UI Balance PASSED", "Withdraw", "Successful_Withdraw");

		}

		@When("the customer withdraws (.*) dollars")
		public void withdraw(String withdrawAmount)
				throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {

			// send request to withdraw
			expectedWithdrawAmount = Withdraw.withdraw_Request_For_SuccessfulWithdrawScenario(accountID, withdrawAmount);
			Logger.log("Successfully withdrew an amount of : " + withdrawAmount + " withdraw webservice", "Withdraw",
					"Successful_Withdraw");

		}

		@Then("the account balance should be (.*) dollars")
		public void verifyBalance(String balance)
				throws InterruptedException, IOException, SAXException, ParserConfigurationException {



			getAccount_ResponseMap = GetAccount.getAccount_Request(accountID);
			expectedBalance = balance;
			
			try {
				Assert.assertEquals(expectedBalance, getAccount_ResponseMap.get("balance"));
				Logger.log("actualBalance vs expectedBalance Assertion PASSED", "Withdraw", "Successful_Withdraw");
			}catch(java.lang.AssertionError e) {
				
				System.out.println("Exception : "+ e.getMessage());
				Logger.log("actualBalance vs expectedBalance Assertion FAILED : "+e.getMessage(), "Withdraw", "Successful_Withdraw");

			}
			

		}

		@And("a new transaction should be recorded \"([^\"]*)\"$")
		public void verifyTransactionRecord(String testname) throws SAXException, IOException, ParserConfigurationException,
				EncryptedDocumentException, InvalidFormatException, InterruptedException {

			// click account id link
			overviewPage.clickAccountIDLink();

//			validate new transaction
			// validate withdrawl debit amount for transaction
			activityPage.validateWithdrawlDebitAmount(expectedWithdrawAmount);
			Logger.log("validateWithdrawlDebitAmount PASSED", "Withdraw", "Successful_Withdraw");

			activityPage.clickFirstTransactionLink();

//			validate transaction amount on UI
			String actualWithdrawAmount = transactionDetailsPage.getAmountTxtValue();
			
			try {
				
				Assert.assertEquals(actualWithdrawAmount, expectedWithdrawAmount);
				Logger.log("actualWithDrawAmount vs expectedWithdrawAmount validation PASSED", "Withdraw",
						"Successful_Withdraw");
				
			}catch(java.lang.AssertionError e) {
				
				System.out.println("Exception : "+ e.getMessage());
				Logger.log("actualWithDrawAmount vs expectedWithdrawAmount validation FAILED : "+e.getMessage(), "Withdraw",
						"Successful_Withdraw");
			}
			

//			get account transaction ID UI
			transactionID = transactionDetailsPage.getTransactionID();
			Logger.log("transactionID : " + transactionID, "Withdraw", "Successful_Withdraw");

			// validate transaction by its ID in backend using webservice
			getTransaction_ResponseMap = GetTransaction.getTransaction_Request(transactionID);
			
			try {
				
				Assert.assertEquals(getTransaction_ResponseMap.get("amount"), expectedWithdrawAmount.replace("$", ""));
				Logger.log("actualAmount vs expectedWithdrawAmount validation PASSED", "Withdraw", "Successful_Withdraw");
		
				Status = "Passed";
			}catch(java.lang.AssertionError e) {
				
				Status = "Failed";
				Logger.log("actualAmount vs expectedWithdrawAmount validation Failed : "+e.getMessage(), "Withdraw", "Successful_Withdraw");

				
			}
			
			
			System.out.println(testname + " : " + Status);

			// write to excel write to log
			Lib.excelwrite(Constants.runResultsFileName,
					new Object[] { Lib.getcurrentdate(), Environment, "Parabank", Constants.ACCOUNTSTATUS, "WITHDRAW",
							testname, Status, "n/a", "n/a", "n/a", "RC-8989", "1.0", "COMMENT HERE" });

			driver.close();
			// driver.quit();

		}

}

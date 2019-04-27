package Steps;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import PageObjects.BasePage;
import PageObjects.LandingPage;
import PageObjects.LoggedInPage;
import PageObjects.RegistrationPage;
import Services.GetAccounts;
import Services.GetCustomer;
import Services.Login;
import Util.Lib;
import WebDriverManager.WebDriverManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class WebServiceSmokeTest_Steps extends WebDriverManager {

	public WebServiceSmokeTest_Steps() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	static LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
	static RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
	static LoggedInPage loggedinPage = PageFactory.initElements(driver, LoggedInPage.class);

	@Given("^We create a new account$")
	public void we_create_a_new_account() throws Throwable {
	    
		
		BasePage.loadUrl("https://parabank.parasoft.com/parabank/index.htm");
		landingPage.clickRegisterbtn();
		registrationPage.fillAndSubmitRegistrationForm();
		registrationPage.clickRegisterBtn();
		loggedinPage.clickLogout();
//		landingPage.login(RANDOMUSERNAME, PASSWORD);
		
	}

	@Then("^call Login service$")
	public void call_Login_service() throws Throwable {
	    
		login_ResponseMap = Login.login_Request(RANDOMUSERNAME, PASSWORD);
		
		Assert.assertEquals(200, responseStatusCode);
		Assert.assertEquals(testData.get("firstname"), login_ResponseMap.get("firstname"));
		Assert.assertEquals(testData.get("lastname"), login_ResponseMap.get("lastname"));
		Assert.assertEquals(testData.get("address"), login_ResponseMap.get("address"));
		Assert.assertEquals(testData.get("city"), login_ResponseMap.get("city"));
		Assert.assertEquals(testData.get("state"), login_ResponseMap.get("state"));
		Assert.assertEquals(testData.get("zipcode"), login_ResponseMap.get("zipcode"));
		Assert.assertEquals(testData.get("phonenumber"), login_ResponseMap.get("phonenumber"));
		Assert.assertEquals(testData.get("ssn"), login_ResponseMap.get("ssn"));

		if(responseStatusCode==200&&testData.get("firstname").equalsIgnoreCase(login_ResponseMap.get("firstname"))
				&&testData.get("lastname").equalsIgnoreCase("lastname")
				&&testData.get("address").equalsIgnoreCase("address")
				&&testData.get("city").equalsIgnoreCase("city")
				&&testData.get("state").equalsIgnoreCase("state")
				&&testData.get("zipcode").equalsIgnoreCase("zipcode")
				&&testData.get("phonenumber").equalsIgnoreCase("ssn")) {
			
			Status = "Passed";

		
		}else {
			Status = "Failed";
		}
		
		System.out.println("call_Login_service :"+Status);

		
		//write to excel
		//write to log (endpoint, headers, request, response status code, response body)
	}
	
	@Then("^GetCustomer service to validate Customer info$")
	public void getcustomer_service_to_validate_Customer_info() throws Throwable {

		getCustomer_ResponseMap = GetCustomer.getCustomerInfo_Request(login_ResponseMap.get("id"));
		
		Assert.assertEquals(200, responseStatusCode);
		Assert.assertEquals(login_ResponseMap.get("id"), getCustomer_ResponseMap.get("id"));
		Assert.assertEquals(testData.get("firstname"), getCustomer_ResponseMap.get("firstname"));
		Assert.assertEquals(testData.get("lastname"), getCustomer_ResponseMap.get("lastname"));
		Assert.assertEquals(testData.get("address"), getCustomer_ResponseMap.get("address"));
		Assert.assertEquals(testData.get("city"), getCustomer_ResponseMap.get("city"));
		Assert.assertEquals(testData.get("state"), getCustomer_ResponseMap.get("state"));
		Assert.assertEquals(testData.get("zipcode"), getCustomer_ResponseMap.get("zipcode"));
		Assert.assertEquals(testData.get("phonenumber"), getCustomer_ResponseMap.get("phonenumber"));
		Assert.assertEquals(testData.get("ssn"), getCustomer_ResponseMap.get("ssn"));

		if(responseStatusCode==200
				&&login_ResponseMap.get("id").equalsIgnoreCase(getCustomer_ResponseMap.get("id"))
				&&testData.get("firstname").equalsIgnoreCase(login_ResponseMap.get("firstname"))
				&&testData.get("lastname").equalsIgnoreCase("lastname")
				&&testData.get("address").equalsIgnoreCase("address")
				&&testData.get("city").equalsIgnoreCase("city")
				&&testData.get("state").equalsIgnoreCase("state")
				&&testData.get("zipcode").equalsIgnoreCase("zipcode")
				&&testData.get("phonenumber").equalsIgnoreCase("ssn")) {
			
			Status = "Passed";

		}else {
			Status = "Failed";

		}
		
		System.out.println("getcustomer_service_to_validate_Customer_info");
		
		//write to excel
		//write to log (endpoint, headers, request, response status code, response body)
		
	}

	@Then("^GetAccounts service to get account info$")
	public void getaccounts_service_to_get_account_info() throws Throwable {

		
		getAccounts_ResponseMap = GetAccounts.getAccounts_Request(getCustomer_ResponseMap.get("id"));
		
		try {
			
			Assert.assertEquals(200, responseStatusCode);
			Assert.assertEquals(getCustomer_ResponseMap.get("id"),getAccounts_ResponseMap.get("id"));
			Assert.assertEquals("CHECKING", getAccounts_ResponseMap.get("type"));
			Assert.assertEquals("515.50", getAccounts_ResponseMap.get("balance"));	
			
			Status = "Passed";
			System.out.println(Lib.getMethodName()+" : "+Status);
		}catch(Exception e) {
			
			Status = "Failed";
			System.out.println(Lib.getMethodName()+" : "+Status);
			System.out.println("Exception : "+e.getMessage());

		}
		

		
		if(responseStatusCode==200) {
			
			Status = "Passed";

		}else {
			Status = "Failed";

		}
		
		System.out.println("getcustomer_service_to_validate_Customer_info");
		
		//write to excel
		//write to log (endpoint, headers, request, response status code, response body)
		
		
		
	}

	@Then("^GetAccount service to get validate the checkings account$")
	public void getaccount_service_to_get_validate_account() throws Throwable {

	}

	@Then("^UpdateAccount service to update account info$")
	public void updateaccount_service_to_update_account_info() throws Throwable {

	}

	@Then("^Deposit (\\d+) dollars to the first account$")
	public void deposit_dollars_to_the_first_account(int arg1) throws Throwable {

	}

	@Then("^GetAccount to validate the deposit amount$")
	public void getaccount_to_validate_the_deposit_amount() throws Throwable {

	}

	@Then("^GetTransactions to get deposit transaction ID$")
	public void gettransactions_to_get_deposit_transaction_ID() throws Throwable {

	}

	@Then("^GetTransaction to validate the deposit transaction and deposit amount$")
	public void gettransaction_to_validate_the_deposit_transaction_and_deposit_amount() throws Throwable {

	}

	@Then("^CreateAccount to create a new Checkings Account$")
	public void createaccount_to_create_a_new_Checkings_Account() throws Throwable {

	}

	@Then("^Call GetAccounts to validate the new Checkings Account was created$")
	public void call_GetAccounts_to_validate_the_new_Checkings_Account_was_created() throws Throwable {

	}

	@Then("^Call GetAccount using the new Checkings Account ID to validate the account$")
	public void call_GetAccount_using_the_new_Checkings_Account_ID_to_validate_the_account() throws Throwable {

	}

	@Then("^Withdraw (\\d+) from the new Checkings account$")
	public void withdraw_from_the_new_Checkings_account(int arg1) throws Throwable {

	}

	@Then("^GetTransactions to look for the withdraw transaction$")
	public void gettransactions_to_look_for_the_withdraw_transaction() throws Throwable {

	}

	@Then("^Call GetTransaction using the withdraw transaction ID to validate the transaction$")
	public void call_GetTransaction_using_the_withdraw_transaction_ID_to_validate_the_transaction() throws Throwable {

	}

	@Then("^Transfer (\\d+) from the old Checkings account to the new Checkings account$")
	public void transfer_from_the_old_Checkings_account_to_the_new_Checkings_account(int arg1) throws Throwable {

	}

	@Then("^GetTransactions to look for the transfer transaction$")
	public void gettransactions_to_look_for_the_transfer_transaction() throws Throwable {

	}

	@Then("^call GetTransaction using the transfer transaction id to calidate the transaction$")
	public void call_GetTransaction_using_the_transfer_transaction_id_to_calidate_the_transaction() throws Throwable {

	}

}

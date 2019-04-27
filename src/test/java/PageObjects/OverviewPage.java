package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import Util.Logger;


public class OverviewPage extends BasePage {

	

	public OverviewPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static @FindBy(xpath = ".//*[@id='accountTable']/thead/tr/th[1]") WebElement accntColumnTxt;
	public static @FindBy(xpath = ".//*[@id='accountTable']/thead/tr/th[2]") WebElement balanceColumnTxt;
	public static @FindBy(xpath = ".//*[@id='accountTable']/thead/tr/th[3]") WebElement availbleAmountColumnTxt;
	public static @FindBy(xpath = ".//*[@id='accountTable']/tbody/tr[1]/td[1]/a") WebElement accountIdLink;
	public static @FindBy(xpath = ".//*[@id='accountTable']/tbody/tr[1]/td[2]") WebElement balanceValueTxt;
	public static @FindBy(xpath = ".//*[@id='accountTable']/tbody/tr[1]/td[3]") WebElement availbleAmountTxt;
	public static @FindBy(xpath = ".//*[@id='accountTable']/tbody/tr[2]/td[2]/b") WebElement totalBalanceTxt;

	public String validateBalance_BalanceTotal_AvailbleAmount(String expectedBalance) throws IOException {

		
		try {
			actualUIbalance = getBalanceValueTxt();
			Assert.assertEquals(expectedBalance, actualUIbalance);	
			Logger.log("actualUIbalance :" + actualUIbalance +"vs expected expected Balance : "+ expectedBalance +"Passed", "Withdraw", "Successful_Withdraw");

			String actualAvailableBalance = getAvaibleAmount();	
			Assert.assertEquals(expectedBalance, actualAvailableBalance);
			Logger.log("actualAvailableBalance :" + actualAvailableBalance +"vs expected expected Balance : "+ expectedBalance +"Passed", "Withdraw", "Successful_Withdraw");

			String actualTotalBalance = getTotalBalance();
			Assert.assertEquals(expectedBalance, actualTotalBalance);
			Logger.log("actualTotalBalance :" + actualTotalBalance +"vs expected expected Balance : "+ expectedBalance +"Passed", "Withdraw", "Successful_Withdraw");
			
		}catch(java.lang.AssertionError e) {
			
			System.out.println("validateBalance_BalanceTotal_AvailbleAmount step Failed : "+ e.getMessage());
			Logger.log("validateBalance_BalanceTotal_AvailbleAmount step Failed : "+ e.getMessage(), "Withdraw", "Successful_Withdraw");
			
			COMMENT = e.getMessage();

		}
		
		return actualUIbalance;
	}
	
	
	
	public String getBalanceValueTxt() {
		
		return balanceValueTxt.getText();
	}
	
	public String getAccountID() {
		return accountIdLink.getText();
	}

	public void clickAccountIDLink() throws InterruptedException, IOException {
		
		//clickOnElementUsingCustomTimeout(accountIdLink, driver, 4);
		waitAndClickElement(accountIdLink);
	}
	
	public String getAvaibleAmount() {
		return availbleAmountTxt.getText();
	}
	
	public String getTotalBalance() {
		return totalBalanceTxt.getText();
	}
	
	
}

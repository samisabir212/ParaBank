package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;


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

	public String validateBalance_BalanceTotal_AvailbleAmount(String expectedBalance) {

		String actualBalance = getBalanceValueTxt();
		System.out.println("Actual Balance : "+actualBalance);
		System.out.println("expected Balance : "+expectedBalance);
		//Assert.assertEquals(expectedBalance, actualBalance, "Balance Validation failed");
		Assert.assertEquals(actualBalance, expectedBalance);
		
		
		String actualAvailableBalance = getAvaibleAmount();
		System.out.println("actualAvailableBalance : "+ actualAvailableBalance);
		Assert.assertEquals(expectedBalance, actualAvailableBalance, "expected balance vs actualAvailableBalance PASSED");

		String actualTotalBalance = getTotalBalance();
		System.out.println("actualTotalBalance : "+ actualTotalBalance);
		Assert.assertEquals(expectedBalance, actualTotalBalance, "expected balance vs actualTotalBalance PASSED");

		return actualBalance;
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

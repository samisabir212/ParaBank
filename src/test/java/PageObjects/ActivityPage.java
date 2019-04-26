package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Util.Logger;

public class ActivityPage extends BasePage {

	public ActivityPage() throws IOException {
		super();
	}
	
	public static @FindBy(xpath = ".//*[@id='transactionTable']/tbody/tr[1]/td[2]/a") WebElement balanceTxtValue;
	public static @FindBy(xpath = ".//*[@id='transactionTable']/tbody/tr[1]/td[2]/a") WebElement firstTransactionLink;
	public static @FindBy(xpath = ".//*[@id='transactionTable']/tbody/tr[1]/td[3]") WebElement debitAmountTxtValue;

	
	
	public void clickFirstTransactionLink() throws InterruptedException, IOException {
		
		
		//clickOnElementUsingCustomTimeout(firstTransactionLink, driver, 10);
		waitAndClickElement(firstTransactionLink);
	}
	
	public String getBalanceTxtValue() {
		return balanceTxtValue.getText();
		
	}
	
	public void validateWithdrawlDebitAmount(String expectedWithdrawlAmount) throws IOException {
		
		System.out.println("expectedWithdrawlAmount :"+ expectedWithdrawlAmount);
		System.out.println("actualDebitAmount : "+ debitAmountTxtValue.getText());
		Assert.assertEquals(debitAmountTxtValue.getText(), expectedWithdrawlAmount);

	}
	

}

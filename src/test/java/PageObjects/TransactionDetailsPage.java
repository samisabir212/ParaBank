package PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransactionDetailsPage extends BasePage {

	public TransactionDetailsPage() throws IOException {
		super();
	}

	
	public static @FindBy(xpath = ".//*[@id='rightPanel']/table/tbody/tr[1]/td[2]") WebElement transactionIDTxtValue;
	public static @FindBy(xpath = ".//*[@id='rightPanel']/table/tbody/tr[5]/td[2]") WebElement amountTxtValue;

	public String getTransactionID() {
		return transactionIDTxtValue.getText();
	}
	
	public String getAmountTxtValue() {
		return amountTxtValue.getText();
	}
	
	
}

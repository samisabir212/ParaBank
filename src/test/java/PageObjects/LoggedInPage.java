package PageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInPage extends BasePage {

	public LoggedInPage() throws IOException {
		super();
	}

	public static @FindBy(xpath = ".//*[@id='leftPanel']/p") WebElement welcomeFirstNameAndLastNameTxt;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/h2") WebElement accountServicesTxt;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/ul/li[1]/a") WebElement openNewAccountLink;
	public static @FindBy(xpath = ".//div[@id='bodyPanel']/div[1]/ul/li[2]/a") WebElement accountOverviewLink;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/ul/li[3]/a") WebElement transferFundsLink;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/ul/li[4]/a") WebElement billPayLink;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/ul/li[5]/a") WebElement findTransactionsLink;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/ul/li[6]/a") WebElement updateContactInfoLink;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/ul/li[7]/a") WebElement requestLoanLink;
	public static @FindBy(xpath = ".//*[@id='leftPanel']/ul/li[8]/a") WebElement logoutLink;

	private static List<WebElement> accountServicesLinks_LIST = new ArrayList<WebElement>();
	
	
	public void validateAllAccountservicesLinks() {

		accountServicesLinks_LIST.add(openNewAccountLink);
		accountServicesLinks_LIST.add(accountOverviewLink);
		accountServicesLinks_LIST.add(transferFundsLink);
		accountServicesLinks_LIST.add(transferFundsLink);
		accountServicesLinks_LIST.add(billPayLink);
		accountServicesLinks_LIST.add(findTransactionsLink);
		accountServicesLinks_LIST.add(updateContactInfoLink);
		accountServicesLinks_LIST.add(requestLoanLink);
		accountServicesLinks_LIST.add(logoutLink);

		for(int i = 0; i<accountServicesLinks_LIST.size();i++) { 
			
			WebElement element = accountServicesLinks_LIST.get(i);
			String elementTxt = element.getText();
			System.out.println("AccountService Link txt : "+ elementTxt);
			
			
		}
		
		
		
		
	}

	
	public void clickAccountOverViewLink() throws InterruptedException, IOException {
		
		//clickOnElementUsingCustomTimeout(accountOverviewLink, driver, 15);
		waitAndClickElement(accountOverviewLink);
		System.out.println("clicked on accountOverViewLink");
		
	}
	
	public void clickLogout() throws InterruptedException, IOException {
		
		//clickOnElementUsingCustomTimeout(logoutLink, driver, 7);
		waitAndClickElement(logoutLink);
	}
	
	
}

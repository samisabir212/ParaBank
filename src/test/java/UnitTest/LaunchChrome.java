package UnitTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Util.Lib;

public class LaunchChrome {
	
	public static WebDriver driver;

	
	@Test(enabled = true)
	public void launchChrome() throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver",Lib.getBrowserDriverPathProperties("macChrome_72"));
		driver = new ChromeDriver();
		
		driver.get("https://www.google.com");
		
		String title = driver.getTitle();
		System.out.println("title is "+ title);
		Thread.sleep(7000);
		
		driver.close();
		driver.quit();

		
	}

}

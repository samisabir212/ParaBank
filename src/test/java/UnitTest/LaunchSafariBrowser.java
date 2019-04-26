package UnitTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class LaunchSafariBrowser {
	
	@Test(enabled = true)
	public void launchSafari() {
		
		WebDriver driver = new SafariDriver();
		
	}

}

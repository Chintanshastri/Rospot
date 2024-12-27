package base;

import org.openqa.selenium.WebDriver;

public class printurl {
	WebDriver driver;
	public void url()
	{
		String currentURL = driver.getCurrentUrl();
	  	System.out.println("Current URL: " + currentURL);
	}
}

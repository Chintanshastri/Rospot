package testcase;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class refreshicons {

	WebDriver driver;
	public static Properties locators = new Properties();
	public refreshicons(WebDriver driver) {
		this.driver = driver;
	}

	
	public void refresh() throws InterruptedException {
		// Fetch all refresh icons
		List<WebElement> refreshIcons = driver.findElements(By.className(locators.getProperty("refresh_icons")));
		// Print the count of refresh icons found
		System.out.println("Total Refresh Icons: " + refreshIcons.size());
		// Perform actions on each refresh icon if needed
		for (WebElement refreshIcon : refreshIcons) {
			refreshIcon.click(); // Clicking on each refresh icon
			Thread.sleep(2000);
		}
		JavascriptExecutor scroll = (JavascriptExecutor) driver;//
		scroll.executeScript("window.scrollBy(0,-200)", ""); // Scrolling Up Code
		Thread.sleep(1000);
		driver.findElement(By.xpath(locators.getProperty("calendar_nextbutton"))).click();
		Thread.sleep(1000);
	}
}

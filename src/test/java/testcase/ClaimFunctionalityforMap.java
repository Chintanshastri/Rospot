package testcase;

import base.Browser;
import base.CalendarScreen;
import base.ManualLogindata;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ClaimFunctionalityforMap extends Browser {

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private final ManualLogindata login = new ManualLogindata();
	private final CalendarScreen selectdateforbooking = new CalendarScreen();
	@Test(priority = 0)
	public void manuallogin() throws InterruptedException {
		login.reusableMethod();
	}

	@Test(priority = 1)
	public void zoneoption() {

		System.out.println("---------Available zone-------------");
		// Locate the dropdown element
		WebElement dropdownElement = driver.findElement(By.id("car_park_calendar")); // Update with the actual ID or
																						// locator
		// Initialize the Select class
		Select zonedropdown = new Select(dropdownElement);
		// Get all options in the dropdown
		List<WebElement> options = zonedropdown.getOptions();
		// Print each option's text
		System.out.println("Zone Dropdown Options:");
		for (WebElement option : options) {
			System.out.println(option.getText());
		}
	}

	@Test(priority = 2)
	public void dashboard() throws InterruptedException {

		JavascriptExecutor scroll = (JavascriptExecutor) driver; // Scrolling Down Code
		scroll.executeScript("window.scrollBy(0,200)", "");
		/*
		 * System.out.println("-------Fetch all refresh icons-------------"); // Fetch
		 * all refresh icons List<WebElement> refreshIcons =
		 * driver.findElements(By.className(locators.getProperty("refresh_icons"))); //
		 * Print the count of refresh icons found
		 * System.out.println("Total Refresh Icons: " + refreshIcons.size()); // Perform
		 * actions on each refresh icon if needed for (WebElement refreshIcon :
		 * refreshIcons) { refreshIcon.click(); // Clicking on each refresh icon
		 * Thread.sleep(4000); }
		 */

		System.out.println("----------Spot Counts----------------");
		// Print total available days
		List<WebElement> availablespot = driver.findElements(By.xpath(locators.getProperty("available_spot")));
		int availablecount = availablespot.size();
		System.out.println("Available Days in calendar = " + availablecount); // Print total unavailable space

		List<WebElement> unavailablespot = driver.findElements(By.xpath(locators.getProperty("unavailable_spot")));
		int unavailablecount = unavailablespot.size();
		System.out.println("UnAvailable Days in calendar = " + unavailablecount);
	}

	@Test(priority = 3)
	public void selectdate() throws InterruptedException {
		selectdateforbooking.selecttodaydate();

	}

	@Test(priority = 4)

	public void clickgotomap() {
		try {
			Thread.sleep(5000);
			System.out.println("----------Click on the Goto Map button----------------");
			WebElement clickmap = driver.findElement(By.xpath(locators.getProperty("click_gotomap")));
			clickmap.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	public void spotdetails() throws InterruptedException {
		System.out.println("----------Spot Details----------------");
		List<WebElement> totalspot = driver.findElements(By.xpath("//div[contains(@class, 'grid-item')]"));
		int spotcount = totalspot.size();
		System.out.println("Total Spot Counts= " + spotcount);
		System.out.println("----------Active Spot----------------");
		List<WebElement> activespot = driver
				.findElements(By.xpath("//div[contains(@class, 'grid-item') and contains(@class, 'SpotID_blank')]"));
		for (WebElement active : activespot) {
			String actualName = active.getText(); // Get visible text
			String spotId = active.getAttribute("id"); // Get ID attribute
			System.out.println("Spot ID: " + spotId + ", Spot Text: " + actualName);
		}
		Thread.sleep(5000);
		System.out.println("----------Inactive Spot----------------");
		List<WebElement> inactivespot = driver.findElements(
				By.xpath("//div[contains(@class, 'grid-item') and not(contains(@class, 'SpotID_blank'))]"));
		for (WebElement inactive : inactivespot) {
			String actualName = inactive.getText(); // Get visible text
			String spotId = inactive.getAttribute("id"); // Get ID attribute
			System.out.println("Spot ID: " + spotId + ", Spot Text: " + actualName);
		}
	}

	@Test(priority = 6)
	public void selectspot() throws InterruptedException {
		System.out.println("----------Click on the availble spot----------------");
		WebElement spotselect = driver
				.findElement(By.xpath("//div[contains(@class, 'grid-item') and contains(@class, 'SpotID_blank')]"));
		String actualNamespot = spotselect.getText();
		String expectedName = "XZ-1";
		Thread.sleep(3000);
		// Compare the names
		if (actualNamespot.equals(expectedName)) {
			spotselect.click();
		} else {
			System.out.println("Spot name does not match. Expected: " + expectedName + ", Found: " + actualNamespot);
		}
	}

	@Test(priority = 7)
	public void bookspacebutton() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id='claim_SpotID']")).click();
		System.out.println("----------Booked Spot----------------");
		List<WebElement> bookedspot = driver
				.findElements(By.xpath("//div[contains(@class, 'grid-item') and contains(@class, 'SpotID_assigned')]"));
		for (WebElement booked : bookedspot) {
			String actualName = booked.getText(); // Get visible text
			String spotId = booked.getAttribute("id"); // Get ID attribute
			System.out.println("Spot ID: " + spotId + ", Spot Text: " + actualName);
		}
	}

	@Test(priority = 8)
	public void cancelbookingbutton() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println("----------Booking Cancel----------------");
		// Find all booked spots
		List<WebElement> bookedSpots = driver
				.findElements(By.xpath("//div[contains(@class, 'grid-item') and contains(@class, 'SpotID_assigned')]"));
		// Check if there are any booked spots
		if (bookedSpots.size() > 0) {
			bookedSpots.get(0).click();
		} else {
			System.out.println("No booked spots found.");
		}
		Thread.sleep(7000);
		driver.findElement(By.xpath("//button[@id='release_SpotID']")).click();
	}
	
}

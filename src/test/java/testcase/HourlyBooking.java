package testcase;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.CalendarScreen;
import base.ManualLogindata;

public class HourlyBooking extends ManualLogindata {
	private final ManualLogindata login = new ManualLogindata();
	private final ClaimFunctionalityforMap dashboarddata = new ClaimFunctionalityforMap();
	private final CalendarScreen selectdateforbooking = new CalendarScreen();
	private static final Logger logger = LogManager.getLogger(HourlyBooking.class);
	private final ClaimFunctionalityforMap gotomap = new ClaimFunctionalityforMap();
	private final ClaimFunctionalityforMap bookspot = new ClaimFunctionalityforMap();
	private final ClaimFunctionalityforMap selectspot = new ClaimFunctionalityforMap();

	@Test(priority = 0)
	public void openbrowse() throws InterruptedException {
		login.reusableMethod();
		logger.info("Navigating to dashboard.....");
		dashboarddata.dashboard();
		logger.info("Navigating to calender screen for selecting date.....");
		selectdateforbooking.selecttodaydate();
	}

	@Test(priority = 1)
	public void hourlybooking() throws InterruptedException {
		logger.info("Click goto map button.....");
		gotomap.clickgotomap();
		logger.info("book spot.....");
		bookspot.spotdetails();
		logger.info("Select spot for booking.....");
		selectspot.selectspot();
		Thread.sleep(3000);
		logger.info("Click book button.....");
		driver.findElement(By.id("SpotID_blank")).click();

	}

	@Test(priority = 2)
	public void selecttime() throws InterruptedException {
		Thread.sleep(3000);
		// Locate all time slots
		WebElement timeSlot = driver.findElement(By.id("spotBookingPeriodSlot"));
		if (timeSlot.isDisplayed() && timeSlot.isEnabled()) {
			List<WebElement> timeSlots = driver.findElements(By.xpath("//select[@id='spotBookingPeriodSlot']"));
			// Print available time slots
			System.out.println("Available Time Slots:--------------");
			for (WebElement slot : timeSlots) {
				String time = slot.getText();
				if (!time.equalsIgnoreCase("Unavailable")) { // Skip unavailable slots
					System.out.println(time);
					timeSlot.click();
				}
			}
		} else {
			System.out.println("----------Booking time slots not available----------------");
		}
		WebElement bookbutton = driver.findElement(By.id("claim_SpotID"));
		if (bookbutton.isDisplayed()) {
			bookbutton.click();
		}

		else {
			System.out.println("Book space button is not available, so click close button..........");
			driver.findElement(By
					.xpath("//div[@class='modal-dialog modal-md']//button[@type='button'][normalize-space()='Close']"))
					.click();
		}
	}
}

package testcase;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.CalendarScreen;
import base.GetSpotDetails;
import base.ManualLogindata;

public class HourlyBooking extends ManualLogindata {
	private final ManualLogindata login = new ManualLogindata();
	private final ClaimFunctionalityforMap dashboarddata = new ClaimFunctionalityforMap();
	private final CalendarScreen selectdateforbooking = new CalendarScreen();
	private static final Logger logger = LogManager.getLogger(HourlyBooking.class);
	private final ClaimFunctionalityforMap gotomap = new ClaimFunctionalityforMap();
	private final ClaimFunctionalityforMap selectspot = new ClaimFunctionalityforMap();
	private final GetSpotDetails spotdetails = new GetSpotDetails();
	
	

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
		spotdetails.Activespotdetails();
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
		Thread.sleep(10000);
		System.out.println("----------Again click spot----------------");
		driver.findElement(By.xpath("//div[contains(@class, 'image-div')]")).click();
		logger.info("Click cancel booking button.....");
		
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

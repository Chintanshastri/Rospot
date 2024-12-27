package testcase;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import base.Browser;
import base.CalendarScreen;
import base.ManualLogindata;
import base.VehicletypeDropdown;

public class GetrandomspaceFuncationality extends ManualLogindata {
	private final ManualLogindata login = new ManualLogindata();
	private final ClaimFunctionalityforMap dashboarddata = new ClaimFunctionalityforMap();
	private final CalendarScreen selectdateforbooking = new CalendarScreen();
	private final VehicletypeDropdown vehicletypedropdown = new VehicletypeDropdown();
	private static final Logger logger = LogManager.getLogger(Browser.class);
	
	@Test(priority = 0)
	public void openbrowse() throws InterruptedException {
		
		login.reusableMethod();
		logger.info("Navigating to dashboard.....");
		dashboarddata.dashboard();
		logger.info("Navigating to calender screen for selecting date.....");
		selectdateforbooking.selecttodaydate();
	}

	@Test(priority = 1)
	public void getrandomspacebutton() throws InterruptedException {
		logger.info("Navigating to vehicle dropdown.......");
		vehicletypedropdown.vehicledropdown();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locators.getProperty("getrandomspace_button"))));
		if (button.isDisplayed()) {
			System.out.println("----------Click on the Get Randomspace button----------------");
			button.click();
		}
		else {
			System.out.println("Get randomspace Button is not visible in popup.");
		}
		System.out.println("------------Spot Booked Successfully--------------");
		String claimbookingnotification = driver
				.findElement(By.xpath(locators.getProperty("claimbooking_notification"))).getText();
		System.out.println(claimbookingnotification);
		
	}

	@Test(priority = 2)
	public void cancelbooking() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement clickassignedspot = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'SpotID_assigned')]")));
		clickassignedspot.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("release_SpotID"))).click();	
	}

}

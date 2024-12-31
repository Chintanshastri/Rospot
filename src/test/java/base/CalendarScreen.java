package base;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CalendarScreen extends Browser {

	public void selecttodaydate() throws InterruptedException {

		try {
			System.out.println("----------compare date and click----------------");
			// compare date and click
			Thread.sleep(5000);
			LocalDate currentdate = LocalDate.now();
			int day = currentdate.getDayOfMonth();
			System.out.println("Current Date: " + day);
			Thread.sleep(5000);
			List<WebElement> today = driver.findElements(By.xpath(locators.getProperty("today_selectdate")));
			// List<WebElement> today =
			// driver.findElements(By.xpath("//td[contains(@class,'fc-day-top') and
			// (contains(@class, 'fc-today') or contains(@class, 'fc-future'))]"));
			int date = today.size();
			for (int i = 0; i < date; i++) {
				int intValue = Integer.parseInt(today.get(i).getText());
				if (day == intValue) {
					driver.findElement(By.xpath(locators.getProperty("spot_select"))).click();
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Timeout while trying to find the element: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}
}

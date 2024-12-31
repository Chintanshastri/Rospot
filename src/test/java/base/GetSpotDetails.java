package base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class GetSpotDetails extends Browser {
	@Test
	public void Activespotdetails() {

		try {
			System.out.println("----------Spot Details----------------");
			List<WebElement> totalspot = driver.findElements(By.xpath("//div[contains(@class, 'grid-item')]"));
			int spotcount = totalspot.size();
			System.out.println("Total Spot Counts= " + spotcount);
			System.out.println("----------Active Spot----------------");
			List<WebElement> activespot = driver.findElements(
					By.xpath("//div[contains(@class, 'grid-item') and contains(@class, 'SpotID_blank')]"));
			for (WebElement active : activespot) {
				String actualName = active.getText(); // Get visible text
				String spotId = active.getAttribute("id"); // Get ID attribute
				System.out.println("Spot ID: " + spotId + ", Spot Text: " + actualName);
			}
			Thread.sleep(4000);
			System.out.println("----------Inactive Spot----------------");
			List<WebElement> inactivespot = driver.findElements(
					By.xpath("//div[contains(@class, 'grid-item') and not(contains(@class, 'SpotID_blank'))]"));
			for (WebElement inactive : inactivespot) {
				String actualName = inactive.getText(); // Get visible text
				String spotId = inactive.getAttribute("id"); // Get ID attribute
				System.out.println("Spot ID: " + spotId + ", Spot Text: " + actualName);
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

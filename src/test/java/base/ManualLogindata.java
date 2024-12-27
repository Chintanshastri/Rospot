package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManualLogindata extends Browser{
  
	public void reusableMethod() {
		
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	        System.out.println("------------------Manual Login----------------");
			try {
				// driver.findElement(By.xpath(locators.getProperty("employee_button"))).click();
				driver.findElement(By.id(locators.getProperty("enter_email"))).sendKeys("chintan.shastri@bhavitech.com");
				driver.findElement(By.xpath(locators.getProperty("iagree_checkbox_login"))).click();
				driver.findElement(By.id(locators.getProperty("confirm_button"))).click();
				Thread.sleep(5000);
				// print login methods
				List<WebElement> elements = driver.findElements(By.className("login-options"));
				// Iterate and print the text of each element
				for (WebElement element : elements) {
					System.out.println("Login Methods: \n" + element.getText());
				}
				// Scroll down
				WebElement scrolldown = driver.findElement(By.id("manual_407"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrolldown);
				Thread.sleep(500);
				driver.findElement(By.id(locators.getProperty("continue_with_email"))).click();
				driver.findElement(By.id(locators.getProperty("passwor_login"))).sendKeys("Aa@12345678");
				driver.findElement(By.id(locators.getProperty("login_button"))).click();
				// Check for login success
				try {
					WebElement dashboardElement = wait
							.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-container")));
					System.out.println("-----Login Successful! Dashboard is visible.-------------");
				} catch (Exception e) {
					// Check for login failure
					WebElement errorMessage = driver.findElement(By.id("errorMessage"));
					if (errorMessage.isDisplayed()) {
						System.out.println("Login Failed! Error message: " + errorMessage.getText());
					} else {
						System.out.println("Login Failed! No specific error message found.");
					}
				}
			} catch (Exception e) {
				System.out.println("An error occurred during the login process: " + e.getMessage());
			}

	    }
}

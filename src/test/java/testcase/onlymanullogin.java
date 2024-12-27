package testcase;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.Browser;
import utilities.readfile;

public class onlymanullogin extends Browser {
	
	
	@Test(dataProviderClass=readfile.class,dataProvider="excelData")

	public static void fmanualloginoptions(String username, String password) {
		try{
			ExtentReports extent = new ExtentReports();
	        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
	        extent.attachReporter(spark);
	        
			// Create WebDriverWait with a timeout of 10 seconds
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));     
			System.out.println("-----------------------------------");

			//driver.findElement(By.xpath(locators.getProperty("employee_button"))).click();
			driver.findElement(By.id(locators.getProperty("enter_email"))).sendKeys(username);
			driver.findElement(By.xpath(locators.getProperty("iagree_checkbox_login"))).click();
			driver.findElement(By.id(locators.getProperty("confirm_button"))).click();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			// print login methods
			List<WebElement> elements = driver.findElements(By.className("login-options"));
			// Iterate and print the text of each element
			for (WebElement element : elements) {
				System.out.println("Login Methods: \n" + element.getText());
			}
			// Scroll down

			WebElement scrolldown = driver.findElement(By.id("manual_407"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrolldown);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locators.getProperty("continue_with_email")))).click();
			//driver.findElement(By.id(locators.getProperty("continue_with_email"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locators.getProperty("passwor_login")))).sendKeys(password);
			//driver.findElement(By.id(locators.getProperty("passwor_login"))).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locators.getProperty("login_button")))).click();
			//driver.findElement(By.id(locators.getProperty("login_button"))).click();
			// Check for login success

			try {
				WebElement dashboardElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-container")));

				System.out.println("-----Login Successful! Dashboard is visible.-------------");
			} catch (Exception e) { // Check for login failure
				WebElement errorMessage = driver.findElement(By.id("errorMessage"));
				if (errorMessage.isDisplayed()) {
					System.out.println("Login Failed! Error message: " + errorMessage.getText());
				} else {
					System.out.println("Login Failed! No specific error message found.");
				}
			}
			Thread.sleep(5000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown"))).click();
			driver.findElement(By.className("dropdown")).click();
			driver.findElement(By.xpath("//*[@id=\"main-container\"]/header/div/div[4]/ul/li[2]/ul/li[2]/a")).click();
			 // Write the report
	        extent.flush();
		} catch (Exception e) {
			
			// System.out.println("An error occurred during the login process: " +
			// e.getMessage());
		}
	}

}

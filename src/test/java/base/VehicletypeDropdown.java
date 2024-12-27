package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehicletypeDropdown extends Browser {
	public void vehicledropdown() throws InterruptedException
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vehicletype")));	
	if (dropdown.isDisplayed())
	{
		Select vehicletypedropdown = new Select(driver.findElement(By.id("vehicletype")));
		List<WebElement> totaloptions = vehicletypedropdown.getOptions(); 
		  int size = totaloptions.size(); 
		  for (int j = 0; j < size; j++) 
		  { 
		String options =totaloptions.get(j).getText();
		  System.out.println("Total available vehicle = " + options); 
		  }
		  System.out.println("----------Available Vehicle in dropdown----------------"); 
		  Thread.sleep(5000); // Select vehicle from drop-down Select
		  vehicletypedropdown = new Select(driver.findElement(By.id("vehicletype")));
		 vehicletypedropdown.selectByIndex(0);
	}
	else
	{
		System.out.println("........vehicletype dropdown is not available.........");	
	}
}}

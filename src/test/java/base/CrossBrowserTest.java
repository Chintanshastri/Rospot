package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowserTest {
	WebDriver driver;

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {
		// Initialize browser based on the parameter
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}

	@Test
	public void testGoogleHomePage() throws InterruptedException {

		driver.get("https://staging.ronspot.ie/member/login/employee");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Title: " + driver.getTitle());
		// Create WebDriverWait with a timeout of 10 seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("-----------------------------------");

		// driver.findElement(By.xpath(locators.getProperty("employee_button"))).click();
		driver.findElement(By.id("email")).sendKeys("chintan.shastri@bhavitech.com");
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/div/div/div[4]/div/label/div")).click();
		driver.findElement(By.id("emailConfirmButton")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// print login methods
		List<WebElement> elements = driver.findElements(By.className("login-options"));
		for (WebElement element : elements) {
			System.out.println("Login Methods: \n" + element.getText());

		}
		WebElement scrolldown = driver.findElement(By.id("manual_407"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrolldown);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("manual_407"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("Aa@12345678");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginBtn"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown"))).click();
		//driver.findElement(By.className("dropdown")).click();
		driver.findElement(By.xpath("//*[@id=\"main-container\"]/header/div/div[4]/ul/li[2]/ul/li[2]/a")).click();
	}

}

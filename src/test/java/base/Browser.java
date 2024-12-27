package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Browser {
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties locators = new Properties();
	public static FileReader confifilereader;
	public static FileReader locatorsfilereader;
	private static final Logger logger = LogManager.getLogger(Browser.class);
	@BeforeTest
	public void setup() throws IOException {
		
		if (driver == null) {
			confifilereader = new FileReader(
					"C:\\Users\\LENOVO\\eclipse-workspace\\Ronspot\\src\\test\\resources\\configfiles\\confi.properties");
			locatorsfilereader = new FileReader(
					"C:\\Users\\LENOVO\\eclipse-workspace\\Ronspot\\src\\test\\resources\\configfiles\\locators.properties");
			prop.load(confifilereader);
			locators.load(locatorsfilereader);
		}
		logger.info("Navigating to Browser.....");
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("testurl"));


		}

		else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("testurl"));
			
		}

	}

	
}

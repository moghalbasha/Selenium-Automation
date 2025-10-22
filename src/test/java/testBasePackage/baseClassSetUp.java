package testBasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class baseClassSetUp {
	public FileInputStream fn;
	public String path;
	public Properties prop;
	public WebDriver driver;
	public Random random;
	public Logger logger;

	public String RandomStringGenerator() {
		String text = "";
		random = new Random();
		for (int i = 0; i < 5; i++) {
			char c = (char) ('a' + random.nextInt(26));
			text += c;

		}
		return text;

	}

	public String randomNum() {
		String mobile = "";
		for (int i = 0; i < 10; i++) {
			mobile += random.nextInt(10);

		}
		return mobile;
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "Browser" })
	public void setup(String browserName) throws IOException {
		logger = LogManager.getLogger();
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "chromeheadless":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new"); // proper headless mode
			options.addArguments("--window-size=1920,1080"); // ensures elements are visible
			options.addArguments("--disable-gpu"); // avoids some rendering issues
			options.addArguments("--remote-allow-origins=*"); // Chrome 141 fixa
			driver = new ChromeDriver(options);
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Browser name invalid or doesnot exist");
			return;
		}
		path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "resources" + File.separator + "data.properties";
		prop = new Properties();
		fn = new FileInputStream(path);
		prop.load(fn);
		driver.manage().deleteAllCookies();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		logger.info("***opening site***");
//	 driver.get("https://tutorialsninja.com/demo");
		driver.get(prop.getProperty("baseURL"));
		
		

	}
	public void clickElement(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	    element.click();
	}


	@AfterMethod(alwaysRun = true)

	public void teardown() {
		logger.info("***quit site***");

		driver.quit();
	}
}

package JavaSelenium.Testcomponets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import JavaSelenium.pageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver; // Class-level driver
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		// Load properties file
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\JavaSelenium\\Resources\\GlobalData.properties");

		prop.load(file);
		String browserName = prop.getProperty("browser");

		// Initialize WebDriver based on browser
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromiumdriver().setup();
			driver = new ChromeDriver(); // Assigning to class-level driver
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Initialize Firefox driver here
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Initialize Edge driver here
		}

		// Ensure driver is initialized before applying settings
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@BeforeMethod(alwaysRun = true)

	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)

	public void closeBrowser() {
		driver.close();
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver; 
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "//reports//" + testcaseName + ".png");
		FileUtils.copyFile(Source, file);
		return System.getProperty("user.dir")+ "//reports//" + testcaseName + ".png";
	}

}

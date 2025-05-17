package BaseClass;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ConfigReader;
import Utils.DriverFactory;
import Utils.ExtentManager;
import Utils.ExtentTestManager;
import Utils.Screenshots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	protected WebDriver driver; // Protected so child classes (tests) can use it
	protected ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void setUpReport() {
		extent = ExtentManager.getExtentReports();
	}

	@Parameters("browser")
	@BeforeMethod
	public void setup(Method method, String browser) {
		ExtentTestManager.createTest(method.getName() + " [" + browser + "]");

//		String browser = ConfigReader.getProperty("browser");
		WebDriver driverInstance = null;

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driverInstance = new ChromeDriver();
			DriverFactory.setDriver(driverInstance);
		}
		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driverInstance = new EdgeDriver();
			DriverFactory.setDriver(driverInstance);
		}
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driverInstance = new FirefoxDriver();
			DriverFactory.setDriver(driverInstance);
		}

		// Set ThreadLocal driver

		// Use thread-safe driver from DriverFactory
		DriverFactory.getDriver().manage().window().maximize();
		DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		ExtentTestManager.getTest().info("Browser Launched");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			Screenshots.takeScreenshot(driver, result.getName());
			ExtentTestManager.getTest().fail("Test Failed: " + result.getThrowable());
			DriverFactory.quitDriver();
		}
	}

	@AfterSuite
	public void tearDownReport() {
		ExtentTestManager.flushReports();
	}

}

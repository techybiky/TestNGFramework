package Utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	public void onTestFailuer(ITestResult result) {
		WebDriver driver = DriverFactory.getDriver();
		if (ITestResult.FAILURE == result.getStatus()) {
			Screenshots.takeScreenshot(driver, result.getName());
			ExtentTestManager.getTest().fail("Test Failed: " + result.getThrowable());

		}
	}

}

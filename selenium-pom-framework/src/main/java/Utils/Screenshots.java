package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshots {

	public static void takeScreenshot(WebDriver driver, String testName) {
		// Create a timestamp
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		// Cast driver to TakesScreenshot
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Define target path
		String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

		try {
			FileHandler.createDir(new File("screenshots")); // Ensure the folder exists
			FileHandler.copy(srcFile, new File(screenshotPath));
			System.out.println("Screenshot saved at: " + screenshotPath);
		} catch (IOException e) {
			System.out.println("Failed to save screenshot: " + e.getMessage());
		}
	}

}

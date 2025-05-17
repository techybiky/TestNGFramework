package LoginTest;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBroswer {
	WebDriver driver;
	public static void main(String[] args) {
	    WebDriverManager.edgedriver().setup();
	    WebDriver driver = new FirefoxDriver();
	    driver.get("https://example.com");
	}
	
	Wait<WebDriver> wait = new FluentWait<>(driver)
			.pollingEvery(Duration.ofSeconds(1))
			.withTimeout(Duration.ofMillis(100))
			.ignoring(NoSuchElementException.class);
	
	
			

}

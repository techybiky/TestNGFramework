package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize WebElements
	}

	@FindBy(xpath = "//a[@id='login2']")
	private WebElement Login;

	@FindBy(xpath = "//input[@id='loginusername']")
	private WebElement username;

	@FindBy(xpath = "//input[@id='loginpassword']")
	private WebElement password;

	@FindBy(xpath = "//button[normalize-space()='Log in']")
	private WebElement submit;

	// Action Method

	public void clickLogin() {
		Login.click();
	}

	public void username(String user) {
		username.sendKeys(user);
	}

	public void Userpassword(String pass) {
		password.sendKeys(pass);
	}

	public void button() {
		submit.click();
		;
	}

}

package LoginTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import PageObject.HomePage;
import Utils.TestListener;

@Listeners(TestListener.class)
public class LoginE2ETest extends BaseClass {

	@DataProvider(name = "LoginData")
	public Object[][] data() {
		return new Object[][] { { "bikram", "bikram1234" }, { "bikram1", "bikram12345" } };

	}

	@Test(dataProvider = "LoginData")
	public void LoginTest(String username, String password) throws Throwable {
		HomePage hp = new HomePage(driver);
		hp.clickLogin();
		Thread.sleep(1000);
		hp.username(username);
		hp.Userpassword(password);
		Thread.sleep(1000);
		hp.button();
		test.pass("Login Sucessfull");

	}
}

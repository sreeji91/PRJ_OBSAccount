package sequential;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pomClasses.POMLogin;
import pomClasses.POMUnits;
import utility.DriverUtility;
import utility.ExcelUtility;
import utility.PropertyReadUtility;
import utility.WaitUtility;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login {
	public static WebDriver driver;
	POMLogin objPomLogin;

	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");

	@Test(priority = 1, enabled = true)
	public void logIn() throws IOException {
		String username = ExcelUtility.readStringData(1, 0);
		String password = ExcelUtility.integerData(1, 1);
		objPomLogin.loginVerification(username, password);

		String current_url = driver.getCurrentUrl();
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(PropertyReadUtility.readConfigFile("url"), current_url);
		objassert.assertAll();
	}

	@Test(priority = 2, enabled = true, groups = { "failed" })
	public void logInFailed() throws IOException, InterruptedException {
		String username = ExcelUtility.readStringData(1, 0);
		String password = ExcelUtility.integerData(1, 1);
		objPomLogin.loginVerification(username, password);

		String current_url = driver.getCurrentUrl();
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(PropertyReadUtility.readConfigFile("search"), current_url);
		objPomLogin.signout();
		objassert.assertAll();
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
		objPomLogin = new POMLogin(driver);
	}

}

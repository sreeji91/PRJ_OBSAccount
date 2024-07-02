package crossBrowser;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import pomClasses.POMLogin;
import pomClasses.POMUnits;
import utility.DriverUtility;
import utility.ExcelUtility;
import utility.ExtendTestManager;
import utility.PropertyReadUtility;
import utility.WaitUtility;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Login extends ExtendTestManager {
	static WebDriver driver;
	POMLogin objPomLogin;
	ExtentTest test;
	public ExtentReports extent;

	static String url = PropertyReadUtility.readConfigFile("login_url");

	ExtendTestManager objTestManager;

	@Test(priority = 1, enabled = true)

	public void logIn() throws IOException {
		test = extent.createTest("Validating login scenario");
		boolean Status;
		String username = ExcelUtility.readStringData(1, 0);
		String password = ExcelUtility.integerData(1, 1);
		objPomLogin.loginVerification(username, password);

		String current_url = driver.getCurrentUrl();
		SoftAssert objassert = new SoftAssert();
		if (PropertyReadUtility.readConfigFile("url").contains(current_url)) {

			objassert.assertTrue(true);
			Status = true;
		} else {
			objassert.assertTrue(false);
			Status = false;
		}

		objassert.assertAll();
		if (Status == true) {
			test.log(com.aventstack.extentreports.Status.PASS, "Login successfully to the application");
		} else if (Status == false) {
			test.log(com.aventstack.extentreports.Status.FAIL, "Login failed");
		}

	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "browser1" })
	public void beforeTest(String browser1) {
		DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser1);
		driver = objDriverManager.driver;
		objPomLogin = new POMLogin(driver);
		objTestManager = new ExtendTestManager();
		extent = objTestManager.extendreportgenerate();
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}

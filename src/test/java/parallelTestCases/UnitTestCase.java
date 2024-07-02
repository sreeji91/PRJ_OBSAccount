package parallelTestCases;

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
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UnitTestCase extends ExtendTestManager {
	static WebDriver driver;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");
	POMLogin objPomLogin;
	POMUnits objPomUnits;

	ExtentTest test;
	public ExtentReports extent;
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

	@Test(priority = 2, enabled = true, dataProvider = "testdata")
	public void Add_units(String tData1, String tData2) throws InterruptedException, IOException {
		test = extent.createTest("Validating Units");

		objPomLogin.product_click();

		objPomUnits.units_click();
		objPomUnits.addUnits(tData1, tData2);

		String actual_message = "Unit added successfully";
		String message = objPomUnits.getMessage();

		Assert.assertTrue(actual_message.contains(message));
		test.log(com.aventstack.extentreports.Status.PASS, "Unit added successfully to the application");

	}

	@Test(priority = 3, enabled = true)
	public void search_units() throws InterruptedException {
		boolean status = objPomUnits.searchUnits(PropertyReadUtility.readConfigFile("unit_test_data"));
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(status, true);
		objassert.assertAll();

	}

	@Test(priority = 4, enabled = true)
	public void deleteUnits() throws InterruptedException {
		objPomUnits.delete_unit(PropertyReadUtility.readConfigFile("unit_test_data"));

		String actual_message = "Unit deleted successfully";
		String message = objPomUnits.getMessage();
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(actual_message, message);
		objassert.assertAll();

	}

	@BeforeTest
	public void beforeTest() {
		DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
		objPomLogin = new POMLogin(driver);
		objPomUnits = new POMUnits(driver);
		objTestManager = new ExtendTestManager();
		extent = objTestManager.extendreportgenerate();
	}

	@AfterTest
	public void afterTest() {
		objTestManager.closereport();
		driver.close();

	}

	@DataProvider(name = "testdata")
	public Object[][] TestDataFeed() {

		Object[][] unitsData = new Object[1][2];

		// Enter data to row 0 column 0
		unitsData[0][0] = "Test_unit";
		// Enter data to row 0 column 1
		unitsData[0][1] = "t_unit";

		return unitsData;
	}

}

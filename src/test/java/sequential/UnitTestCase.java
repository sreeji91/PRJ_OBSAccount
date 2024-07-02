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
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UnitTestCase {
	static WebDriver driver;
	POMLogin objPomLogin;
	POMUnits objPomUnits;

	@Test(priority = 1, enabled = true, dataProvider = "testdata")
	public void Add_units(String tData1, String tData2) throws InterruptedException, IOException {

		objPomLogin.product_click();

		objPomUnits.units_click();
		objPomUnits.addUnits(tData1, tData2);

		String actual_message = "Unit added successfully";
		String message = objPomUnits.getMessage();

		Assert.assertEquals(actual_message, message);

	}

	@Test(priority = 2, enabled = true)
	public void search_units() throws InterruptedException {
		boolean status = objPomUnits.searchUnits(PropertyReadUtility.readConfigFile("unit_test_data"));
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(status, true);
		objassert.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void deleteUnits() throws InterruptedException {
		objPomUnits.delete_unit(PropertyReadUtility.readConfigFile("unit_test_data"));

		String actual_message = "Unit deleted successfully";
		String message = objPomUnits.getMessage();
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(actual_message, message);
		objassert.assertAll();
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		Login objLogin = new Login();

		objPomLogin = new POMLogin(objLogin.driver);
		objPomUnits = new POMUnits(objLogin.driver);
	}

	@DataProvider(name = "testdata")
	public Object[][] TestDataFeed() {

		// Create object array with 1 rows and 2 column- first parameter is row and
		// second is //column
		Object[][] unitsData = new Object[1][2];

		// Enter data to row 0 column 0
		unitsData[0][0] = "Test_unit";
		// Enter data to row 0 column 1
		unitsData[0][1] = "t_unit";

		return unitsData;
	}

}

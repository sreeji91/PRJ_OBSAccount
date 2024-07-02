package parallelTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pomClasses.POMCategory;
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

public class CategoryTC {
	static WebDriver driver;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");
	POMLogin objPomLogin;
	POMCategory objpomCategory;

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

	@Test(priority = 2, enabled = true, dataProvider = "testData")
	public void add_category(String categoryName, String categoryCode) throws IOException, InterruptedException {

		objPomLogin.product_click();

		objpomCategory.category_click();
		objpomCategory.add_Category(categoryName, categoryCode);

		String actual_message = "Category added successfully";
		String exp_message = objpomCategory.expected_message;

		Assert.assertTrue(actual_message.contains(exp_message));
	}

	@Test(priority = 3, enabled = true)
	public void SearchCategory() throws InterruptedException {
		boolean status = objpomCategory.Search_category(PropertyReadUtility.readConfigFile("category_test_data"));
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(status, true);
		objassert.assertAll();
	}

	@Test(priority = 4, enabled = true)
	public void deleteCategory() throws InterruptedException {
		objpomCategory.delete_category(PropertyReadUtility.readConfigFile("category_test_data"));

		String actual_message = "Category deleted successfully";
		String exp_message = objpomCategory.expected_message;
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(actual_message, exp_message);
		objassert.assertAll();

	}

	@BeforeTest
	public void beforeTest() {
		DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
		objPomLogin = new POMLogin(driver);
		objpomCategory = new POMCategory(driver);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

	@DataProvider(name = "testData")
	public Object[][] TestDataFeed() {

		// Create object array with 1 rows and 2 column- first parameter is row and
		// second is //column
		Object[][] categoryData = new Object[1][2];

		// Enter data to row 0 column 0
		categoryData[0][0] = "Test_category";
		// Enter data to row 0 column 1
		categoryData[0][1] = "t_code";

		return categoryData;
	}

}

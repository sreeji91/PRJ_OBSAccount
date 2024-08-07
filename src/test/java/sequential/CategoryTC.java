package sequential;

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
	WebDriver driver;
	Login objLogin = new Login();
	POMLogin objPomLogin;
	POMUnits obPomUnits;

	WaitUtility objWait;
	POMCategory objpomCategory;

	@Test(priority = 1, enabled = true, dataProvider = "testData")
	public void add_category(String categoryName, String categoryCode) throws IOException, InterruptedException {

		objpomCategory = new POMCategory(objLogin.driver);
		objpomCategory.category_click();

		objpomCategory.add_Category(categoryName, categoryCode);

		String actual_message = "Category added successfully";
		String exp_message = objpomCategory.expected_message;

		Assert.assertTrue(actual_message.contains(exp_message));
	}

	@Test(priority = 2, enabled = true)
	public void SearchCategory() throws InterruptedException {
		boolean status = objpomCategory.Search_category(PropertyReadUtility.readConfigFile("category_test_data"));
		Assert.assertEquals(status, true);
	}

	@Test(priority = 3, enabled = true)
	public void deleteCategory() throws InterruptedException {
		objpomCategory.delete_category(PropertyReadUtility.readConfigFile("category_test_data"));

		String actual_message = "Category deleted successfully";
		String exp_message = objpomCategory.expected_message;
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(actual_message, exp_message);
		objassert.assertAll();

	}

	@AfterTest
	public void afterTest() {
		objLogin.driver.close();
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

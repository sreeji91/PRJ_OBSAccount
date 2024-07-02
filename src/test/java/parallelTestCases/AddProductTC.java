package parallelTestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pomClasses.POMAddProduct;
import pomClasses.POMLogin;
import utility.DriverUtility;
import utility.ExcelUtility;
import utility.PropertyReadUtility;

public class AddProductTC {
	static WebDriver driver;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");
	POMLogin objPomLogin;
	POMAddProduct objPomAddProduct;

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

	@Test(priority = 2, enabled = true)
	public void add_Product() throws Exception {

		objPomLogin.product_click();

		objPomAddProduct.add_product();
		String message = objPomAddProduct.get_message();
		String exp_message = "Product added successfully";
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(exp_message, message);
		objassert.assertAll();
	}

	@BeforeTest
	public void beforeTest() {
		DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
		objPomLogin = new POMLogin(driver);
		objPomAddProduct = new POMAddProduct(driver);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}
}

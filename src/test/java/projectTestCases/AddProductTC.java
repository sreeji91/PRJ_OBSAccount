package projectTestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtility.PropertyFileRead;
import excelUtility.ExcelRead;
import pomClasses.POMAddProduct;
import pomClasses.POMLogin;
import webDriverUtility.DriverManager;

public class AddProductTC {
	static WebDriver driver;
	static String url = "https://qalegend.com/billing/public/login";
	static String browser = "chrome";
	POMLogin objPomLogin;
	POMAddProduct objPomAddProduct;
	
	@Test(priority = 1, enabled = true)
	public void logIn() throws IOException {
		String username = ExcelRead.readStringData(1, 0);
		String password = ExcelRead.integerData(1, 1);
		objPomLogin.login(username, password);

		String current_url = driver.getCurrentUrl();
		SoftAssert asser = new SoftAssert();
		asser.assertEquals(PropertyFileRead.readConfigFile("url"), current_url);
		asser.assertAll();
	}
	
	
  @Test(priority = 2,enabled=true)
  public void add_Product() throws InterruptedException {
	  
	  objPomLogin.product_click();
	  objPomAddProduct=new POMAddProduct(driver);
	  objPomAddProduct.add_product(PropertyFileRead.readConfigFile("product_name"),PropertyFileRead.readConfigFile("brand_test_data"),PropertyFileRead.readConfigFile("category_test_data"),PropertyFileRead.readConfigFile("alert_quantity"),PropertyFileRead.readConfigFile("expiry_period"),PropertyFileRead.readConfigFile("exc_tax"));
	  String message=objPomAddProduct.get_message();
	  String exp_message="Product added successfully";
	  SoftAssert asser=new SoftAssert();
	  asser.assertEquals(exp_message, message);
	  asser.assertAll();
  }
  @BeforeTest
  public void beforeTest() {
	  DriverManager objDriverManager = new DriverManager();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
		objPomLogin=new POMLogin(driver);
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }
}

package projectTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtility.PropertyFileRead;
import excelUtility.ExcelRead;
import pomClasses.POMBrands;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import waitUtility.WaitUtility;
import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class BrandsTestCase {
	static WebDriver driver;
	static String url = "https://qalegend.com/billing/public/login";
	static String browser = "chrome";
	POMLogin objPomLogin;
	POMBrands objPomBrands;
	
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
  @Test(priority = 2,enabled=true,dataProvider = "testData")
  public void addBrands(String brandName,String brandDescription) throws IOException, InterruptedException {
	  
		objPomLogin.product_click();
		
		objPomBrands=new POMBrands(driver);
		objPomBrands.brands_click();
		objPomBrands.add_brands(brandName,brandDescription);
		
		String exp_message="Brand added successfully";
		String current_message=objPomBrands.expected_message;
		
		Assert.assertTrue(exp_message.contains(current_message));
		
  }
  
  @Test(priority = 3,enabled=true)
  public void SearchCategory() throws InterruptedException 
  {
	 boolean status=objPomBrands.search_brand(PropertyFileRead.readConfigFile("brand_test_data"));
	 SoftAssert asser=new SoftAssert();
	 asser.assertEquals(status, true);
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
  @DataProvider(name="testData")
  public Object[][] TestDataFeed(){

  // Create object array with 1 rows and 2 column- first parameter is row and second is //column
  Object [][] brandData=new Object[1][2];

  // Enter data to row 0 column 0
  brandData[0][0]="Sree_brand";
  // Enter data to row 0 column 1
  brandData[0][1]="Sree_brand";
  
  return brandData;
  }

}

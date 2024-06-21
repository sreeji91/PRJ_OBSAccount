package projectTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commonUtility.PropertyFileRead;
import excelUtility.ExcelRead;
import pomClasses.POMCategory;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import waitUtility.WaitUtility;
import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class CategoryTC {
	static WebDriver driver;
	static String url = "https://qalegend.com/billing/public/login";
	static String browser = "chrome";
	POMLogin objPomLogin;
	POMCategory objpomCategory;
	
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
	
  @Test(priority = 2,enabled=true, dataProvider = "testData")
  public void add_category(String categoryName,String categoryCode ) throws IOException, InterruptedException {
	 
		objPomLogin.product_click();
		
		objpomCategory=new POMCategory(driver);
		objpomCategory.category_click();
		objpomCategory.add_Category(categoryName,categoryCode);
		
		String actual_message="Category added successfully";
		String exp_message=objpomCategory.expected_message;
		
		Assert.assertTrue(actual_message.contains(exp_message));
  }
  
  @Test(priority = 3,enabled=true)
  public void SearchCategory() throws InterruptedException 
  {
	  boolean status=objpomCategory.Search_category(PropertyFileRead.readConfigFile("category_test_data"));
	  SoftAssert asser=new SoftAssert();
	  asser.assertEquals(status, true);
	  asser.assertAll();
  }
  
  @Test(priority = 4,enabled=true)
  public void deleteCategory() throws InterruptedException
  {
	  objpomCategory.delete_category(PropertyFileRead.readConfigFile("category_test_data"));
	  
	  String actual_message="Category deleted successfully";
	  String exp_message=objpomCategory.expected_message;
	  SoftAssert asser=new SoftAssert();
	  asser.assertEquals(actual_message,exp_message );
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
  Object [][] categoryData=new Object[1][2];

  // Enter data to row 0 column 0
  categoryData[0][0]="Test_category";
  // Enter data to row 0 column 1
  categoryData[0][1]="t_code";
  
  return categoryData;
  }

}

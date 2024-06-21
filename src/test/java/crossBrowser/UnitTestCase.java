package crossBrowser;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import commonUtility.PropertyFileRead;
import excelUtility.ExcelRead;
import extendReport.ExtendTestManager;
import pomClasses.POMLogin;
import pomClasses.POMUnits;
import waitUtility.WaitUtility;
import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UnitTestCase extends ExtendTestManager {
	static WebDriver driver;
	static String url = "https://qalegend.com/billing/public/login";
	//static String browser = "chrome";
	POMLogin objPomLogin;
	POMUnits objPomUnits;
	
	ExtentTest test;
	public ExtentReports extent;
	ExtendTestManager objTestManager;
	
	
	@Test(priority = 1, enabled = true)
	public void logIn() throws IOException {
		test=extent.createTest("Validating login scenario");
		boolean Status;
		String username = ExcelRead.readStringData(1, 0);
		String password = ExcelRead.integerData(1, 1);
		objPomLogin.login(username, password);

		String current_url = driver.getCurrentUrl();
		SoftAssert asser = new SoftAssert();
		if(PropertyFileRead.readConfigFile("url").contains(current_url))
		{
			
			asser.assertTrue(true);
			Status=true;
		}
		else {
			asser.assertTrue(false);
			Status=false;
		}
		
		asser.assertAll();
		if(Status==true)
		{
			test.log(com.aventstack.extentreports.Status.PASS, "Login successfully to the application");
		}
		else if(Status==false) {
			test.log(com.aventstack.extentreports.Status.FAIL, "Login failed");
		}
	}

	@Test(priority = 2, enabled = true, dataProvider="testdata")
	public void Add_units(String tData1,String tData2) throws InterruptedException, IOException {
		test=extent.createTest("Validating Units");
		
		objPomLogin.product_click();
		
		
		objPomUnits=new POMUnits(driver);
		objPomUnits.units_click();
		objPomUnits.addUnits(tData1,tData2);
		
		String actual_message="Unit added successfully";
		String message=objPomUnits.getMessage();
		
		Assert.assertTrue(actual_message.contains(message));
		test.log(com.aventstack.extentreports.Status.PASS, "Unit added successfully to the application");

		
		
	}
	
	@Test(priority = 3, enabled = true)
	public void search_units() throws InterruptedException
	{
		boolean status=objPomUnits.searchUnits(PropertyFileRead.readConfigFile("unit_test_data"));
		SoftAssert asser=new SoftAssert();
		asser.assertEquals(status, true);
		asser.assertAll();
		
	}
	
	@Test(priority = 4, enabled = true)
	public void deleteUnits() throws InterruptedException
	{
		objPomUnits.delete_unit(PropertyFileRead.readConfigFile("unit_test_data"));
		
		String actual_message="Unit deleted successfully";
		String message=objPomUnits.getMessage();
		SoftAssert asser=new SoftAssert();
		asser.assertEquals(actual_message,message);
		asser.assertAll();
		
	}
	
	

	@BeforeTest(alwaysRun = true)
	@Parameters({"browser2"})
	public void beforeTest(String browser2) {
		DriverManager objDriverManager = new DriverManager();
		objDriverManager.launchBrowser(url, browser2);
		driver = objDriverManager.driver;
		objPomLogin=new POMLogin(driver);
		objTestManager=new ExtendTestManager();
		extent=objTestManager.extendreportgenerate();
	}
	
	

	@AfterTest
	public void afterTest() {
		objTestManager.closereport();
		driver.close();
	
	}
	
	 @DataProvider(name="testdata")
	  public Object[][] TestDataFeed(){

	  // Create object array with 1 rows and 2 column- first parameter is row and second is //column
	  Object [][] unitsData=new Object[1][2];

	  // Enter data to row 0 column 0
	  unitsData[0][0]="Test_unit";
	  // Enter data to row 0 column 1
	  unitsData[0][1]="t_unit";
	  
	  return unitsData;
	  }

}

package automationCore;

import org.testng.annotations.Test;

import pomClasses.POMLogin;
import utility.DriverUtility;
import utility.PropertyReadUtility;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class BasePage {
	public WebDriver driver;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");

  
  @BeforeTest
  public void beforeTest() {
	  DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;
	
  }

  @AfterTest
  public void afterTest() {
	  driver.close();
  }

}

package projectTestCases;

import org.testng.annotations.Test;

import excelUtility.ExcelRead;
import pomClasses.POMLogin;

import webDriverUtility.DriverManager;

import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class Login {
	static WebDriver driver;
	POMLogin objPomLogin;
	static String url="https://qalegend.com/billing/public/login";
	static String browser="chrome";
	
  @Test(priority = 1,enabled=true)
  public void logIn() throws IOException {
	  String username=ExcelRead.readStringData(1, 0);
	  String password=ExcelRead.integerData(1, 1);
	  
	  objPomLogin.login(username,password);
	  
  }
  @BeforeTest
  public void beforeTest()  {
	  DriverManager objDriverManager=new DriverManager();
	  objDriverManager.launchBrowser(url, browser);
	  driver=objDriverManager.driver;
	  objPomLogin=new POMLogin(driver);
	  
	  
	  
  }

  @AfterTest
  public void afterTest() {
  }

}

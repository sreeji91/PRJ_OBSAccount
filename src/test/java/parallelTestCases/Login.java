package parallelTestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automationCore.BasePage;
import pomClasses.POMLogin;
import utility.ExcelUtility;
import utility.PropertyReadUtility;
import java.io.IOException;

public class Login extends BasePage {
	POMLogin objPomLogin;
	

	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");

	

	@Test(priority = 1, enabled = true)
	public void ValidatelogIn() throws IOException {
		
		objPomLogin=new POMLogin(driver);
		String username = ExcelUtility.readStringData(1, 0);
		String password = ExcelUtility.integerData(1, 1);
		objPomLogin.loginVerification(username, password);
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(objPomLogin.isElementDisplayed(), true);	
	}


}

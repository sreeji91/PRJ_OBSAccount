package testNGUtility;


import java.io.IOException;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import webDriverUtility.DriverManager;
import webDriverUtility.WebDriverActions;

public class Retry implements IRetryAnalyzer {
	WebDriverActions objActions;
	DriverManager objManage;
	
	
	private int retryCount = 0;
	private int maxRetryCount = 2;

	public boolean retry(ITestResult result ) {
		objManage=new DriverManager();
		

		if (retryCount < maxRetryCount) {
			System.out.println("Retrying test " + result.getName() + " with status "
					+ getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			try {
				objManage.screenshot(result.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retryCount++;
			return true;
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}

}

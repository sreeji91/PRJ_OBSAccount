package webDriverUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager 
{
	public WebDriver driver;
	public void launchBrowser(String url,String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		if(browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		
		
	}
	public void closeBrowser()
	{
		driver.close();
	}
	public WebDriver driver()
	{
		return driver;
	}
}

package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import waitUtility.WaitUtility;
import webDriverUtility.WebDriverActions;

public class POMLogin {
	WebDriver driver;
	WebDriverActions objActions;
	WaitUtility objWait;

	public POMLogin(WebDriver driver) {
		this.driver = driver;
		objActions = new WebDriverActions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='username']")
	public WebElement webuserName;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement webpassword;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement webLoginBtn;

	@FindBy(xpath = "//button[text()='End tour']")
	public WebElement endTourBtn;
	
	@FindBy(xpath = "//a[@id='tour_step5_menu']")
	public WebElement product;
	
	@FindBy(xpath="/html/body/div[2]/header/nav/div/ul/li[2]/a")
	public WebElement profile;
	
	@FindBy (xpath="//a[text()='Sign Out']")
	public WebElement signOut;

	public void login(String username, String password)  {
		objActions.sendkeys(webuserName, username);
		objActions.sendkeys(webpassword, password);
		objActions.click(webLoginBtn);
		try {
		if(endTourBtn.isDisplayed())
		{
		objActions.click(endTourBtn);
		}
		}
		catch(Exception e)
		{
			System.out.println("handle exception");
		}
		

	}
	
	public void product_click() throws InterruptedException {

		objActions.click(product);
		objWait=new WaitUtility(driver);
		objWait.normalWait(2000);

	}
	public void signout() throws InterruptedException
	{
		objActions.click(profile);
		objWait=new WaitUtility(driver);
		objWait.normalWait(4000);
		objActions.JavascriptClick(signOut);
		objWait.normalWait(8000);
	}

}

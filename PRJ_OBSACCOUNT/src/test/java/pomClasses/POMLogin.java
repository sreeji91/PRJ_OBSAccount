package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webDriverUtility.WebDriverActions;

public class POMLogin {
WebDriver driver;
WebDriverActions objActions;

public POMLogin(WebDriver driver)
{
this.driver=driver;
objActions=new WebDriverActions(driver);
PageFactory.initElements(driver, this);
}

@FindBy (xpath="//input[@id='username']")
public WebElement webuserName;

@FindBy (xpath="//input[@id='password']")
public WebElement webpassword;

@FindBy (xpath="//button[@type='submit']")
public WebElement webLoginBtn;

public void login(String username,String password)
{
	objActions.sendkeys(webuserName, username);
	objActions.sendkeys(webpassword, password);
	objActions.click(webLoginBtn);
	
}


}

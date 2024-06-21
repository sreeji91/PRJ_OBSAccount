package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import waitUtility.WaitUtility;
import webDriverUtility.WebDriverActions;

public class POMBrands {
	public static String expected_message="";
	WebDriver driver;
	WebDriverActions objActions;
	WaitUtility objWait;
	
	public POMBrands(WebDriver driver) {
		this.driver=driver;
		objActions=new WebDriverActions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//span[text()='Brands']")
	public WebElement brands;
	
	@FindBy(xpath="//button[contains(@class,'btn btn-block')]")
	public WebElement addBrandsBtn;
	
	@FindBy (xpath="//input[@id='name']")
	public WebElement brand_name;
	
	@FindBy (xpath="//input[@id='description']")
	public WebElement brand_discription;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	public WebElement save_Button;
	
	@FindBy (xpath="//*[@id='toast-container']/div")
	public WebElement popUpMessage;
	
	@FindBy (xpath="//div[@id='brands_table_filter']/label/input")
	public WebElement search;
	
	@FindBy (xpath="//table[@id='brands_table']/tbody/tr/td[1]")
	public WebElement table_value;
	
	public void brands_click() throws InterruptedException
	
	{
		objWait = new WaitUtility(driver);
		objWait.normalWait(2000);
		objActions.click(brands);
	}
	public void add_brands(String brandName,String brandDescription) throws InterruptedException
	{
		objActions.click(addBrandsBtn);
		objWait=new WaitUtility(driver);
		objWait.normalWait(2000);
		objActions.sendkeys(brand_name, brandName);
		objActions.sendkeys(brand_discription, brandDescription);
		objActions.click(save_Button);
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
		String expected_message=objActions.getText(popUpMessage);
		System.out.println("Expected message= "+expected_message);
		
		
	}
	public boolean search_brand(String brand) throws InterruptedException
	{
		objActions.click(search);
		objActions.sendkeys(search, brand);
		objWait.normalWait(2000);
		String table_name=objActions.getText(table_value);
		if(table_name.equalsIgnoreCase(brand))
		{
			return true;
		}
		else {
			return false;
		}
		
	}
}

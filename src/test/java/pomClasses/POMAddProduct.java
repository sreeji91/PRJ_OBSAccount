package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import waitUtility.WaitUtility;
import webDriverUtility.WebDriverActions;

public class POMAddProduct {
	 WebDriver driver;
	 WebDriverActions objActions;
	 WaitUtility objWait;
	 public POMAddProduct(WebDriver driver)
	 {
		 this.driver=driver;
		 objActions=new WebDriverActions(driver);
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(xpath="//li[@id='tour_step5']/ul/li[2]/a")
	 public WebElement addProduct;
	 
	 @FindBy(xpath="//input[@id='name']")
	 public WebElement product_name;
	 
	 @FindBy(xpath="//span[@id='select2-brand_id-container'] ")
	 public WebElement brand_id;
	 
	 @FindBy (xpath="/html/body/span/span/span[1]/input")
	 public WebElement brand_search;
	 
	 @FindBy(xpath="//ul[@id='select2-brand_id-results']/li[1]")
	 public WebElement brand_name;
	 
	 @FindBy(xpath="//span[@id='select2-category_id-container']")
	 public WebElement category_id;
	 
	 @FindBy(xpath="/html/body/span/span/span[1]/input")
	 public WebElement category_search;
	 
	 @FindBy(xpath="//ul[@id='select2-category_id-results']/li[1]")
	 public WebElement category_name;
	 
	 @FindBy(xpath="//input[@id='alert_quantity']")
	 public WebElement alert_quantity;
	 
	 @FindBy(xpath="//input[@id='expiry_period']")
	 public WebElement expiry_period;
	 
	 @FindBy(xpath="//input[@id='single_dpp']")
	 public WebElement exc_tax;
	 
	 @FindBy(xpath="//div[@class='btn-group']/button[4]")
	 public WebElement save_btn;
	 
	 @FindBy (xpath="//*[@id='toast-container']/div")
	 public WebElement popUpMessage;
	 
	 
	 
	 public void add_product(String productName,String brandName,String categoryName,String alertQuantity,String expiryPeriod,String excTax ) throws InterruptedException
	 {
		 objActions.click(addProduct);
		 objWait=new WaitUtility(driver);
		 objWait.normalWait(2000);
		 objActions.sendkeys(product_name, productName);
		 objActions.click(brand_id);
		 objWait.normalWait(2000);
		 objActions.sendkeys(brand_search, brandName);
		 objActions.click(brand_name);
		 objWait.normalWait(2000);
		 objActions.click(category_id);
		 objWait.normalWait(2000);
		 objActions.sendkeys(category_search, categoryName);
		 objActions.click(category_name);
		 objActions.sendkeys(alert_quantity, alertQuantity);
		 objWait.normalWait(2000);
		 objActions.sendkeys(expiry_period, expiryPeriod);
		 objWait.normalWait(2000);
		 objActions.sendkeys(exc_tax, excTax);
		 objWait.normalWait(2000);
		 objActions.click(save_btn); 
	 }
	 public String get_message()
	 {
		 objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"),5);
		 return objActions.getText(popUpMessage);
	 }
	 
	

}

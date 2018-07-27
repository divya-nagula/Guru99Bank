package Customer_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P01_HomePage {
  
	public static String PageTitle;

	WebDriver driver;
		
	@FindBy(xpath="/html/body/div[2]/div/ul/li[2]/a")
	public WebElement BalanceEnquiry;
	
	@FindBy(xpath="/html/body/div[2]/div/ul/li[3]/a")
	public WebElement FundTransfer;
	
	@FindBy(xpath="/html/body/div[2]/div/ul/li[4]/a")
	public WebElement ChangePassword;
	
	@FindBy(xpath="/html/body/div[2]/div/ul/li[5]/a")
	public WebElement MiniStatement;
	
	@FindBy(xpath="/html/body/div[2]/div/ul/li[6]/a")
	public WebElement CustomisedStatement;
	
	@FindBy(xpath="/html/body/div[2]/div/ul/li[7]/a")
	public WebElement LogOut;
	
	
	public P01_HomePage(WebDriver driver){
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	}

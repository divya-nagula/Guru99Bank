package BankManager_PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class P05_NewAccount {
	
	WebDriver driver;
	public static String PageTitle;
	public static String AccountID;
	P01_HomePage objHomePage;
	@FindBy(name = "cusid")
	WebElement CustomerID;
	
	@FindBy(name = "selaccount")
	WebElement AccountType;
	
	@FindBy(name = "inideposit")
	WebElement InitialDeposit;
	
	@FindBy(name = "button2")
	WebElement Submit;
	
	@FindBy(name = "reset")
	WebElement Reset;
	
	@FindBy(name = "accmsg")
	WebElement table;
	
	@FindBy(xpath = "//*[@id=\"account\"]/tbody/tr[11]/td/a")
	public static WebElement Continue;
	
	public P05_NewAccount(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.NewAccount.click();
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void CreateAccount(String CustomerID, String AccType, String Deposit) {
		this.CustomerID.sendKeys(CustomerID);
		Select AcType = new Select(this.AccountType);
		AcType.selectByVisibleText(AccType);
		this.InitialDeposit.sendKeys(Deposit);
		this.Submit.click();
		
		String ConfirmationPageTitle = this.driver.getTitle();
		Reporter.log(ConfirmationPageTitle, true);
		
		AccountID = this.driver.findElement(By.xpath("//*[@id=\"account\"]/tbody/tr[4]/td[2]")).getText();	
		
		for(WebElement tdata:driver.findElements(By.tagName("tr")))
        {
			System.out.println(" | " + tdata.getText() + " | ");			 
		} 		
	}
}

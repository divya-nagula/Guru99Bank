package BankManager_PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class P08_Deposit {
	
	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	
	@FindBy(name = "accountno")
	WebElement AccountNo;
	
	@FindBy(name = "ammount")
	WebElement Amount;
	
	@FindBy(name = "desc")
	WebElement Description;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(name = "deposit")
	WebElement table;
	
	public P08_Deposit(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.Deposit.click();
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void Deposit(String AccountID, String Amount, String Description){
		this.AccountNo.sendKeys(AccountID);
		this.Amount.sendKeys(Amount);
		this.Description.sendKeys(Description);
		this.Submit.click();
		
		String ConfirmationPageTitle = this.driver.getTitle();
		Reporter.log(ConfirmationPageTitle, true);
		
				
		for(WebElement tdata:driver.findElements(By.tagName("tr")))
        {
			 System.out.println(" | " + tdata.getText() + " | ");
		}
		
		Reporter.log(this.driver.getTitle(), true);
	}
}

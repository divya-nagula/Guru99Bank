package BankManager_PageFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class P10_FundTransfer {
	
	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	public static String AlertTitle;
	@FindBy(name = "payersaccount")
	WebElement PayersAccountNo;
	
	@FindBy(name = "payeeaccount")
	WebElement PayeesAccountNo;
	
	@FindBy(name = "ammount")
	WebElement Amount;
	
	@FindBy(name = "desc")
	WebElement Description;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(xpath = "/html/body/table/tbody/tr[2]/td/table")
	WebElement table;
	
	public P10_FundTransfer(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.FundTransfer.click();
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void FundTransfer(String PayersAccountID, String PayeesAccountID, String Amount, String Description){
		this.PayersAccountNo.sendKeys(PayersAccountID);
		this.PayeesAccountNo.sendKeys(PayeesAccountID);
		this.Amount.sendKeys(Amount);
		this.Description.sendKeys(Description);
		this.Submit.click();
		
		
		try {
			Alert alt = driver.switchTo().alert();
			AlertTitle = alt.getText(); //get Content of the alter message
			alt.accept();
			//Compare error text with expected error value
			Reporter.log(AlertTitle, true);
			
		 }
		catch(NoAlertPresentException Ex) {
		String ConfirmationPageTitle = this.driver.getTitle();
		Reporter.log(ConfirmationPageTitle, true);
		
		Reporter.log(driver.findElement(By.xpath("/html/body/table/tbody/tr[1]/td/p")).getText(), true);	
		
		for(WebElement tdata:driver.findElements(By.tagName("tr")))
        {
			System.out.println(" | " + tdata.getText() + " | ");
		}
		}	
		Reporter.log(this.driver.getTitle(), true);
	}
}

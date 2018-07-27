package BankManager_PageFactory;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class P12_BalanceEnquiry {
	
	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	
	@FindBy(name = "accountno")
	WebElement AccountNo;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(name = "balenquiry")
	WebElement table;
	
	public P12_BalanceEnquiry(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.BalanceEnquiry.click();
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void BalanceEnquiry(String AccountID){
		this.AccountNo.sendKeys(AccountID);
		this.Submit.click();
		
		try {
			Alert alt = driver.switchTo().alert();
			String NoAccountExist = alt.getText(); //get Content of the alter message
			alt.accept();
			//Compare error text with expected error value
			Reporter.log(NoAccountExist, true);
			assertEquals(NoAccountExist, "Account does not exist");
	  }		catch(NoAlertPresentException Ex) {
			
		String ConfirmationPageTitle = this.driver.getTitle();
		Reporter.log(ConfirmationPageTitle, true);
		
		for(WebElement tdata:driver.findElements(By.tagName("tr")))
        {
			System.out.println(" | " + tdata.getText() + " | ");			 
		}
		}
		Reporter.log(this.driver.getTitle(), true);
	}
}

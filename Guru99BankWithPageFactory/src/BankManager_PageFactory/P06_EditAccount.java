
package BankManager_PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class P06_EditAccount {

	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	
	@FindBy(name = "accountno")
	WebElement AccountNo;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(name = "txtcid")
	WebElement CustomerID;
	
	@FindBy(name = "a_type")
	WebElement AccountType;
	
	@FindBy(name = "txtinitdep")
	WebElement Balance;
	
	@FindBy(name = "AccSubmit")
	WebElement AccSubmit;
	
	@FindBy(name = "AccReset")
	WebElement AccReset;
	
	@FindBy(name = "accmsg")
	WebElement table;
	
	public P06_EditAccount(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.EditAccount.click();
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void Edit_Account(String AccountID, String AccType) {
		this.AccountNo.sendKeys(AccountID);
		this.Submit.click();
		Select AcType = new Select(this.AccountType);
		AcType.selectByVisibleText(AccType);
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

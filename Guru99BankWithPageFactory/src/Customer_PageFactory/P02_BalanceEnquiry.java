package Customer_PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class P02_BalanceEnquiry {
	
	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	
	@FindBy(name = "accountno")
	WebElement AccountNo;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td/table")
	WebElement table;
	
	public P02_BalanceEnquiry(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.BalanceEnquiry.click();
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void BalanceEnquiry(String AccountID){
		Select AcNo = new Select(this.AccountNo);
		AcNo.selectByVisibleText(AccountID);
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

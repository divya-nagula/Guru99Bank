package Customer_PageFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class P06_CustomisedStatement {
	
	WebDriver driver;
	public static String PageTitle, WarningTitle;
	P01_HomePage objHomePage;
	
	@FindBy(name = "accountno")
	WebElement AccountNo;
	
	@FindBy(name = "fdate")
	WebElement FromDate;
	
	@FindBy(name = "tdate")
	WebElement ToDate;
	
	@FindBy(name = "amountlowerlimit")
	WebElement MinTransactionValue;
	
	@FindBy(name = "numtransaction")
	WebElement NoOfTransaction;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(xpath = "/html/body/table")
	WebElement table;
	
	public P06_CustomisedStatement(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.CustomisedStatement.click();
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void CustomisedStatement(String AccountID, String FDate, String TDate, String MTValue, String NofTransaction){
		this.AccountNo.sendKeys(AccountID);
		this.FromDate.sendKeys(FDate);
		this.ToDate.sendKeys(TDate);
		this.MinTransactionValue.sendKeys(MTValue);
		this.NoOfTransaction.sendKeys(NofTransaction);
		this.Submit.click();
		
		try {
			Alert alt = driver.switchTo().alert();
			WarningTitle = alt.getText(); //get Content of the alter message
			alt.accept();
			//Compare error text with expected error value
			Reporter.log(WarningTitle, true);
			
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


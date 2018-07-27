package Customer_PageFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class P05_MiniStatement {
	
	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	public static String WarningTitle;
	@FindBy(name = "accountno")
	WebElement AccountNo;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(xpath = "/html/body/table")
	WebElement table;
	
	public P05_MiniStatement(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.MiniStatement.click();
		
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void MiniStatement(String AccountID){
		this.AccountNo.sendKeys(AccountID);
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



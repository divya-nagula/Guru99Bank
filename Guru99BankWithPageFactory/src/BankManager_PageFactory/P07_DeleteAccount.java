package BankManager_PageFactory;

import static org.junit.Assert.assertEquals;


import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import TestData.*;

public class P07_DeleteAccount {

	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	
	@FindBy(name = "accountno")
	WebElement AccountNo;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	public P07_DeleteAccount(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.DeleteAccount.click();
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void DeleteAccount(String AccountID) throws Exception {
		this.AccountNo.sendKeys(AccountID);
		this.Submit.click();
		
		try {
			Alert alt = driver.switchTo().alert();
			String WarningTitle = alt.getText(); //get Content of the alter message
			alt.accept();
			//Compare error text with expected error value
			Reporter.log(WarningTitle, true);
			assertEquals(WarningTitle, Alerts.AccountDeleteWarning);
			
			try {
				Alert alt2 = driver.switchTo().alert();
				String DelConfirmationTitle = alt2.getText(); //get Content of the alter message
				alt2.accept();
				//Compare error text with expected error value
				Reporter.log(DelConfirmationTitle, true);
				assertEquals(DelConfirmationTitle, Alerts.AccountDeletionSuccess);
				
			} catch(NoAlertPresentException Ex) {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Code to save screenshot at desired location
				FileUtils.copyFile(scrFile, new File(TestData.Screenshots + "\\AccountDelete1.png"));
			}
						
		 }catch(NoAlertPresentException Ex) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Code to save screenshot at desired location
			FileUtils.copyFile(scrFile, new File(TestData.Screenshots + "\\AccountDelete2.png"));
		}
		
		Reporter.log(this.driver.getTitle(), true); 
	}
}
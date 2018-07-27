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

public class P04_DeleteCustomer {

	WebDriver driver;
	public static String PageTitle;
	public static String DelConfirmationTitle;
	P01_HomePage objHomePage;
	@FindBy(name = "cusid")
	WebElement CustomerID;
	
	@FindBy(name = "AccSubmit")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	public P04_DeleteCustomer(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.DeleteCustomer.click();
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void Delete_Customer(String CustomerID) throws Exception {
		this.CustomerID.sendKeys(CustomerID);
		this.Submit.click();
		
		Alert altM = driver.switchTo().alert();
		String alertTitle = altM.getText();
		if(alertTitle == "Customer does not exist!!") {
			altM.accept();
		}
		else {
		try {
			Alert alt = driver.switchTo().alert();
			String WarningTitle = alt.getText(); //get Content of the alter message
			alt.accept();
			//Compare error text with expected error value
			Reporter.log(WarningTitle, true);
			assertEquals(WarningTitle, "Do you really want to delete this Customer?");
		}
		  catch(NoAlertPresentException Ex) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Code to save screenshot at desired location
			FileUtils.copyFile(scrFile, new File("J:\\Selenium Webdriver\\Guru99\\Guru99Bank\\Screenshots\\screenshot1.png"));
		}
		
		try {
			Alert alt2 = driver.switchTo().alert();
			DelConfirmationTitle = alt2.getText(); //get Content of the alter message
			alt2.accept();
			//Compare error text with expected error value
			Reporter.log(DelConfirmationTitle, true);
			
			
		} catch(NoAlertPresentException Ex) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Code to save screenshot at desired location
			FileUtils.copyFile(scrFile, new File("J:\\Selenium Webdriver\\Guru99\\Guru99Bank\\Screenshots\\screenshot1.png"));
			
		}
		
		}
		Reporter.log(this.driver.getTitle(), true); 
	}
}

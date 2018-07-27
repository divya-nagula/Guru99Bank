package BankManager_PageFactory;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import TestData.*;

public class P15_Logout {

	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
		
	public P15_Logout(WebDriver driver) throws Exception{
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.Logout.click();
			
		try {
			Alert alt2 = driver.switchTo().alert();
			String LogoutTitle = alt2.getText(); //get Content of the alter message
			alt2.accept();
			//Compare error text with expected error value
			Reporter.log(LogoutTitle, true);
			assertEquals(LogoutTitle, Alerts.Logout);
			
		} catch(NoAlertPresentException Ex) {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Code to save screenshot at desired location
			FileUtils.copyFile(scrFile, new File(TestData.Screenshots + "\\Logout.png"));
		}
		
		Reporter.log(this.driver.getTitle(), true); 
	}
	
	
}

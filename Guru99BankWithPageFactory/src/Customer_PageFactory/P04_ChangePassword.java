package Customer_PageFactory;

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

public class P04_ChangePassword {
	WebDriver driver;
	P01_HomePage objHomePage;
	public static String PageTitle;
	public static String alertTitle;
	@FindBy(name="oldpassword")
	WebElement OldPassword;
	
	@FindBy(name="newpassword")
	WebElement NewPassword;
	
	@FindBy(name="confirmpassword")
	WebElement ConfirmPassword;
	
	@FindBy(name="sub")
	WebElement Submit;
	
	@FindBy(name="res")
	WebElement Reset;
	

	public P04_ChangePassword(WebDriver driver){
		//navigate to Change Password page
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.ChangePassword.click();
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
		
	}

	public void Change_Password(String oldpassword, String newpassword, String confirmpassword) throws Exception{
				
		this.OldPassword.sendKeys(oldpassword);
		this.NewPassword.sendKeys(newpassword);
		this.ConfirmPassword.sendKeys(confirmpassword);
		this.Submit.click();
		
		try {
			Alert alt = driver.switchTo().alert();
			alertTitle = alt.getText(); //get Content of the alter message
			alt.accept();
			Reporter.log(alertTitle,true);
			//Compare error text with expected error value
						
			}
			
			catch (NoAlertPresentException Ex) {
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Code to save screenshot at desired location
				FileUtils.copyFile(scrFile, new File(TestData.Screenshots + "\\CChangePassword.png"));
			}
		Reporter.log(this.driver.getTitle(), true); 
	}

}

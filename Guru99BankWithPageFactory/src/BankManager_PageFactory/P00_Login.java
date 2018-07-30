package BankManager_PageFactory;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import TestData.Alerts;


public class P00_Login {

	WebDriver driver;
	public static String PageTitle; 
	public static String HomePageTitle;
	@FindBy(name="uid")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="btnLogin")
	WebElement login;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
	WebElement ManagerDetails;
	
	public P00_Login(WebDriver driver){
		this.driver = driver;
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * This POM method will be exposed in test case to login in the application
	 * @param strUserName
	 * @param strPasword
	 * @return
	 */
	
	public void loginToGuru99(String strUserName,String strPassword){
		//Fill user name
		try {
			this.username.sendKeys(strUserName);
			//Fill password
			this.password.sendKeys(strPassword);
			//Click Login button
			this.login.click();
			HomePageTitle = this.driver.getTitle();
			Reporter.log(HomePageTitle, true);
		}
		catch(UnhandledAlertException Ex) {
			
			Alert alt = driver.switchTo().alert();
			String InvalidCredentials = alt.getText(); //get Content of the alter message
			alt.accept();
			//Compare error text with expected error value
			Reporter.log(InvalidCredentials, true);
			assertEquals(InvalidCredentials, Alerts.InvalidLogin);
			
			driver.close();
		}
		
	}
	
}

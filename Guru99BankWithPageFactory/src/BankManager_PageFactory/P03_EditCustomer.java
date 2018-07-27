
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

import TestData.*;

public class P03_EditCustomer {

	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	@FindBy(name = "cusid")
	WebElement CustomerID;
	
	@FindBy(name = "AccSubmit")
	WebElement AccSubmit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(name = "addr")
	WebElement Address;
	
	@FindBy(name = "sub")
	WebElement Submit;
	
	@FindBy(name = "customer")
	WebElement table;
	
	public P03_EditCustomer(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.EditCustomer.click();
		
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void Edit_Customer(String custID, String Address) {
		this.CustomerID.sendKeys(custID);
		this.AccSubmit.click();
			
		try {
			Alert alt = driver.switchTo().alert();
			String NoCustomerExist = alt.getText(); //get Content of the alter message
			alt.accept();
			//Compare error text with expected error value
			Reporter.log(NoCustomerExist, true);
			assertEquals(NoCustomerExist, Alerts.NoCustomerExist);
			
	  }	catch(NoAlertPresentException Ex) {
		  
		
		this.Address.clear();
		this.Address.sendKeys(Address);
		this.Submit.click();
		
		String ConfirmationPageTitle = this.driver.getTitle();
		Reporter.log(ConfirmationPageTitle, true);

		for(WebElement tdata:driver.findElements(By.tagName("tr")))
        {
			System.out.println(" | " + tdata.getText() + " | ");
		}
	  }
	}
}	

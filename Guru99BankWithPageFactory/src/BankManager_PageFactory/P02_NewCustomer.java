package BankManager_PageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class P02_NewCustomer {

	WebDriver driver;
	public static String PageTitle;
	P01_HomePage objHomePage;
	public static String CustomerID;
	
	@FindBy(name = "name")
	WebElement CustomerName;
	
	@FindBy(name = "rad1")
	List<WebElement> GenderRadio;
	
	@FindBy(name = "dob")
	WebElement DOB;
	
	@FindBy(name = "addr")
	WebElement Address;
	
	@FindBy(name = "city")
	WebElement City;
	
	@FindBy(name = "state")
	WebElement State;
	
	@FindBy(name = "pinno")
	WebElement PinNo;
	
	@FindBy(name = "telephoneno")
	WebElement MobileNumber;
	
	@FindBy(name = "emailid")
	WebElement EmailID;
	
	@FindBy(name = "password")
	WebElement Password;
	
	@FindBy(name = "sub")
	WebElement Submit;
	
	@FindBy(name = "res")
	WebElement Reset;
	
	@FindBy(name = "customer")
	WebElement table;
	
	@FindBy(xpath = "//*[@id=\"customer\"]/tbody/tr[14]/td/a")
	public static WebElement Continue;
	
	public P02_NewCustomer(WebDriver driver) {
		this.driver = driver;
		objHomePage = new P01_HomePage(driver);
		objHomePage.NewCustomer.click();
		PageTitle = driver.getTitle();
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void CreateCustomer(String EmailID) {
		this.CustomerName.sendKeys("Divya");
		boolean gValue = false;
		gValue = this.GenderRadio.get(0).isSelected();
		if(gValue == true) {
			this.GenderRadio.get(1).click();
		}else {
			this.GenderRadio.get(0).click();
		}
		this.DOB.sendKeys("09/07/1987");
		this.Address.sendKeys("Lothkunta");
		this.City.sendKeys("Hyderabad");
		this.State.sendKeys("Telangana");
		this.PinNo.sendKeys("500015");
		this.MobileNumber.sendKeys("9988776655");
		this.EmailID.sendKeys(EmailID);
		this.Password.sendKeys("Welcome@1");
		this.Submit.click();
		
		//Get Confirmation Message
		String ConfirmationPageTitle = this.driver.getTitle();
		Reporter.log(ConfirmationPageTitle, true);
		
		CustomerID = this.driver.findElement(By.xpath("//*[@id=\"customer\"]/tbody/tr[4]/td[2]")).getText();
				
		for(WebElement tdata:driver.findElements(By.tagName("tr")))
        {
		    System.out.println(" | " + tdata.getText() + " | ");
	}
}
}

package TestSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ManagerSignup {

	public static String[] credentials = new String[2];
	WebDriver driver;
	
	@FindBy(linkText = "here")
	WebElement Signuplink;
	
	@FindBy(name = "emailid")
	WebElement EmailID;
	
	@FindBy(name = "btnLogin")
	WebElement Submit;
	
	@FindBy(xpath = "/html/body/table/tbody/tr[4]/td[2]")
	WebElement UserID;
	
	@FindBy(xpath = "/html/body/table/tbody/tr[5]/td[2]")
	WebElement Password;
	
	
	public ManagerSignup(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String[] Signup(String emailid) {
				
	    this.Signuplink.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("emailid")));
		
		this.EmailID.sendKeys(emailid);
		this.Submit.click();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr[4]/td[2]")));
		
		credentials[0] = this.UserID.getText();
		credentials[1] = this.Password.getText();
	 
		Reporter.log(credentials[0]);
		Reporter.log(credentials[1]);
		
		return credentials;
	}
	
}

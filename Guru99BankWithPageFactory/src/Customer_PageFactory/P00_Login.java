package Customer_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;


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
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/marquee")
	WebElement WelcomeCustomer;
	
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
	
	public void loginToGuru99(String strUserName,String strPasword){
		//Fill user name
		this.username.sendKeys(strUserName);
		//Fill password
		this.password.sendKeys(strPasword);
		//Click Login button
		this.login.click();
		HomePageTitle = this.driver.getTitle();
		Reporter.log(HomePageTitle, true);
		Reporter.log(WelcomeCustomer.getText(), true);
				
	}
	
}

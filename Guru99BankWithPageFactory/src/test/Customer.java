package test;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BankManager_PageFactory.*;
import BankManager_PageFactory.P00_Login;
import Customer_PageFactory.*;

public class Customer {
	  WebDriver driver;
	  BankManager_PageFactory.P00_Login objManagerLogin;
	  Customer_PageFactory.P00_Login objCustomerLogin;
	  P15_Logout objManagerLogout;
	  P07_Logout objCustomerLogout;
	  String CustomerID, AccountID1, AccountID2;
	  
	  
	  @BeforeTest
	  public void setUp(){
			System.setProperty("webdriver.chrome.driver", "J:\\Selenium Webdriver\\Browser Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(); 
		  	
		  	//driver = new HtmlUnitDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://demo.guru99.com/V4/");
				  
			//Create Login Page object	
		  	 objManagerLogin = new BankManager_PageFactory.P00_Login(driver);
			//login to application
			
			objManagerLogin.loginToGuru99("mngr115115","Welcome@Guru99");
			// go the next page
		}

	  @Test
	  public void CreateCustomer_CreateAccounts() throws Exception {
		  
		  Reporter.log("\n" + "Test Result of CreateCustomer" + "\n" + "=============================================================================", true );
		  
			P02_NewCustomer objCustomer = new P02_NewCustomer(driver);
			
			objCustomer.CreateCustomer("bittu101@test.com");
			P02_NewCustomer.Continue.click();
			CustomerID = P02_NewCustomer.CustomerID;
			
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")));
			String Title = driver.getTitle();
			assertEquals(Title, P00_Login.HomePageTitle);
			
			P05_NewAccount objAccount1 = new P05_NewAccount(driver);
			objAccount1.CreateAccount(CustomerID, "Savings", "10000");
			P05_NewAccount.Continue.click();
			AccountID1 = P05_NewAccount.AccountID;
			
			
			P05_NewAccount objAccount2 = new P05_NewAccount(driver);
			objAccount2.CreateAccount(CustomerID, "Savings", "10000");
			P05_NewAccount.Continue.click();
			AccountID2 = P05_NewAccount.AccountID;
			
			objManagerLogout = new P15_Logout(driver);
						
  }
	  
	  @Test
	  public void SC01_to_SC03_ChangePassword() throws Exception{
		  Reporter.log("\n"+"Test Result of SC01_to_SC03_ChangePassword" + "\n" + "=============================================================================", true);
		    
		    objCustomerLogin = new Customer_PageFactory.P00_Login(driver);
			objCustomerLogin.loginToGuru99(CustomerID, "Welcome@1");
		    
			P04_ChangePassword objPassword = new P04_ChangePassword(driver);
			objPassword.Change_Password("invalid", "Welcome@1", "Welcome@1");
			assertEquals(P04_ChangePassword.alertTitle, "Old Password is incorrect");
				
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("oldpassword")));
			
			String finalTitle = driver.getTitle();
			assertEquals(finalTitle, P04_ChangePassword.PageTitle);
			
			objPassword.Change_Password("Welcome@1", "Welcome@1", "Welcome@1");
			assertEquals(P04_ChangePassword.alertTitle, "Password is Changed");	
			
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));
			
			String finalTitle2 = driver.getTitle();
			assertEquals(finalTitle2, P00_Login.PageTitle);
			
			objCustomerLogin.loginToGuru99(CustomerID, "Welcome@1");
	  }
	  
	  @Test
	  public void SC04_BalanceEnquiry() throws Exception{
		  Reporter.log("\n"+"Test Result of SC04_BalanceEnquiry" + "\n" + "=============================================================================", true);
		  
		  P02_BalanceEnquiry objBEnquiry1 = new P02_BalanceEnquiry(driver);
		  objBEnquiry1.BalanceEnquiry(AccountID1);
		  
		  P02_BalanceEnquiry objBEnquiry2 = new P02_BalanceEnquiry(driver);
		  objBEnquiry2.BalanceEnquiry(AccountID2);
		  
		  }
	  
	  @Test
	  public void SC07_MiniStatement() throws Exception{
		  Reporter.log("\n"+"Test Result of SC07_MiniStatement" + "\n" + "=============================================================================", true);
		  
		  P05_MiniStatement objMStatement = new P05_MiniStatement(driver);
		  objMStatement.MiniStatement(AccountID1);
	  }
	  
	  @Test
	  public void SC08_to_SC13_FundTransfer_CustomisedStatement_BalanceEnquiry() throws Exception{
		  Reporter.log("\n"+"Test Result of SC08_to_SC13_FundTransfer_CustomisedStatement_BalanceEnquiry" + "\n" + "=============================================================================", true);
		  
		  P03_FundTransfer objFTransfer = new P03_FundTransfer(driver);
		  objFTransfer.FundTransfer(AccountID1, AccountID2, "100", "Transferred");
		  
		  driver.navigate().refresh();
		  
		  assertEquals(driver.getTitle(), P03_FundTransfer.PageTitle);
		  
		  P06_CustomisedStatement objCStatement = new P06_CustomisedStatement(driver);
		  objCStatement.CustomisedStatement(AccountID1, "01/01/2018", "12/31/2018", "0", "100");
		  
		  P03_FundTransfer objFTransfer1 = new P03_FundTransfer(driver);
		  objFTransfer1.FundTransfer("37714", AccountID2, "100", "Transferred");
		  assertEquals(P03_FundTransfer.AlertTitle, "You are not authorize to Transfer Funds from this account!!");
		  
		  P03_FundTransfer objFTransfer2 = new P03_FundTransfer(driver);
		  objFTransfer2.FundTransfer("11111", AccountID2, "100", "Transferred");
		  assertEquals(P03_FundTransfer.AlertTitle, "Account 11111 does not exist!!!");
		  
		  P03_FundTransfer objFTransfer3 = new P03_FundTransfer(driver);
		  objFTransfer3.FundTransfer(AccountID1, AccountID1, "100", "Transferred");
		  assertEquals(P03_FundTransfer.AlertTitle, "Payers account No and Payees account No Must Not be Same!!!");
		  
	  }
	  
	  @Test
	  public void SC14_SC15_MiniStatement() throws Exception{
		  Reporter.log("\n"+"Test Result of SC14_SC15_MiniStatement" + "\n" + "=============================================================================", true);
		  
		  P05_MiniStatement objMStatement = new P05_MiniStatement(driver);
		  objMStatement.MiniStatement("37714");
		  assertEquals(P05_MiniStatement.WarningTitle, "You are not authorize to generate statement of this Account!!");
		  
		  P05_MiniStatement objMStatement1 = new P05_MiniStatement(driver);
		  objMStatement1.MiniStatement("11111");
		  assertEquals(P05_MiniStatement.WarningTitle, "Account does not exist");
		  		  
	  }
	  
	  @Test
	  public void SC16_to_SC18_CustomisedStatement() throws Exception{
		  Reporter.log("\n"+"Test Result of SC16_to_SC18_CustomisedStatement" + "\n" + "=============================================================================", true);
		  
		  P06_CustomisedStatement objCStatement = new P06_CustomisedStatement(driver);
		  objCStatement.CustomisedStatement("37714", "01/01/2018", "12/31/2018", "0", "100");
		  assertEquals(P06_CustomisedStatement.WarningTitle, "You are not authorize to generate statement of this Account!!");
		  
		  P06_CustomisedStatement objCStatement1 = new P06_CustomisedStatement(driver);
		  objCStatement1.CustomisedStatement("11111", "01/01/2018", "12/31/2018", "0", "100");
		  assertEquals(P06_CustomisedStatement.WarningTitle, "Account does not exist");
		  
		  P06_CustomisedStatement objCStatement2 = new P06_CustomisedStatement(driver);
		  objCStatement2.CustomisedStatement(AccountID1, "01/01/2019", "12/31/2018", "0", "100");
		  assertEquals(P06_CustomisedStatement.WarningTitle, "FromDate field should be lower than ToDate field!!");
		  
	  }
	  
	  @Test
	  public void SC19_Logout() throws Exception{
		  
		  objCustomerLogout = new P07_Logout(driver);
	  }
	  
	  @AfterTest
	  public void tearDown() throws Exception{
			
			driver.quit();
		}
}

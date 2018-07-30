/*
 * Before running this file, edit the test data in TestData.java
 */

/* This class tests the complete functionality 
 * of Guru99 Customer Login
*/

package TestSuite;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
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
import TestData.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;


//use above import for testing in Headless Browser

public class Customer {
	  WebDriver driver;
	  BankManager_PageFactory.P00_Login objManagerLogin;
	  Customer_PageFactory.P00_Login objCustomerLogin;
	  P15_Logout objManagerLogout;
	  P07_Logout objCustomerLogout;
	  String CustomerID, AccountID1, AccountID2;
	  ManagerSignup objSignup;
	  String emailID = TestData.memailID;
	  
	  
	  @BeforeTest
	  public void setUp(){
			System.setProperty("webdriver.chrome.driver", TestData.chromeDriverPath + "\\chromedriver.exe");
			driver = new ChromeDriver(); 
			
			//Use FireFox Browser(comment above two lines and uncomment below two lines)
			//System.setProperty("webdriver.firefox.marionette", TestData.geckoDriverPath + "\\geckodriver.exe");
			//driver = new FirefoxDriver();
		  	
		  	//driver = new HtmlUnitDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(TestData.testURL);
			
			objSignup = new ManagerSignup(driver);
			
			objSignup.Signup(emailID);
			TestData.mUsername = ManagerSignup.credentials[0];
			TestData.mCurrentPassword = ManagerSignup.credentials[1];
			
			driver.get(TestData.testURL);

			//Create Login Page object	
		  	 objManagerLogin = new BankManager_PageFactory.P00_Login(driver);
			//login to application
			
			objManagerLogin.loginToGuru99(TestData.mUsername,"invalid");
			// go the next page
		}

	  //Test Cases start here 
	  
	  @Test
	  public void CreateCustomer_CreateAccounts() throws Exception {
		  
		  Reporter.log("\n" + "Test Result of CreateCustomer" + "\n" + Alerts.testCaseDivider, true );
		  
			P02_NewCustomer objCustomer = new P02_NewCustomer(driver);
			
			objCustomer.CreateCustomer(TestData.Customer3);
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
		  Reporter.log("\n"+"Test Result of SC01_to_SC03_ChangePassword" + "\n" + Alerts.testCaseDivider, true);
		    
		    objCustomerLogin = new Customer_PageFactory.P00_Login(driver);
			objCustomerLogin.loginToGuru99(CustomerID, TestData.cCurrentPassword);
		    
			P04_ChangePassword objPassword = new P04_ChangePassword(driver);
			objPassword.Change_Password("invalid", TestData.cNewPassword, TestData.cNewPassword);
			assertEquals(P04_ChangePassword.alertTitle, "Old Password is incorrect");
				
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("oldpassword")));
			
			String finalTitle = driver.getTitle();
			assertEquals(finalTitle, P04_ChangePassword.PageTitle);
			
			objPassword.Change_Password(TestData.cCurrentPassword, TestData.cNewPassword, TestData.cNewPassword);
			assertEquals(P04_ChangePassword.alertTitle, "Password is Changed");	
			
			WebDriverWait wait2 = new WebDriverWait(driver, 20);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));
			
			String finalTitle2 = driver.getTitle();
			assertEquals(finalTitle2, P00_Login.PageTitle);
			
			objCustomerLogin.loginToGuru99(CustomerID, TestData.cNewPassword);
	  }
	  
	  @Test
	  public void SC04_BalanceEnquiry() throws Exception{
		  Reporter.log("\n"+"Test Result of SC04_BalanceEnquiry" + "\n" + Alerts.testCaseDivider, true);
		  
		  P02_BalanceEnquiry objBEnquiry1 = new P02_BalanceEnquiry(driver);
		  objBEnquiry1.BalanceEnquiry(AccountID1);
		  
		  P02_BalanceEnquiry objBEnquiry2 = new P02_BalanceEnquiry(driver);
		  objBEnquiry2.BalanceEnquiry(AccountID2);
		  
		  }
	  
	  @Test
	  public void SC07_MiniStatement() throws Exception{
		  Reporter.log("\n"+"Test Result of SC07_MiniStatement" + "\n" + Alerts.testCaseDivider, true);
		  
		  P05_MiniStatement objMStatement = new P05_MiniStatement(driver);
		  objMStatement.MiniStatement(AccountID1);
	  }
	  
	  @Test
	  public void SC08_to_SC13_FundTransfer_CustomisedStatement_BalanceEnquiry() throws Exception{
		  Reporter.log("\n"+"Test Result of SC08_to_SC13_FundTransfer_CustomisedStatement_BalanceEnquiry" + "\n" + Alerts.testCaseDivider, true);
		  
		  P03_FundTransfer objFTransfer = new P03_FundTransfer(driver);
		  objFTransfer.FundTransfer(AccountID1, AccountID2, "100", "Transferred");
		  
		  driver.navigate().refresh();
		  
		  assertEquals(driver.getTitle(), P03_FundTransfer.PageTitle);
		  
		  P06_CustomisedStatement objCStatement = new P06_CustomisedStatement(driver);
		  objCStatement.CustomisedStatement(AccountID1, "01/01/2018", "12/31/2018", "0", "100");
		  
		  P03_FundTransfer unauthorizedAccountFundTransfer = new P03_FundTransfer(driver);
		  unauthorizedAccountFundTransfer.FundTransfer("45980", AccountID2, "100", "Transferred");
		  assertEquals(P03_FundTransfer.AlertTitle, Alerts.unauthorizedAccountFundTransfer);
		  
		  P03_FundTransfer invalidAccountFundTransfer = new P03_FundTransfer(driver);
		  invalidAccountFundTransfer.FundTransfer("11111", AccountID2, "100", "Transferred");
		  assertEquals(P03_FundTransfer.AlertTitle, Alerts.invalidAccountFundTransfer);
		  
		  P03_FundTransfer sameAccountFundTransfer = new P03_FundTransfer(driver);
		  sameAccountFundTransfer.FundTransfer(AccountID1, AccountID1, "100", "Transferred");
		  assertEquals(P03_FundTransfer.AlertTitle, Alerts.sameAccountFundTransfer);
		  
	  }
	  
	  @Test
	  public void SC14_SC15_MiniStatement() throws Exception{
		  Reporter.log("\n"+"Test Result of SC14_SC15_MiniStatement" + "\n" + Alerts.testCaseDivider, true);
		  
		  P05_MiniStatement unauthorizedAccountMStatement = new P05_MiniStatement(driver);
		  unauthorizedAccountMStatement.MiniStatement("45980");
		  assertEquals(P05_MiniStatement.WarningTitle, Alerts.unauthorizedAccountMStatement);
		  
		  P05_MiniStatement invalidAccountMStatement = new P05_MiniStatement(driver);
		  invalidAccountMStatement.MiniStatement("11111");
		  assertEquals(P05_MiniStatement.WarningTitle, Alerts.invalidAccountMStatement);
		  		  
	  }
	  
	  @Test
	  public void SC16_to_SC18_CustomisedStatement() throws Exception{
		  Reporter.log("\n"+"Test Result of SC16_to_SC18_CustomisedStatement" + "\n" + Alerts.testCaseDivider, true);
		  
		  P06_CustomisedStatement unAuthorizedAccountStatement = new P06_CustomisedStatement(driver);
		  unAuthorizedAccountStatement.CustomisedStatement("45980", "01/01/2018", "12/31/2018", "0", "100");
		  assertEquals(P06_CustomisedStatement.WarningTitle, Alerts.unAuthorizedAccountStatement);
		  
		  P06_CustomisedStatement invalidAccountStatement = new P06_CustomisedStatement(driver);
		  invalidAccountStatement.CustomisedStatement("11111", "01/01/2018", "12/31/2018", "0", "100");
		  assertEquals(P06_CustomisedStatement.WarningTitle, Alerts.invalidAccountStatement);
		  
		  P06_CustomisedStatement invalidDateStatement = new P06_CustomisedStatement(driver);
		  invalidDateStatement.CustomisedStatement(AccountID1, "01/01/2019", "12/31/2018", "0", "100");
		  assertEquals(P06_CustomisedStatement.WarningTitle, Alerts.invalidDateStatement);
		  
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

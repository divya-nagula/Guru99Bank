/*
 * Before running this file, edit the test data in TestData.java
 */

/* This class tests the complete functionality 
 * of Guru99 Bank Manager Login
*/

package TestSuite;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BankManager_PageFactory.*;
import TestData.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;


//use above import for testing in Headless Browser

public class BankManager {
	
	  WebDriver driver;
	  P00_Login objLogin;
	  P15_Logout objLogout;
	  String CustomerID1, CustomerID2, AccountID1, AccountID2;
	  
	  @BeforeTest
	  public void setUp(){
		    //Use Chrome Browser 
			System.setProperty("webdriver.chrome.driver", TestData.chromeDriverPath + "\\chromedriver.exe");
			driver = new ChromeDriver(); 
		  	
			//Use FireFox Browser(comment above two lines and uncomment below two lines)
			//System.setProperty("webdriver.firefox.marionette", TestData.geckoDriverPath + "\\geckodriver.exe");
			//driver = new FirefoxDriver();
			 
		  	//driver = new HtmlUnitDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(TestData.testURL);
				  
			//Create Login Page object	
		  	 objLogin = new P00_Login(driver);
			//login to application
			
			objLogin.loginToGuru99(TestData.mUsername,TestData.mCurrentPassword);
			// go the next page
		}
	  
	  //Test Cases start here 
	  
	  @Test
	  public void SM01_to_SM03_ChangePassword() throws Exception {
		Reporter.log("\n"+"Test Result of SM01_to_SM03_ChangePassword" + "\n" + Alerts.testCaseDivider, true);
		  
		P11_ChangePassword objPassword = new P11_ChangePassword(driver);
		
		objPassword.Change_Password("invalid", TestData.mNewPassword, TestData.mNewPassword);
		assertEquals(P11_ChangePassword.alertTitle, Alerts.incorrectOldPassword);
			
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("oldpassword")));
		
		String finalTitle = driver.getTitle();
		assertEquals(finalTitle, P11_ChangePassword.PageTitle);
		
		objPassword.Change_Password(TestData.mCurrentPassword, TestData.mNewPassword, TestData.mNewPassword);
		assertEquals(P11_ChangePassword.alertTitle, Alerts.passwordChangeSuccessful);
		
		TestData.cCurrentPassword = TestData.cNewPassword;
		
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));
		
		String finalTitle2 = driver.getTitle();
		assertEquals(finalTitle2, P00_Login.PageTitle);
		
		objLogin.loginToGuru99(TestData.mUsername, TestData.mNewPassword);
		
	  }
	  
	  @Test
	  public void SM04_CreateCustomer() throws Exception {
		Reporter.log("\n" + "Test Result of SM04_CreateCustomer" + "\n" + Alerts.testCaseDivider, true );
		  
		P02_NewCustomer objCustomer1 = new P02_NewCustomer(driver);
		
		objCustomer1.CreateCustomer(TestData.Customer1);
		P02_NewCustomer.Continue.click();
		CustomerID1 = P02_NewCustomer.CustomerID;
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")));
		String Title = driver.getTitle();
		assertEquals(Title, P00_Login.HomePageTitle);
		
		P02_NewCustomer objCustomer2 = new P02_NewCustomer(driver);
		objCustomer2.CreateCustomer(TestData.Customer2);
		P02_NewCustomer.Continue.click();
		CustomerID2 = P02_NewCustomer.CustomerID;
		
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")));
		String Title2 = driver.getTitle();
		assertEquals(Title2, P00_Login.HomePageTitle);
	
	  }
		
	  @Test
	  public void SM05_NewAccount() throws Exception{
		Reporter.log("\n" + "Test Result of SM05_NewAccount" + "\n" + Alerts.testCaseDivider, true );
		
		P05_NewAccount objAccount1 = new P05_NewAccount(driver);
		objAccount1.CreateAccount(CustomerID1, "Current", "1000");
		
		P05_NewAccount.Continue.click();
		AccountID1 = P05_NewAccount.AccountID;
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")));
		
		String Title = driver.getTitle();
		assertEquals(Title, P00_Login.HomePageTitle);
		
		P05_NewAccount objAccount2 = new P05_NewAccount(driver);
		objAccount2.CreateAccount(CustomerID2, "Current", "1000");
		
		P05_NewAccount.Continue.click();
		AccountID2 = P05_NewAccount.AccountID;
		
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")));
		
		String Title2 = driver.getTitle();
		assertEquals(Title2, P00_Login.HomePageTitle);
				
	}

	  @Test
	  public void SM06_to_SM10_DeleteAccount() throws Exception{
		  Reporter.log("\n" + "Test Result of SM06_to_SM10_DeleteAccount" + "\n" + Alerts.testCaseDivider, true );
		  
		  P07_DeleteAccount objAccount = new P07_DeleteAccount(driver);
		  objAccount.DeleteAccount(AccountID2);
		  
		  String Title = driver.getTitle();
		  assertEquals(Title, P00_Login.HomePageTitle);
		  
		  P12_BalanceEnquiry objBEnquiry = new P12_BalanceEnquiry(driver);
		  objBEnquiry.BalanceEnquiry(AccountID2);
		  
		  P13_MiniStatement objMStatement = new P13_MiniStatement(driver);
		  objMStatement.MiniStatement(AccountID2);
		  
		  P14_CustomisedStatement objCStatement = new P14_CustomisedStatement(driver);
		  objCStatement.CustomisedStatement(AccountID2, "01/01/2017", "31/12/2018", "0", "100");
		  assertEquals(P14_CustomisedStatement.WarningTitle, Alerts.AccountDoesntExist);
	  }
	  
	  @Test
	  public void SM11_SM12_DeleteCustomerWithAccounts() throws Exception{
		  Reporter.log("\n" + "Test Result of SM11_SM12_DeleteCustomerWithAccounts" + "\n" + Alerts.testCaseDivider, true );
		  
		    P04_DeleteCustomer objCustomer = new P04_DeleteCustomer(driver);
			objCustomer.Delete_Customer(CustomerID1);
		    
			assertEquals(P04_DeleteCustomer.DelConfirmationTitle, Alerts.DeleteCustomerWithAccounts);
			
		  }

	  @Test
	  public void SM13_to_SM15_DeleteCustomerWithoutAccounts() throws Exception{
		  Reporter.log("\n" + "Test Result of SM13_to_SM15_DeleteCustomerWithoutAccounts" + "\n" + Alerts.testCaseDivider, true );
		    		  
		    P04_DeleteCustomer objCustomer = new P04_DeleteCustomer(driver);
			objCustomer.Delete_Customer(CustomerID2);
			
			assertEquals(P04_DeleteCustomer.DelConfirmationTitle, Alerts.DeleteCustomerWithoutAccounts);
								
			P03_EditCustomer objECustomer = new P03_EditCustomer(driver);
			objECustomer.Edit_Customer(CustomerID2, "New Address");
	  }

	  @Test
	  public void SM16_to_SM22_FundsTransfer_CustomizedStatement() throws Exception{
		  Reporter.log("\n" + "Test Result of SM16_to_SM22_FundsTransfer_CustomizedStatement" + "\n" + Alerts.testCaseDivider, true );
		  
		  P10_FundTransfer objAccount = new P10_FundTransfer(driver);
		  objAccount.FundTransfer(AccountID1, "37714", "50", "Transfer");
		  
		  driver.navigate().refresh();
		  
		  assertEquals(driver.getTitle(), P10_FundTransfer.PageTitle);
		  
		  P14_CustomisedStatement objAccountStmt = new P14_CustomisedStatement(driver);
		  objAccountStmt.CustomisedStatement(AccountID1, "01/01/2017", "12/31/2018", "0", "100");
		  
		  P10_FundTransfer invalidAccount = new P10_FundTransfer(driver);
		  invalidAccount.FundTransfer("36456", "37714", "100", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, Alerts.FundTransferInvalidAccount);
		  
		  P10_FundTransfer unAuthorisedAccount = new P10_FundTransfer(driver);
		  unAuthorisedAccount.FundTransfer("37714", "37717", "100", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, Alerts.FundTransferUnauthorizedAccount);
		  
		  P10_FundTransfer sameAccount = new P10_FundTransfer(driver);
		  sameAccount.FundTransfer(AccountID1, AccountID1 , "100", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, Alerts.FundTransferSameAccount);
		  
		  P10_FundTransfer lowBalance = new P10_FundTransfer(driver);
		  lowBalance.FundTransfer(AccountID1, "37714" , "100000", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, Alerts.FundTransferLowBalance);
	  }

	  @Test
	  public void SM23_EditCustomer() throws Exception{
		  Reporter.log("\n" + "Test Result of SM23_EditCustomer" + "\n" + Alerts.testCaseDivider, true );
		  
		  P03_EditCustomer objECustomer = new P03_EditCustomer(driver);
		  objECustomer.Edit_Customer(CustomerID1, "New Address");
	  }

	  @Test
	  public void SM24_SM25_Deposit() throws Exception{
		  Reporter.log("\n" + "Test Result of SM24_SM25_Deposit" + "\n" + Alerts.testCaseDivider, true );
		  
		  P08_Deposit objAccount = new P08_Deposit(driver);
		  objAccount.Deposit(AccountID1, "1000", "Deposited");
		  
		  driver.navigate().refresh();
		  
		  assertEquals(driver.getTitle(), P08_Deposit.PageTitle);
	  }

	  @Test
	  public void SM27_to_SM30_Withdrawal_MiniStatement_Balance() throws Exception{
		  Reporter.log("\n" + "SM27_to_SM30_Withdrawal_MiniStatement_Balance" + "\n" + Alerts.testCaseDivider, true );
		  
		  P09_Withdrawal objAccount = new P09_Withdrawal(driver);
		  objAccount.Withdrawal(AccountID1, "1000", "Withdrawal Made");
		  
		  P09_Withdrawal objAccount2 = new P09_Withdrawal(driver);
		  objAccount2.Withdrawal(AccountID1, "1000000", "Withdrawal Made");
		  
		  P13_MiniStatement objMiniS = new P13_MiniStatement(driver);
		  objMiniS.MiniStatement(AccountID1);
		  
		  P12_BalanceEnquiry objBEnquiry = new P12_BalanceEnquiry(driver);
		  objBEnquiry.BalanceEnquiry(AccountID1);
		  		  
	  }

	  @Test
	  public void SM31_to_SM33_CustomisedStatement() throws Exception{
		  Reporter.log("\n" + "SM31_to_SM33_CustomisedStatement" + "\n" + Alerts.testCaseDivider, true );
		  
		  P14_CustomisedStatement objCStatement = new P14_CustomisedStatement(driver);
		  objCStatement.CustomisedStatement(AccountID1, "01/01/2017", "12/31/2018", "0", "100");
		  
		  P14_CustomisedStatement invalidDate = new P14_CustomisedStatement(driver);
		  invalidDate.CustomisedStatement(AccountID1, "01/01/2019", "12/31/2018", "0", "100");
		  assertEquals(P14_CustomisedStatement.WarningTitle, Alerts.CustomStatementInvalidDate);
		  
	 }
	  
	  @Test
	  public void SM34_EditAccount() throws Exception{
		  Reporter.log("\n" + "SM34_Edit Account" + "\n" + Alerts.testCaseDivider, true );
		  
		  P06_EditAccount objAccount = new P06_EditAccount(driver);
		  objAccount.Edit_Account(AccountID1, "Savings");
		  
	  }
	  
	  @Test
	  public void SM35_Logout() throws Exception{
		  objLogout = new P15_Logout(driver);
	  }
	  
	  @Test
	  public void SM36_LoginCustomer()  throws Exception{
		  
		 	objLogin = new P00_Login(driver);
			objLogin.loginToGuru99(CustomerID1,TestData.cCurrentPassword);
			
			String Title = driver.getTitle();
			Reporter.log(Title, true);
				
	  }
	  
	  @AfterTest
	  public void tearDown() throws Exception{
			
			driver.quit();
		}
}

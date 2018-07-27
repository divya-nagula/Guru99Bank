package test;

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

import BankManager_PageFactory.P00_Login;
import BankManager_PageFactory.P02_NewCustomer;
import BankManager_PageFactory.P03_EditCustomer;
import BankManager_PageFactory.P04_DeleteCustomer;
import BankManager_PageFactory.P05_NewAccount;
import BankManager_PageFactory.P06_EditAccount;
import BankManager_PageFactory.P07_DeleteAccount;
import BankManager_PageFactory.P08_Deposit;
import BankManager_PageFactory.P09_Withdrawal;
import BankManager_PageFactory.P10_FundTransfer;
import BankManager_PageFactory.P11_ChangePassword;
import BankManager_PageFactory.P12_BalanceEnquiry;
import BankManager_PageFactory.P13_MiniStatement;
import BankManager_PageFactory.P14_CustomisedStatement;
import BankManager_PageFactory.P15_Logout;

//import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BankManager {
	
	  WebDriver driver;
	  P00_Login objLogin;
	  P15_Logout objLogout;
	  String CustomerID1, CustomerID2, AccountID1, AccountID2;
	  
	  @BeforeTest
	  public void setUp(){
			System.setProperty("webdriver.chrome.driver", "J:\\Selenium Webdriver\\Browser Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(); 
		  	
		  	//driver = new HtmlUnitDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://demo.guru99.com/V4/");
				  
			//Create Login Page object	
		  	 objLogin = new P00_Login(driver);
			//login to application
			
			objLogin.loginToGuru99("mngr115115","Welcome@Guru99");
			// go the next page
		}
	  
	  @Test
	  public void SM01_to_SM03_ChangePassword() throws Exception {
		Reporter.log("\n"+"Test Result of SM01_to_SM03_ChangePassword" + "\n" + "=============================================================================", true);
		  
		P11_ChangePassword objPassword = new P11_ChangePassword(driver);
		
		objPassword.Change_Password("invalid", "Welcome@Guru99", "Welcome@Guru99");
		assertEquals(P11_ChangePassword.alertTitle, "Old Password is incorrect");
			
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("oldpassword")));
		
		String finalTitle = driver.getTitle();
		assertEquals(finalTitle, P11_ChangePassword.PageTitle);
		
		objPassword.Change_Password("Welcome@Guru99", "Welcome@Guru99", "Welcome@Guru99");
		assertEquals(P11_ChangePassword.alertTitle, "Password is Changed");	
		
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));
		
		String finalTitle2 = driver.getTitle();
		assertEquals(finalTitle2, P00_Login.PageTitle);
		
		objLogin.loginToGuru99("mngr115115", "Welcome@Guru99");
	  }
	  
	  @Test
	  public void SM04_CreateCustomer() throws Exception {
		Reporter.log("\n" + "Test Result of SM04_CreateCustomer" + "\n" + "=============================================================================", true );
		  
		P02_NewCustomer objCustomer1 = new P02_NewCustomer(driver);
		
		objCustomer1.CreateCustomer("bittu69@test.com");
		P02_NewCustomer.Continue.click();
		CustomerID1 = P02_NewCustomer.CustomerID;
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")));
		String Title = driver.getTitle();
		assertEquals(Title, P00_Login.HomePageTitle);
		
		P02_NewCustomer objCustomer2 = new P02_NewCustomer(driver);
		objCustomer2.CreateCustomer("bittu70@test.com");
		P02_NewCustomer.Continue.click();
		CustomerID2 = P02_NewCustomer.CustomerID;
		
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")));
		String Title2 = driver.getTitle();
		assertEquals(Title2, P00_Login.HomePageTitle);
	
	  }
		
	  @Test
	  public void SM05_NewAccount() throws Exception{
		Reporter.log("\n" + "Test Result of SM05_NewAccount" + "\n" + "=============================================================================", true );
		
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
		  Reporter.log("\n" + "Test Result of SM06_to_SM10_DeleteAccount" + "\n" + "=============================================================================", true );
		  
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
		  assertEquals(P14_CustomisedStatement.WarningTitle, "Account does not exist");
	  }
	  
	  @Test
	  public void SM11_SM12_DeleteCustomerWithAccounts() throws Exception{
		  Reporter.log("\n" + "Test Result of SM11_SM12_DeleteCustomerWithAccounts" + "\n" + "=============================================================================", true );
		  
		    P04_DeleteCustomer objCustomer = new P04_DeleteCustomer(driver);
			objCustomer.Delete_Customer(CustomerID1);
		    
			assertEquals(P04_DeleteCustomer.DelConfirmationTitle, "Customer could not be deleted!!. First delete all accounts of this customer then delete the customer");
			
		  }

	  @Test
	  public void SM13_to_SM15_DeleteCustomerWithoutAccounts() throws Exception{
		  Reporter.log("\n" + "Test Result of SM13_to_SM15_DeleteCustomerWithoutAccounts" + "\n" + "=============================================================================", true );
		    		  
		    P04_DeleteCustomer objCustomer = new P04_DeleteCustomer(driver);
			objCustomer.Delete_Customer(CustomerID2);
			
			assertEquals(P04_DeleteCustomer.DelConfirmationTitle, "Customer deleted Successfully");
								
			P03_EditCustomer objECustomer = new P03_EditCustomer(driver);
			objECustomer.Edit_Customer(CustomerID2, "New Address");
	  }

	  @Test
	  public void SM16_to_SM22_FundsTransfer_CustomizedStatement() throws Exception{
		  Reporter.log("\n" + "Test Result of SM16_to_SM22_FundsTransfer_CustomizedStatement" + "\n" + "=============================================================================", true );
		  
		  P10_FundTransfer objAccount = new P10_FundTransfer(driver);
		  objAccount.FundTransfer(AccountID1, "37714", "50", "Transfer");
		  
		  driver.navigate().refresh();
		  
		  assertEquals(driver.getTitle(), P10_FundTransfer.PageTitle);
		  
		  P14_CustomisedStatement objAccountStmt = new P14_CustomisedStatement(driver);
		  objAccountStmt.CustomisedStatement(AccountID1, "01/01/2017", "12/31/2018", "0", "100");
		  
		  P10_FundTransfer invalidAccount = new P10_FundTransfer(driver);
		  invalidAccount.FundTransfer("36456", "37714", "100", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, "Account 36456does not exist!!!");
		  
		  P10_FundTransfer unAuthorisedAccount = new P10_FundTransfer(driver);
		  unAuthorisedAccount.FundTransfer("37714", "37717", "100", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, "You are not authorize to Transfer Funds from this account!!");
		  
		  P10_FundTransfer sameAccount = new P10_FundTransfer(driver);
		  sameAccount.FundTransfer(AccountID1, AccountID1 , "100", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, "Payers account No and Payees account No Must Not be Same!!!");
		  
		  P10_FundTransfer lowBalance = new P10_FundTransfer(driver);
		  lowBalance.FundTransfer(AccountID1, "37714" , "100000", "Transfer");
		  assertEquals(P10_FundTransfer.AlertTitle, "Transfer Failed. Account Balance low!!");
	  }

	  @Test
	  public void SM23_EditCustomer() throws Exception{
		  Reporter.log("\n" + "Test Result of SM23_EditCustomer" + "\n" + "=============================================================================", true );
		  
		  P03_EditCustomer objECustomer = new P03_EditCustomer(driver);
		  objECustomer.Edit_Customer(CustomerID1, "New Address");
	  }

	  @Test
	  public void SM24_SM25_Deposit() throws Exception{
		  Reporter.log("\n" + "Test Result of SM24_SM25_Deposit" + "\n" + "=============================================================================", true );
		  
		  P08_Deposit objAccount = new P08_Deposit(driver);
		  objAccount.Deposit(AccountID1, "1000", "Deposited");
		  
		  driver.navigate().refresh();
		  
		  assertEquals(driver.getTitle(), P08_Deposit.PageTitle);
	  }

	  @Test
	  public void SM27_to_SM30_Withdrawal_MiniStatement_Balance() throws Exception{
		  Reporter.log("\n" + "SM27_to_SM30_Withdrawal_MiniStatement_Balance" + "\n" + "=============================================================================", true );
		  
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
		  Reporter.log("\n" + "SM31_to_SM33_CustomisedStatement" + "\n" + "=============================================================================", true );
		  
		  P14_CustomisedStatement objCStatement = new P14_CustomisedStatement(driver);
		  objCStatement.CustomisedStatement(AccountID1, "01/01/2017", "12/31/2018", "0", "100");
		  
		  P14_CustomisedStatement objCStatement1 = new P14_CustomisedStatement(driver);
		  objCStatement1.CustomisedStatement(AccountID1, "01/01/2019", "12/31/2018", "0", "100");
		  assertEquals(P14_CustomisedStatement.WarningTitle, "FromDate field should be lower than ToDate field!!");
		  
	 }
	  
	  @Test
	  public void SM34_EditAccount() throws Exception{
		  Reporter.log("\n" + "SM34_Edit Account" + "\n" + "=============================================================================", true );
		  
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
			objLogin.loginToGuru99(CustomerID1,"Welcome@1");
			
			String Title = driver.getTitle();
			Reporter.log(Title, true);
				
	  }
	  
	  @AfterTest
	  public void tearDown() throws Exception{
			
			driver.quit();
		}
}

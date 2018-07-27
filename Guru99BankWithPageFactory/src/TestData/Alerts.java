/*
 * This class to store the alert messages
 */

package TestData;

public class Alerts {
	
	//Test Case Divider
	public static String testCaseDivider = "=============================================================================";
	
	//Password Related Alert Messages
	public static final String incorrectOldPassword = "Old Password is incorrect";
	public static final String passwordChangeSuccessful = "Password is Changed";
	
	//Manager Account Related Alert Messages
	public static final String AccountDoesntExist = "Account does not exist"; 
	public static final String DeleteCustomerWithAccounts = "Customer could not be deleted!!. First delete all accounts of this customer then delete the customer";
	public static final String DeleteCustomerWithoutAccounts = "Customer deleted Successfully";
	public static final String FundTransferInvalidAccount = "Account 36456does not exist!!!";
	public static final String FundTransferUnauthorizedAccount = "You are not authorize to Transfer Funds from this account!!";
	public static final String FundTransferSameAccount = "Payers account No and Payees account No Must Not be Same!!!";
	public static final String FundTransferLowBalance = "Transfer Failed. Account Balance low!!";	
	public static final String CustomStatementInvalidDate = "FromDate field should be lower than ToDate field!!";

	//Customer Account Related Alert Messages
	public static final String unAuthorizedAccountStatement = "You are not authorize to generate statement of this Account!!";
	public static final String invalidAccountStatement = "Account does not exist";
	public static final String invalidDateStatement = "FromDate field should be lower than ToDate field!!";
	public static final String unauthorizedAccountFundTransfer = "You are not authorize to Transfer Funds from this account!!";
	public static final String invalidAccountFundTransfer = "Account 11111 does not exist!!!";
	public static final String sameAccountFundTransfer = "Payers account No and Payees account No Must Not be Same!!!";
	public static final String unauthorizedAccountMStatement = "You are not authorize to generate statement of this Account!!";
	public static final String invalidAccountMStatement = "Account does not exist";
}

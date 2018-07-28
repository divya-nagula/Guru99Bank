/* This Java class stores 
 * all the test data
 */

package TestData;

import TestSuite.*;

public class TestData {

	//Test URL
	public static String testURL = "http://demo.guru99.com/V4/";
	
	//Screenshot Save Path
	
	public static String Screenshots = "D:\\Divya\\SeleniumWebDriver\\Screenshots";
	
	//Email ID for manager access generation
	
	public static String memailID = "divya.nagula@gmail.com";
	
	//Login IDs
	public static String mUsername = ManagerSignup.credentials[0];
	public static String mCurrentPassword = ManagerSignup.credentials[1];
	public static String mNewPassword = "Welcome@Guru99";
	public static String Customer1 = "test901@test.com";
	public static String Customer2 = "test902@test.com";
	public static String Customer3 = "test907@test.com";
	public static String cCurrentPassword = "Welcome@1";
	public static String cNewPassword = "Welcome@1";	
	
	//Driver Paths - provide Folder path for the driver(Note: don't put the file name i.e., chromedriver.exe or geckodriver.exe
	public static String chromeDriverPath = "D:\\Divya\\SeleniumWebDriver\\chromedriver_win32_1";
	public static String geckoDriverPath = "D:\\Divya\\SeleniumWebDriver\\geckodriver-v0.19.1-win64";
	
	//New Customer Data, need not be unique, so edit if you want to change!
	
	public static String DOB = "11/11/2000";
	public static String Address = "Kondapaka";
	public static String City = "Hyderabad";
	public static String State = "Telangana";
	public static String PinNo = "100001";
	public static String MobileNo = "9878685848";
	
	
	
	
}

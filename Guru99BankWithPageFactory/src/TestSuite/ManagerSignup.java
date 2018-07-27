package TestSuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestData.*;

public class ManagerSignup {

	public static String UserID = null;
	public static String Password = null;
	WebDriver driver;
	
	public void SignUp(){
		//Use Chrome Browser 
		System.setProperty("webdriver.chrome.driver", TestData.chromeDriverPath + "\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Use FireFox Browser(comment above two lines and uncomment below two lines)
		//System.setProperty("webdriver.firefox.marionette", TestData.geckoDriverPath + "\\geckodriver.exe");
		//driver = new FirefoxDriver();
		 
	  	//driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(TestData.testURL);
		
		driver.findElement(By.xpath("/html/body/div[6]/ol/li[1]/a")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("emailid")));
		
		WebElement emailid = driver.findElement(By.name("emailid"));
		emailid.sendKeys(TestData.memailID);
		
		driver.findElement(By.name("btnLogin")).click();
	
		UserID = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[2]")).getText();
		Password = driver.findElement(By.xpath("/html/body/table/tbody/tr[5]/td[2]")).getText();
		
	}
}

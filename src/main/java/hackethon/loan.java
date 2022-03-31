package hackethon;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class loan
{
	public static WebDriver driver;//create webdriver object
	public static String baseUrl3= "https://emicalculator.net/loan-calculator/"; //url to open the web page
	
	
	@BeforeClass
	public static void openUrl() 
	//opens the url
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl3);
	}
	
	@Test(priority =1)
	public static void maximize() {
		//maximize the window
		driver.manage().window().maximize();
	}

	@AfterClass
	public static void close() {
		//close the web page
		driver.close();
	}
	
	@Test(priority=2)
	public static void enterValues() throws InterruptedException
	{
		//send the required values
		String beforeamt = driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[1]/div[2]/div")).getAttribute("style");
		System.out.println("Before Amount selected is "+ beforeamt);
		driver.findElement(By.xpath("//input[@id='loanamount']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "500000");
		String afteramt = driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[1]/div[2]/div")).getAttribute("style");
		System.out.println("After Amount selected is "+afteramt);
		if(beforeamt.equals(afteramt))
		{
			System.out.println("Amount on the slider is same");
		}
		else
		{
			System.out.println("Amount on the slider is different");
		}
		String beforeInterest =driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[3]/div[2]/div")).getAttribute("style");
		System.out.println("Before Interest selected is "+ beforeInterest);
		driver.findElement(By.xpath("//input[@id='loaninterest']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "5");
		String afterInterest =driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[3]/div[2]/div")).getAttribute("style");
		System.out.println("After Interest selected is "+ afterInterest);
		if(beforeInterest.equals(afterInterest))
		{
			System.out.println("Interest on the slider is same");
		}
		else
		{
			System.out.println("Interest on the slider is different");
		}
		String beforeLoanTerm=driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[4]/div[2]/div")).getAttribute("style");
		System.out.println("Before Loan term selected is "+ beforeLoanTerm);
		driver.findElement(By.xpath("//input[@id='loanterm']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "7");
		String afterLoanTerm=driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[4]/div[2]/div")).getAttribute("style");
		System.out.println("After Loan term selected is "+ afterLoanTerm);
		if(beforeLoanTerm.equals(afterLoanTerm))
		{
			System.out.println("Loan Term on the slider is same");
		}
		else
		{
			System.out.println("Loan Term on the slider is different");
		}
		String beforeLoanFees=driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[5]/div[2]/div")).getAttribute("style");
		System.out.println("Before Loan fees selected is "+ beforeLoanFees);
		driver.findElement(By.xpath("//input[@id='loanfees']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "20000");
		String afterLoanFees=driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[5]/div[2]/div")).getAttribute("style");
		System.out.println("After Loan fees selected is "+ afterLoanFees);
		if(beforeLoanTerm.equals(afterLoanFees))
		{
			System.out.println("Loan Fees on the slider is same");
		}
		else
		{
			System.out.println("Loan Fees on the slider is different");
		}
		
		driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[4]/div[1]/div/div/div/div/div/label[1]")).click();
		
		
		
		
	}
}

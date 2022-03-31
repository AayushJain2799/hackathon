package hackethon;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class homeLoanCalculator 
{
	
	public static WebDriver driver;
	public static String baseUrl2= "https://emicalculator.net/home-loan-emi-calculator/"; //stores url of the web page
	
	public static void openUrl() {
		//opens the url
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl2);
	}
	
	public static void maximize() {
		//maximize the window
		driver.manage().window().maximize();
	}
	
	public static void ScrollUp()
	{
		//scrolls up the page
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1700);");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		openUrl();
		ScrollUp();
		maximize();
		String path="Data_HomeLoan.xlsx";  //path to excel file
		XLUtility xlutil = new XLUtility(path); //implements utility class having required methods
		//set cell data in excel file
		xlutil.setCellData("Sheet1", 0, 0, "Year" );
		xlutil.setCellData("Sheet1", 0, 1, "Principal");
		xlutil.setCellData("Sheet1", 0, 2, "Interest");
		xlutil.setCellData("Sheet1", 0, 3, "Taxes");
		xlutil.setCellData("Sheet1", 0, 4, "Total Payment");
		xlutil.setCellData("Sheet1", 0, 5, "Balance");
		xlutil.setCellData("Sheet1", 0, 6, "Loan paid to date");
		
		WebElement table =driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[8]/table/tbody"));
		int rows=table.findElements(By.xpath("tr")).size();
		System.out.println(rows);
		String year,principal,interest,taxes,totalBal,payment,loanPaidToDate;
		for(int r=2;r<=rows;r=r+2)
		{
			//get required data from web table
				year =table.findElement(By.xpath("tr["+r+"]/td[1]")).getText();
				principal =table.findElement(By.xpath("tr["+r+"]/td[2]")).getText();
				interest =table.findElement(By.xpath("tr["+r+"]/td[3]")).getText();
				taxes =table.findElement(By.xpath("tr["+r+"]/td[4]")).getText();
				totalBal=table.findElement(By.xpath("tr["+r+"]/td[5]")).getText();
				payment=table.findElement(By.xpath("tr["+r+"]/td[6]")).getText();
				loanPaidToDate =table.findElement(By.xpath("tr["+r+"]/td[7]")).getText();
				
				//print the value on the console
				System.out.println(year);
				System.out.println(principal);
				System.out.println(interest);
				System.out.println(taxes);
				System.out.println(totalBal);
				System.out.println(payment);
				System.out.println(loanPaidToDate);
				
				
				//sets the values in the excel sheet
				xlutil.setCellData("Sheet1", r, 0, year);
				xlutil.setCellData("Sheet1", r, 1, principal);
				xlutil.setCellData("Sheet1", r, 2, interest);
				xlutil.setCellData("Sheet1", r, 3, taxes);
				xlutil.setCellData("Sheet1", r, 4, totalBal);
				xlutil.setCellData("Sheet1", r, 5, payment);
				xlutil.setCellData("Sheet1", r, 6, loanPaidToDate);
				
				
			
			}
			System.out.println("Successful");// prints successful when completed
			driver.close();
			//close the web page
	}
}



	
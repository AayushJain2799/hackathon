package hackethon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.Element;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Emi_Calc {

	public static WebDriver driver; 

	public static String baseUrl = "https://emicalculator.net/"; //Store address of web page
	

	@BeforeClass()
	public static void openUrl() 
	{
		
		//opens the url
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}
	
	@Test(priority = 2)
	public static void maximize() throws InterruptedException {
		
		//maximize the window
		driver.manage().window().maximize();
		Thread.sleep(4000);
	}

	@AfterClass
	public static void close() {
		//close the web page
		driver.close();
	}

	@Test(priority = 3)
	public static void select() {
		File file;
		FileInputStream fis;
		XSSFSheet sheet = null;
		XSSFWorkbook workbook;
		FileOutputStream op;
		try {
	
			file = new File("Data_Hackathon - Copy.xlsx"); //Excel spreadsheet
			fis = new FileInputStream(file);

			 workbook = new XSSFWorkbook(fis);
			 sheet = workbook.getSheet("Sheet1"); //takes to sheet 1
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//get the data from excel file
		String type = sheet.getRow(1).getCell(0).getStringCellValue(); 
		String amount = sheet.getRow(1).getCell(1).toString();
		String interest = sheet.getRow(1).getCell(2).toString();
		String tenure = sheet.getRow(1).getCell(3).toString();
		String time = sheet.getRow(1).getCell(4).getStringCellValue();

		//set the data in the web page
		if (type.equals("Home Loan")) {
			driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/ul/li[1]/a"))
					.click();
		} else if (type.equals("Personal Loan")) {
			driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/ul/li[2]/a"))
					.click();

		} else if (type.equals("Car Loan")) {
			driver.findElement(By.xpath("/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/ul/li[3]/a"))
					.click();
		}
		driver.findElement(By.xpath("//input[@id='loanamount']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), amount);
		driver.findElement(By.xpath("//input[@id='loaninterest']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), interest);
		driver.findElement(By.xpath("//input[@id='loanterm']")).sendKeys(Keys.chord(Keys.CONTROL, "a"), tenure);
		if (time.equals("Years")) {
			driver.findElement(By.xpath(
					"/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[7]/div/div/div/div/div/label[1]"))
					.click();
		} else if (time.equals("Months")) {
			driver.findElement(By.xpath(
					"/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/form/div/div[7]/div/div/div/div/div/label[2]"))
					.click();
		}
		
		
	}

	@Test(priority = 4)
	public static void emi() {
		// prints the required data on the console
		String loanEmi = driver
				.findElement(By.xpath(
						"/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/div/div[1]/div[1]/p/span"))
				.getText();
		System.out.println("Loan EMI = " + loanEmi);
		String totalInterestPayable = driver
				.findElement(By.xpath(
						"/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/div/div[1]/div[2]/p/span"))
				.getText();
		System.out.println("Total Interest payable = " + totalInterestPayable);
		String total = driver
				.findElement(By.xpath(
						"/html/body/div[1]/div/main/article/div[3]/div/div[1]/div[1]/div[2]/div/div[1]/div[3]/p/span"))
				.getText();
		System.out.println("Total payment = " + total);
	}
	
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
		
		
	



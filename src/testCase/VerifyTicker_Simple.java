package testCase;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import operation.ReadProjectConstants;



public class VerifyTicker_Simple {
	static String BASE_URL,BROWSER_TYPE,TICKER;
	public WebDriver driver;
	
	@BeforeTest
	public void OpenBaseURL() throws IOException{
		
		ReadProjectConstants object = new ReadProjectConstants();
        Properties allObjects =  object.getProjectConstants();		
		System.out.println(allObjects.getProperty("BASE_URL"));
		BASE_URL=allObjects.getProperty("BASE_URL");
		BROWSER_TYPE=allObjects.getProperty("BROWSER_TYPE");
		TICKER=allObjects.getProperty("TICKER");
		
		String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);		
		
		if(BROWSER_TYPE.equalsIgnoreCase("CHROME")){
			driver = new ChromeDriver();
			System.out.println("Running in Headful chrome");
			Reporter.log("Running in Headful mode");
		}else if (BROWSER_TYPE.equalsIgnoreCase("CHROME-HEADLESS")){
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");		    
			driver = new ChromeDriver(chromeOptions);
			System.out.println("Running in Headless chrome");
			Reporter.log("Running in Headless mode");
		}else{
			System.out.println("No Browser type found");
			Reporter.log("No Browser type found");
		}
		
		driver.get(BASE_URL);
	}
	
	@Test
	public void VerifyTickerPrice() throws InterruptedException {
		//Search for AAPL stock
		Reporter.log("Searching for Ticker: "+TICKER);
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(TICKER);
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);
		
		//Get Current price
		String currentpriceText = driver.findElement(By.xpath("//div[@class='gsrt']")).getText();
		String currentprice=currentpriceText.split(" ", 2)[0];
		System.out.println("Price: "+currentprice);
		double currentpricedbl = Double.parseDouble(currentprice);
		Reporter.log("Current Price: "+currentprice);
		System.out.println("Current price of stock: "+currentprice);
		
		//Get Higher price
		String highprice = driver.findElement(By.xpath("//td[text()='52-wk high']//following::td")).getText();
		double highpricedbl = Double.parseDouble(highprice);
		Reporter.log("52 Wk High Price: "+ highprice);
		System.out.println("Highest price of 52 wk: "+highpricedbl);
		
		//Get Lower price
		String lowprice = driver.findElement(By.xpath("//td[text()='52-wk low']//following::td")).getText();
		double lowpricedbl = Double.parseDouble(lowprice);
		Reporter.log("52 Wk Low Price: "+lowprice);
		System.out.println("Lowest price of 52 wk: "+lowpricedbl);
		
		//Verify the current price is withing the range
		Reporter.log("Verifing Range");
		if(currentpricedbl<=highpricedbl && currentpricedbl>=lowpricedbl )
		{
			System.out.println("Test case passed: Current price is within 52 week range");
			Reporter.log("Test case passed: Current price is within 52 week range");
		}
		else{
			Assert.fail("Test case failed: Current price is not within 52 week range");
			Reporter.log("Test case failed: Current price is not within 52 week range");
			System.out.println("Test case failed: Current price is not within 52 week range");
		}
	}
	
	@AfterClass
	public void endTest(){
		Reporter.log("End of Test");
		driver.quit();
	}
}

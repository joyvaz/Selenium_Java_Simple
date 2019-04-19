package testCase;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import operation.BrowserSetup;
import operation.ReadProjectConstants;
import operation.UI_Operations;

public class VerifyTicker_FunctionCall {
	static String BASE_URL, BROWSER_TYPE, TICKER;
	public WebDriver driver;
	BrowserSetup brSetup;
	UI_Operations uiOpps;
	ReadProjectConstants objConst= new ReadProjectConstants();
	
	@BeforeTest
	public void OpenBaseURL() throws IOException{
		//Get Base URL
		Properties allConst =  objConst.getProjectConstants();
		BASE_URL=allConst.getProperty("BASE_URL");
		BROWSER_TYPE = allConst.getProperty("BROWSER_TYPE");
		TICKER = allConst.getProperty("TICKER");
		//Initiate Browser Type
		brSetup = new BrowserSetup(BROWSER_TYPE);
		
		//Create driver based on Browser Type	
		driver = brSetup.CreateBrowserDriver();
		uiOpps=new UI_Operations(driver);
		//Navigate to Base URL
		driver.get(BASE_URL);		
	}
	
	@Test
	public void VerifyTickerPrice() throws InterruptedException {
		Boolean isPriceInRange = uiOpps.verifyTickerPriceRange(TICKER);
		Assert.assertTrue(isPriceInRange,"Ticker Price is not within range");
		if(isPriceInRange){
			Reporter.log("Ticker price is in range");
		}else{
			Reporter.log("Ticker price is NOT in range");
		}
		
	}
	
	@AfterTest
	public void endTest(){
		driver.quit();
	}
}

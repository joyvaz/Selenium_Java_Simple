package operation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class UI_Operations {
	public WebDriver driver;
	public UI_Operations(WebDriver driver){
		this.driver = driver;
	}
	public Boolean verifyTickerPriceRange(String ticker){
		
		Boolean priceRange = false;
		//Search for stock ticker
		Reporter.log("Searching Ticker: "+ticker);
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(ticker);
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);
		
		//Get Current price
		String currentpriceText = driver.findElement(By.xpath("//div[@class='gsrt']")).getText();
		String currentprice=currentpriceText.split(" ", 2)[0];		
		double currentpricedbl = Double.parseDouble(currentprice);
		Reporter.log("Current Price: "+currentprice);
		System.out.println("Current price of stock: "+currentprice);
		
		//Get Higher price
		String highprice = driver.findElement(By.xpath("//td[text()='52-wk high']//following::td")).getText();
		double highpricedbl = Double.parseDouble(highprice);
		System.out.println("Highest price of 52 wk: "+highpricedbl);
		Reporter.log("52 Wk High Price: "+highprice);
		//Get Lower price
		String lowprice = driver.findElement(By.xpath("//td[text()='52-wk low']//following::td")).getText();
		double lowpricedbl = Double.parseDouble(lowprice);
		System.out.println("Lowest price of 52 wk: "+lowpricedbl);
		Reporter.log("52 Wk low price: "+lowprice);
		//Verify the current price is withing the range
			if(currentpricedbl<=highpricedbl && currentpricedbl>=lowpricedbl )
			{
				priceRange = true;
			}
			return priceRange;
	}
}

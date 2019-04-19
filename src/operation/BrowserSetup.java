package operation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserSetup {
	public String browserType;
	public BrowserSetup(String browserType){
		this.browserType=browserType;
	}
	
	public WebDriver CreateBrowserDriver(){
		String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver_win32\\chromedriver.exe";
		System.out.println("Starting test on: "+browserType);
		WebDriver driver=null;
		switch(browserType){
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", chromePath);	    
			driver = new ChromeDriver();
			break;
		case "CHROME-HEADLESS":
			System.setProperty("webdriver.chrome.driver", chromePath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			//chromeOptions.setHeadless(true);	    
			driver = new ChromeDriver(chromeOptions);
			break;
		default:
			System.out.println("Please pass correct browser name");
			
		}
		return driver;
	}
}

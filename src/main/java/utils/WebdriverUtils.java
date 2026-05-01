package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebdriverUtils extends CommonVariables
{
    public static void initDriver()
    {
        if (getDriver() == null)
        {
            WebDriver instance = new ChromeDriver();
            driverLocal.set(instance);
        }
    }

    /**
    * Retrieves the WebDriver instance associated with the current thread.
    * @return The active WebDriver instance for the current thread
    */
    public static WebDriver getDriver()
    {
        if (driverLocal.get() == null)
        {
            String browserType = PropLoader.get("browser");
            if (browserType.isEmpty())
                browserType = "chrome";

            createDriver(browserType);
        }
        return driverLocal.get();
    }

    /**
    * Creates and configures a new WebDriver instance based on the specified browser type.
    * @param browser The name of the browser to launch (case-insensitive, e.g., "chrome", "firefox")
    */
    private static void createDriver(String browser)
    {
        WebDriver driver;
        switch (browser.toLowerCase())
        {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Basic implicit wait
        driverLocal.set(driver);
    }

    /**
    * Navigates the current browser window to the specified URL.
    * @param urlName The ur name
    * @throws IllegalArgumentException if the provided URL is null or an empty string
    */
    public static void openUrl(String urlName)
    {
        String url = PropLoader.get("config.properties", urlName);
        if (url.isEmpty())
            throw new IllegalArgumentException("The provided URL is null or empty.");

        getDriver().get(url);
    }

    /**
    * Quits the WebDriver instance for the current thread and releases resources.
    */
    public static void quitDriver()
    {
        WebDriver currentDriver = driverLocal.get();
        if (currentDriver != null)
        {
            try
            {
                currentDriver.quit();
            }
            catch (Exception e)
            {
                System.err.println("Error while quitting the driver: " + e.getMessage());
            }
            finally
            {
                driverLocal.remove();
            }
        }
    }

    /**
    * Configures Chrome-specific options.
    * @return The configured ChromeOptions object
    */
    private static ChromeOptions getChromeOptions()
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        String headless = System.getProperty("headless");
        if ("true".equalsIgnoreCase(headless))
            options.addArguments("--headless=new");

        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        return options;
    }

    /**
    * Configures Firefox-specific options.
    * @return The configured FirefoxOptions object.
    */
    private static FirefoxOptions getFirefoxOptions()
    {
        FirefoxOptions options = new FirefoxOptions();

        String headless = System.getProperty("headless");
        if ("true".equalsIgnoreCase(headless))
            options.addArguments("-headless");

        return options;
    }
}
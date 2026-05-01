package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebElementsToolbox
{
    /**
    * Waits until the given WebElement is visible on the page.
    * @param driver The WebDriver instance
    * @param element The WebElement to wait for (e.g., from PageFactory)
    * @param timeoutInSeconds The maximum time to wait in seconds
    * @return The visible WebElement (allows for method chaining)
    */
    public static WebElement waitElementToBeVisible(WebDriver driver, WebElement element, int timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
    * Waits until the element is present in the DOM AND visible on the screen.
    * @param driver The Selenium WebDriver instance
    * @param xpath The xpath of the element
    * @param timeoutInSeconds The maximum time to wait in seconds
    * @return The WebElement once it is visible
    */
    public static WebElement waitElementToBeVisible(WebDriver driver, String xpath, int timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
    * Waits for a child element to be present INSIDE a parent element.
    * @param driver The WebDriver instance (needed for the Wait object)
    * @param parent The parent WebElement to search inside
    * @param childXpath The XPath of the child (relative to the parent)
    * @param timeoutInSeconds Maximum time to wait
    * @return The found child WebElement
    */
    public static WebElement waitElementToBePresent(WebDriver driver, WebElement parent, String childXpath, int timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, By.xpath(childXpath)));
    }

    /**
    * Waits until the given WebElement is invisible.
    * Handles StaleElementReferenceException (which means the element is gone, so it is invisible).     *
    * @param driver The WebDriver instance
    * @param element The WebElement to watch
    * @param timeoutInSeconds Maximum time to wait
    * @return true if invisible, false otherwise
    */
    public static  boolean waitElementToBeInvisible(WebDriver driver, WebElement element, int timeoutInSeconds)
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.pollingEvery(Duration.ofMillis(100));
            return wait.until(ExpectedConditions.invisibilityOf(element));
        }
        catch (Exception e)
        {
            return false;
        }
    }
}

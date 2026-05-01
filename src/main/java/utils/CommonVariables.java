package utils;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

public class CommonVariables
{
    public boolean headless;
    public String browser;
    public int thread;
    public static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();

    // Each thread (test) gets its own isolated Map of Properties.
    // .withInitial ensures the Map is created automatically when a new thread accesses it.
    public static ThreadLocal<Map<String, Properties>> propMap = ThreadLocal.withInitial(HashMap::new);

    //This the count for the screenshot
    public static ThreadLocal<AtomicInteger> count = ThreadLocal.withInitial(() -> new AtomicInteger(1));

    public void SetVariable()
    {
        headless = Boolean.valueOf(System.getProperty("headless"));
        browser = String.valueOf(System.getProperty("browser"));
        thread = Integer.parseInt(System.getProperty("thread"));
    }
}

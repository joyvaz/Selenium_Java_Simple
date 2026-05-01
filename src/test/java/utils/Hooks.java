package utils;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;
import java.util.stream.Collectors;

public class Hooks
{
    @Before(order = 0)
    public void setup()
    {
        WebdriverUtils.initDriver();
    }

    @Before(order = 1)
    public void markAsRerun(Scenario scenario)
    {
        String isRerun = System.getProperty("isRerun");

        if ("true".equals(isRerun))
        {
            String fakeTag = "<b style='color: #2A9D8F; font-size: 11px;'>@rerun_attempt</b>";
            scenario.attach(fakeTag, "text/html", "");
        }
    }

    @AfterStep
    public void afterStep(Scenario scenario)
    {
        WebDriver driver = WebdriverUtils.getDriver();
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", String.format("Screenshot_%s", CommonVariables.count.get()));
        if (scenario.isFailed() && driver != null)
        {
            try
            {
                String pageSource = driver.getPageSource();
                scenario.attach(pageSource, "text/html", String.format("SourcePage_%s", CommonVariables.count.get()));

                List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
                String consoleOutput = logs.stream()
                        .map(LogEntry::toString)
                        .collect(Collectors.joining("\n"));

                if (consoleOutput.isEmpty())
                    consoleOutput = "No console logs found.";
                scenario.attach(consoleOutput, "text/plain", String.format("Console_%s", CommonVariables.count.get()));

            }
            catch (Exception e)
            {
                System.err.println("Erreur lors de la capture des preuves de fail : " + e.getMessage());
            }
        }
        CommonVariables.count.get().incrementAndGet();
    }

    @After
    public void tearDown(Scenario scenario)
    {
        PropLoader.unload();
        WebdriverUtils.quitDriver();
    }
}

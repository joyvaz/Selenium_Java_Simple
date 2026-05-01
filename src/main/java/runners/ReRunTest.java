package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Rerun Failed Cucumber tests using TestNG.
 */
@CucumberOptions(
        features = "@reports/rerun.txt",
        glue = {"stepdefinitions", "utils"},
        plugin = {
                "pretty",
                "json:reports/cucumber-json/rerun-cucumber.json",
                "timeline:reports/cucumber-timeline-report",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:reports/rerun.txt"
        },
        monochrome = true
)
public class ReRunTest extends AbstractTestNGCucumberTests
{
    /**
     * Overrides the scenarios method to enable parallel execution
     * @return The array of scenarios to be executed.
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        System.setProperty("isRerun", "true");
        return super.scenarios();
    }
}

package stepdefinitions;

import io.cucumber.java.en.Given;
import utils.WebdriverUtils;

public class StepDefinitionWebDriver
{
    @Given("I open the link {string}")
    public void iOpenTheLink(String url)
    {
        WebdriverUtils.openUrl(url);
    }
}

package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import login.LoginPage;
import utils.WebdriverUtils;

public class StepDefinitionLogin
{
    LoginPage page;

    @Given("I set the credential of the user {string} on the login page")
    public void iSetTheCredentialsOfTheUserOnLoginPage(String user)
    {
        if (page == null)
            page = new LoginPage(WebdriverUtils.getDriver());
        page.setLogin(user);
    }

    @When("I click on The Login button on the login page")
    public void iClickOnTheLoginButtonOnTheLoginPage()
    {
        if (page == null)
            page = new LoginPage(WebdriverUtils.getDriver());
        page.clickLoginButton();
    }

    @Then("I verify an error {optionalMessage} {isOrNot} displayed on the login page")
    public void iVerifyErrorState(String message, boolean isNotDisplayed)
    {
        if (page == null)
            page = new LoginPage(WebdriverUtils.getDriver());
        page.verifyErrorMessage(isNotDisplayed, message);
    }
}

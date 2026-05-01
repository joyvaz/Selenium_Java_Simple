package login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropLoader;
import utils.VerificationToolbox;
import utils.WebElementsToolbox;

public class LoginToolbox
{
    private final WebDriver driver;
    private final LoginLocators locators;

    /**
    * Handles interactions and business logic for the Login page.
    */
    public LoginToolbox(WebDriver driver)
    {
        this.driver = driver;
        this.locators = new LoginLocators(driver);
    }

    /**
    * Retrieves credentials for the specified user alias and fills the login form.
    * The credentials are fetched from the 'accounts.properties' file using the format 'username|password'.
    * @param user The key/alias of the user defined in the properties file.
    */
    public void setLogin(String user)
    {
        String[] userProp = PropLoader.get("accounts.properties", user).split("\\|");
        VerificationToolbox.verifyElementExists(true, locators.loginContainer,
                "The login locator is not visible on the page");

        setValueOnInput(locators.loginContainer, locators.userNameInput, userProp[0].trim(),
                "The user name input is not present on login page");

        setValueOnInput(locators.loginContainer, locators.passwordInput, userProp[1].trim(),
                "The password input is not present on login page");
    }

    /**
     * Helper method to wait for an input field, verify its presence, clear it, and enter text.
     * @param parentElt The parent/container element within which the input field is located
     * @param input The locator string (or selector) used to identify the specific input field relative to the parent
     * @param text The text value to be entered into the input field
     * @param errorMessage The custom error message to display if the element is not found or verification fails
     */
    private void setValueOnInput(WebElement parentElt, String input, String text, String errorMessage)
    {
        WebElement inputElt = WebElementsToolbox.waitElementToBePresent(driver, parentElt, input, 10);
        VerificationToolbox.verifyElementExists(true, inputElt, errorMessage);
        inputElt.clear();
        inputElt.sendKeys(text);
    }

    /**
    * Clicks on the login button after ensuring it is present.
    */
    public void clickOnLoginButton()
    {
        WebElement loginBtnElt = WebElementsToolbox.waitElementToBePresent(driver, locators.loginContainer, locators.loginBtn, 10);
        VerificationToolbox.verifyElementExists(true, loginBtnElt, "The login button is not present on login page");
        loginBtnElt.click();
    }

    /**
    * Verifies the state of the application after a login attempt.
    * @param noError If true, expects a successful login (login container disappears)
    * If false, expects a failed login (error message appears)
    * @param errorMessage The expected text of the error message (ignored if noError is true)
    */
    public void verifyErrorMessage(boolean noError, String errorMessage)
    {
        if(noError)
        {
            WebElementsToolbox.waitElementToBeInvisible(driver, locators.loginContainer, 5);
            VerificationToolbox.verifyElementExists(false, locators.loginContainer,
                    "The login locator should not be visible on the page");
        }
        else
        {
            WebElement errorElt = WebElementsToolbox.waitElementToBePresent(driver, locators.loginContainer, locators.errorMessage, 5);
            VerificationToolbox.verifyElementExists(true, errorElt, "The error message should be displayed");

            VerificationToolbox.verifyElementHasText(true, errorElt, errorMessage,
                    "The error message should be displayed");
        }
    }
}

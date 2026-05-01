package login;

import org.openqa.selenium.WebDriver;

public class LoginPage
{
    WebDriver driver;
    LoginToolbox toolbox;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        this.toolbox = new LoginToolbox(driver);
    }

    public void setLogin(String user)
    {
        toolbox.setLogin(user);
    }

    public void clickLoginButton()
    {
        toolbox.clickOnLoginButton();
    }

    public void verifyErrorMessage(boolean noError, String errorMessage)
    {
        toolbox.verifyErrorMessage(noError, errorMessage);
    }
}

package login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginLocators
{
    @FindBy(className = "login_container")
    public WebElement loginContainer;

    public final String userNameInput = "//input[@id='user-name']";

    public final String passwordInput = "//input[@id='password']";

    public final String loginBtn = "//input[@id='login-button']";

    public final String errorMessage = "//*[@class = 'error-message-container error']";

    public LoginLocators(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
}

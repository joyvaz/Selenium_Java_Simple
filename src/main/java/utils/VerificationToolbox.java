package utils;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class VerificationToolbox
{
    /**
    * Verifies that the element is displayed on the page.
    * Handles the case where the element is not found (NoSuchElementException).
    * @param shouldExist true if element is displayed, false otherwise
    * @param element The WebElement to check (can be a PageFactory proxy).
    * @param message The error message to display if the verification fails.
    */
    public static void verifyElementExists(boolean shouldExist, WebElement element, String message)
    {
        boolean isCurrentlyDisplayed = false;

        try
        {
            isCurrentlyDisplayed = element.isDisplayed();
        }
        catch (Exception e)
        {
            isCurrentlyDisplayed = false;
        }

        Assert.assertEquals(isCurrentlyDisplayed, shouldExist, message);
    }

    /**
     * Verifies if the text of a specific WebElement matches the expected value.
     * @param shouldExist Set to true to verify that the text matches the expected value
     * Set to false to verify that the text DOES NOT match the expected value
     * @param element The WebElement to inspect
     * @param text The expected text string to compare against
     * @param message The error message to display in the report if the verification fails
     */
    public static void verifyElementHasText(boolean shouldExist, WebElement element, String text, String message)
    {
        String actualText = "";
        try
        {
            actualText = element.getText().trim();
        }
        catch (Exception e)
        {
            Assert.fail("Failed to get text from element: " + e.getMessage());
        }

        boolean isMatch = actualText.equalsIgnoreCase(text);
        String debugMsg = String.format("%s -> [Expected: '%s' | Actual: '%s']", message, text, actualText);
        Assert.assertEquals(isMatch, shouldExist, debugMsg);
    }
}

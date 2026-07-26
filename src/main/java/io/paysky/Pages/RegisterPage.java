package io.paysky.Pages;

import io.paysky.Utils.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static io.paysky.Utils.BaseTest.driver;
import static io.paysky.Utils.BaseTest.wait;


public class RegisterPage {


    private final By registerLink      = By.cssSelector("[data-test='register-link']");
    private final By firstNameInput    = By.cssSelector("[data-test='first-name']");
    private final By lastNameInput     = By.cssSelector("[data-test='last-name']");
    private final By dobInput          = By.cssSelector("[data-test='dob']");
    private final By addressInput      = By.cssSelector("[data-test='street']");
    private final By postalCodeInput   = By.cssSelector("[data-test='postal_code']");
    private final By houseNumberInput = By.cssSelector("[data-test='house_number']");
    private final By cityInput         = By.cssSelector("[data-test='city']");
    private final By stateInput        = By.xpath("(//input[@id='state'])[1]");
    private final By countrySelect     = By.cssSelector("[data-test='country']");
    private final By phoneInput        = By.cssSelector("[data-test='phone']");
    private final By emailInput        = By.cssSelector("[data-test='email']");
    private final By passwordInput     = By.cssSelector("[data-test='password']");
    private final By registerButton    = By.cssSelector("[data-test='register-submit']");

    public void openRegisterPage() {

        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    private void safeSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void fillRegistrationForm(UserData user) {

        safeSendKeys(firstNameInput, user.firstName);
        safeSendKeys(lastNameInput, user.lastName);
        safeSendKeys(dobInput, user.dob);
        safeSendKeys(addressInput, user.address);
        safeSendKeys(postalCodeInput, user.postalCode);
        safeSendKeys(houseNumberInput, user.houseNumber);
        safeSendKeys(cityInput, user.city);
        safeSendKeys(stateInput, user.state);

        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(countrySelect));
        new Select(countryDropdown).selectByVisibleText(user.country);

        safeSendKeys(phoneInput, user.phone);
        safeSendKeys(emailInput, user.email);
        safeSendKeys(passwordInput, user.password);
    }

    public void submitRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    public String getRegistrationPageUrl(){
        wait.until(ExpectedConditions.urlContains("auth/register"));
        return driver.getCurrentUrl();
    }

}

package io.paysky.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static io.paysky.Utils.BaseTest.driver;
import static io.paysky.Utils.BaseTest.wait;


public class LoginPage{

    private final By signInNavLink = By.cssSelector("[data-test='nav-sign-in']");
    private final By emailInput    = By.cssSelector("[data-test='email']");
    private final By passwordInput = By.cssSelector("[data-test='password']");
    private final By loginButton   = By.cssSelector("[data-test='login-submit']");
    private final By globalErrorAlert = By.cssSelector("[data-test='login-error']");
    private final By emailErrorMess = By.cssSelector("[data-test='email-error']");
    private final By passwordErrorMess = By.cssSelector("[data-test='password-error']");


    public void goToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signInNavLink)).click();
        wait.until(ExpectedConditions.urlContains("/auth/login"));
    }

    private void safeSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void login(String email, String password) {
        /*safeSendKeys(emailInput, email);
        safeSendKeys(passwordInput, password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();*/
        wait.until(ExpectedConditions.urlContains("/auth/login"));

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(emailInput));

        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        try {
            btn.click();
        } catch (Exception e) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", btn);
        }
    }


    public static String getLoginPageUrl() {
        wait.until(ExpectedConditions.urlContains("/auth/login"));
        return driver.getCurrentUrl();
    }

    public static String getLoginSuccessUrl() {
        wait.until(ExpectedConditions.urlContains("/account"));
        return driver.getCurrentUrl();
    }

    public String getGlobalErrorMess() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(globalErrorAlert)).getText().trim();
    }

    public String getEmailErrorMess(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailErrorMess));
        return element.getText().trim();
    }

    public String getPassErrorMess(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMess));
        return element.getText().trim();
    }

}

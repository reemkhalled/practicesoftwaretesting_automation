package io.paysky.Tests;

import io.paysky.Utils.BaseTest;
import io.paysky.Pages.LoginPage;
import io.paysky.Pages.RegisterPage;
import io.paysky.Utils.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin extends BaseTest {

    private UserData user;

    /*@org.testng.annotations.Test(priority = 1)
    @Epic("validCredentials")
    @Description("Valid")*/

    @BeforeMethod(alwaysRun = true)
    public void createAccountForLogin() {
        user = UserData.generateRandomUser();

        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();

        //Navigate to Login Page & Assert the Login URL
        loginPage.goToLoginPage();

        String actualLoginPageUrl = LoginPage.getLoginPageUrl();
        String expectedLoginPageUrl = "https://practicesoftwaretesting.com/auth/login";

        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Page is not found!");

        //Navigate to Register Page & Assert the Register URL
        registerPage.openRegisterPage();

        String actualRegisterPageUrl = registerPage.getRegistrationPageUrl();
        String expectedRegisterPageUrl = "https://practicesoftwaretesting.com/auth/register";

        Assert.assertEquals(actualRegisterPageUrl, expectedRegisterPageUrl, "Page is not found!");

        //Fill the required fields & Assert directing to Login Page
        registerPage.fillRegistrationForm(user);
        registerPage.submitRegistration();

        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Page is not found!");
    }

    @Test(priority = 1, groups = {"Login", "smoke", "regression"},
            description = "Verify successful login with valid registered user credentials")
    public void loginWithRegisteredUser() {
        LoginPage loginPage = new LoginPage();

        loginPage.goToLoginPage();

        String actualLoginPageUrl = LoginPage.getLoginPageUrl();
        String expectedLoginPageUrl = "https://practicesoftwaretesting.com/auth/login";

        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Page is not found!");

        loginPage.login(user.email, user.password);

        String actualLoginSuccessUrl = LoginPage.getLoginSuccessUrl();
        String expectedLoginSuccessUrl = "https://practicesoftwaretesting.com/account";

        Assert.assertEquals(actualLoginSuccessUrl,expectedLoginSuccessUrl, "Login is failed");
    }

    @Test(priority = 2, groups = {"Login", "negative", "regression"},
            description = "Verify login error message with an unregistered email address")
    public void testLoginWithUnregisteredEmail() {
        LoginPage loginPage = new LoginPage();

        loginPage.goToLoginPage();

        String actualLoginPageUrl = LoginPage.getLoginPageUrl();
        String expectedLoginPageUrl = "https://practicesoftwaretesting.com/auth/login";

        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Page is not found!");

        loginPage.login("unregistered_user_2026@example.com", "WrongPassword123");

        String actualInvalidCredErrorMess = loginPage.getGlobalErrorMess();
        String expectedInvalidCredErrorMess = "Invalid email or password";

        Assert.assertEquals(actualInvalidCredErrorMess, expectedInvalidCredErrorMess,
                "Actual and Expected Error Message is not equal!");
    }

    @Test(priority = 3, groups = {"Login", "negative", "regression"},
            description = "Verify login error message with a valid email but incorrect password")
    public void testLoginWithIncorrectPassword() {
        LoginPage loginPage = new LoginPage();

        loginPage.goToLoginPage();

        String actualLoginPageUrl = LoginPage.getLoginPageUrl();
        String expectedLoginPageUrl = "https://practicesoftwaretesting.com/auth/login";

        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Page is not found!");

        loginPage.login(user.email, "IncorrectPass!");

        String actualInvalidCredErrorMess = loginPage.getGlobalErrorMess();
        String expectedInvalidCredErrorMess = "Invalid email or password";

        Assert.assertEquals(actualInvalidCredErrorMess, expectedInvalidCredErrorMess,
                "Actual and Expected Error Message is not equal!");
    }

    @Test(priority = 4, groups = {"Login", "negative", "regression"},
            description = "Verify validation error when submitting an empty email field")
    public void testLoginWithEmptyEmail() {
        LoginPage loginPage = new LoginPage();

        loginPage.goToLoginPage();

        String actualLoginPageUrl = LoginPage.getLoginPageUrl();
        String expectedLoginPageUrl = "https://practicesoftwaretesting.com/auth/login";

        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Page is not found!");

        loginPage.login("", "somePassword");

        String actualEmptyEmailErrorMess = loginPage.getEmailErrorMess();
        String expectedEmptyEmailErrorMess = "Email is required";

        Assert.assertEquals(actualEmptyEmailErrorMess, expectedEmptyEmailErrorMess,
                "Actual and Expected Error Message is not equal!");
    }

    @Test(priority = 5, groups = {"Login", "negative", "regression"},
            description = "Verify validation error when submitting an empty password field")
    public void testLoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage();

        loginPage.goToLoginPage();

        String actualLoginPageUrl = LoginPage.getLoginPageUrl();
        String expectedLoginPageUrl = "https://practicesoftwaretesting.com/auth/login";

        Assert.assertEquals(actualLoginPageUrl, expectedLoginPageUrl, "Page is not found!");

        loginPage.login(user.email, "");

        String actualEmptyPassErrorMess = loginPage.getPassErrorMess();
        String expectedEmptyPassErrorMess = "Password is required";

        Assert.assertEquals(actualEmptyPassErrorMess, expectedEmptyPassErrorMess,
                "Actual and Expected Error Message is not equal!");
    }

}

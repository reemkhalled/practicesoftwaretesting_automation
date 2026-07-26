package io.paysky.Tests;

import io.paysky.Utils.BaseTest;
import io.paysky.Pages.LoginPage;
import io.paysky.Pages.RegisterPage;
import io.paysky.Utils.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRegister extends BaseTest {

    @Test(groups = {"Register", "regression"})
    public void registerWithRandomData() {
        UserData user = UserData.generateRandomUser();

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
}

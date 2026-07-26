package io.paysky.Tests;

import io.paysky.Utils.BaseTest;
import io.paysky.Pages.*;
import io.paysky.Utils.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestE2EPurchase extends BaseTest {

    @Test(groups = {"E2E", "regression"})
    public void testRegisterLoginAndPurchase() {
        UserData user = UserData.generateRandomUser();

        LoginPage loginPage = new LoginPage();
        RegisterPage registerPage = new RegisterPage();
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        //Registration
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

        //Login
        loginPage.login(user.email, user.password);
        String actualLoginSuccessUrl = LoginPage.getLoginSuccessUrl();
        String expectedLoginSuccessUrl = "https://practicesoftwaretesting.com/account";

        Assert.assertEquals(actualLoginSuccessUrl, expectedLoginSuccessUrl,"Login is failed");

        //nav to homepage with assertion and select a product with assertion
        homePage.clickHomeNav();

        String actualHomePageUrl = homePage.homePageUrl();
        String expectedHomePageUrl = "https://practicesoftwaretesting.com/";

        Assert.assertEquals(actualHomePageUrl, expectedHomePageUrl, "Page is not found");

        //Select a product & assert it
        homePage.selectCombinationPliers();

        String actualProductNameText = HomePage.getProductNameText();
        String expectedProductNameText = "Combination Pliers";

        Assert.assertEquals(actualProductNameText, expectedProductNameText, "No product is found!");

        //add to cart with assertion and go to cart
        productPage.addProductToCart();

        String actualAddToCartMess = productPage.getAddToCartToastMessSuccess();
        String expectedAddToCartMess = "Product added to shopping cart.";

        Assert.assertEquals(actualAddToCartMess, expectedAddToCartMess,"Product is not added");

        productPage.clickCartIcon();

        //Checkout
        checkoutPage.proceedThroughSteps();
        checkoutPage.selectCashOnDeliveryAndConfirm();

        String actualPaymentMess = checkoutPage.getPaymentMess();
        String expectedPaymentMess = "Payment was successful";

        Assert.assertEquals(actualPaymentMess,expectedPaymentMess,"Payment method is not selected");

        String actualConfirmationText = checkoutPage.getOrderConfirmationText();
        String expectedConfirmationText = "Thanks for your order! Your invoice number is";

        Assert.assertEquals(actualConfirmationText,
                expectedConfirmationText,"Order is not completed!");
    }
}

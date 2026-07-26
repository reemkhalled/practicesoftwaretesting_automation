package io.paysky.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static io.paysky.Utils.BaseTest.wait;


public class ProductPage {

    private final By addToCartButton = By.cssSelector("[data-test='add-to-cart']");
    private final By dynamicToast = By.cssSelector(".toast-message");
    private final By cartIcon = By.cssSelector("fa-icon.ng-fa-icon.px-1");

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public String getAddToCartToastMessSuccess() {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicToast));
        return toast.getText().trim();
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
}

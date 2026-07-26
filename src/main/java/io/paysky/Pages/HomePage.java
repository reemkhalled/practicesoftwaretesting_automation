package io.paysky.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static io.paysky.Utils.BaseTest.driver;
import static io.paysky.Utils.BaseTest.wait;


public class HomePage {


    private final By homeNavLink = By.cssSelector("[data-test='nav-home']");
    // Explicit dynamic locator for your target product text element
    private final By combinationPliersLink = By.xpath(
            "//h5[@data-test='product-name' and normalize-space()='Combination Pliers']");
    private static final By productNameHeader = By.cssSelector("h1[data-test='product-name']");


    public void clickHomeNav() {
        wait.until(ExpectedConditions.elementToBeClickable(homeNavLink)).click();
    }

    public void selectCombinationPliers() {
        wait.until(ExpectedConditions.elementToBeClickable(combinationPliersLink)).click();
    }

    public String homePageUrl(){
        wait.until(ExpectedConditions.urlContains("https://practicesoftwaretesting.com/"));
        return driver.getCurrentUrl();
    }

    public static String getProductNameText(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(productNameHeader));
        return element.getText().trim();
    }
}

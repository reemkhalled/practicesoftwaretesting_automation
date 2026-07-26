package io.paysky.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static io.paysky.Utils.BaseTest.driver;
import static io.paysky.Utils.BaseTest.wait;


public class CheckoutPage {


    private final By proceedStep1 = By.cssSelector("[data-test='proceed-1']");
    private final By proceedStep2 = By.cssSelector("[data-test='proceed-2']");
    private final By proceedStep3 = By.cssSelector("[data-test='proceed-3']");
    private final By houseNumberInput = By.xpath("(//input[@id='house_number'])[1]");
    private final By paymentMethodSelect = By.cssSelector("[data-test='payment-method']");
    private final By confirmButton = By.cssSelector("[data-test='finish']");
    private final By paymentSuccessMess = By.xpath("(//div[@class='help-block'])[1]");
    private final By orderConfirmation = By.xpath("(//div[@id='order-confirmation'])[1]");

    public void proceedThroughSteps() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedStep1)).click();
        wait.until(ExpectedConditions.elementToBeClickable(proceedStep2)).click();
        WebElement houseNum = wait.until(ExpectedConditions.visibilityOfElementLocated(houseNumberInput));
        houseNum.sendKeys("62642");
        //houseNum.clear();
        wait.until(ExpectedConditions.elementToBeClickable(proceedStep3)).click();
    }

    public void selectCashOnDeliveryAndConfirm() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(paymentMethodSelect));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Cash on Delivery");
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();

        int attempts = 0;
        boolean isConfirmed = false;

        while (attempts < 5 && !isConfirmed) {
            try {
                // If the confirmation box is visible, the second click worked!
                if (!driver.findElements(orderConfirmation).isEmpty() &&
                        driver.findElement(orderConfirmation).isDisplayed()) {
                    isConfirmed = true;
                    break;
                }

                // Otherwise, find the button freshly and click it again
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
                btn.click();

                // Give the UI a brief moment to process the state transition
                Thread.sleep(800);
            } catch (Exception e) {
                // Catch StaleElement, ElementClickIntercepted, or InterruptedException issues during redraw
                attempts++;
            }
        }
    }

    public String getPaymentMess(){
        WebElement paymentMessElement = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentSuccessMess));
        return paymentMessElement.getText().trim();
    }

    public String getOrderConfirmationText() {
        WebElement confirmationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
        return confirmationElement.getText().trim();
    }
}

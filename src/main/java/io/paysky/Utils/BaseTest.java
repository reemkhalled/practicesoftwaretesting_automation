package io.paysky.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    /*public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Thread.sleep(3000);
        driver.navigate().to("https://practicesoftwaretesting.com/");
    }*/
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        // SMART FIX: Configure Chrome to disable all password and address autofill popups
        ChromeOptions options = new ChromeOptions();

        // 1. Disables password manager and bubble popups
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-autofill-keyboard-accessory-view");

        // 2. Turns off autofill profile saving features via experimental preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false); // Turns off "Save address" popup
        prefs.put("autofill.credit_card_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // 3. Excludes automation switches to prevent browser banner interference
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.navigate().to("https://practicesoftwaretesting.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

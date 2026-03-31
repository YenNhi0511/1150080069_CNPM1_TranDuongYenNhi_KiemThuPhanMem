package framework.base;

import framework.config.ConfigReader;
import framework.driver.DriverFactory;
import framework.utils.ScreenshotUtil;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.time.Duration;

/**
 * BaseTest - Lop cha cho tat ca Test class.
 * Quan ly vong doi WebDriver bang ThreadLocal de ho tro chay song song.
 * Tich hop Allure screenshot khi test fail.
 */
public abstract class BaseTest {

    // ThreadLocal dam bao moi thread co WebDriver rieng (parallel safe)
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters({"browser", "env"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser,
                       @Optional("dev") String env) {
        // Dat env lam System property de ConfigReader doc dung file
        System.setProperty("env", env);

        // Tao WebDriver qua DriverFactory
        WebDriver driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navigate den base URL tu config
        driver.get(ConfigReader.getInstance().getBaseUrl());
        tlDriver.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        // Chup anh TRUOC khi quit - bat buoc trong du an thuc de debug
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtil.capture(getDriver(), result.getName());

            // Dinh kem vao Allure report neu dang dung Allure
            try {
                Allure.addAttachment("Screenshot khi fail",
                        new ByteArrayInputStream(
                                ScreenshotUtil.captureAsBytes(getDriver())));
            } catch (Exception e) {
                System.out.println("Khong the dinh kem screenshot vao Allure: " + e.getMessage());
            }
        }

        // Quit driver va remove khoi ThreadLocal
        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove(); // Quan trong: tranh memory leak khi chay song song
        }
    }
}

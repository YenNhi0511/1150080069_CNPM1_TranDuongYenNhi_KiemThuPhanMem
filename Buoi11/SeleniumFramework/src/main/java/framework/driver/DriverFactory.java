package framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * DriverFactory - Tao WebDriver theo browser va moi truong.
 * Ho tro: local, headless (CI), va RemoteWebDriver (Selenium Grid).
 */
public class DriverFactory {

    /**
     * Tao WebDriver dua theo ten browser.
     * Tu dong phat hien moi truong CI de bat headless mode.
     * Neu co grid.url thi dung RemoteWebDriver.
     */
    public static WebDriver createDriver(String browser) {
        WebDriver driver;

        // Kiem tra co dang chay tren CI khong (GitHub Actions set CI=true)
        boolean isCI = System.getenv("CI") != null;

        // Kiem tra Selenium Grid URL
        String gridUrl = System.getProperty("grid.url");
        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = System.getenv("GRID_URL");
        }

        // Neu co Grid URL => dung RemoteWebDriver
        if (gridUrl != null && !gridUrl.isEmpty()) {
            driver = createRemoteDriver(browser, gridUrl);
        } else {
            // Tao driver local
            driver = createLocalDriver(browser, isCI);
        }

        return driver;
    }

    /**
     * Tao driver chay tren may local.
     * Bat headless neu dang o moi truong CI.
     */
    private static WebDriver createLocalDriver(String browser, boolean isCI) {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isCI) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("--no-sandbox");
                    firefoxOptions.addArguments("--disable-dev-shm-usage");
                    firefoxOptions.addArguments("--window-size=1920,1080");
                }
                return new FirefoxDriver(firefoxOptions);

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isCI) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }
                return new ChromeDriver(chromeOptions);
        }
    }

    /**
     * Tao RemoteWebDriver ket noi den Selenium Grid.
     * Su dung khi chay voi Docker Compose.
     */
    private static WebDriver createRemoteDriver(String browser, String gridUrl) {
        try {
            URL hubUrl = new URL(gridUrl + "/wd/hub");

            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    return new RemoteWebDriver(hubUrl, firefoxOptions);

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    return new RemoteWebDriver(hubUrl, chromeOptions);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Grid URL khong hop le: " + gridUrl, e);
        }
    }
}

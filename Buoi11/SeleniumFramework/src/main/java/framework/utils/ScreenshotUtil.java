package framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ScreenshotUtil - Chup anh man hinh khi test fail.
 * Luu vao thu muc screenshots/ voi ten test va timestamp.
 */
public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "screenshots";

    /**
     * Chup screenshot va luu ra file.
     *
     * @param driver WebDriver hien tai
     * @param testName ten test method
     * @return duong dan file screenshot
     */
    public static String capture(WebDriver driver, String testName) {
        try {
            // Tao thu muc screenshots neu chua co
            Path dir = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            // Tao ten file voi timestamp
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";
            Path filePath = dir.resolve(fileName);

            // Chup screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), filePath);

            System.out.println("Screenshot da luu tai: " + filePath.toAbsolutePath());
            return filePath.toAbsolutePath().toString();

        } catch (IOException e) {
            System.out.println("Khong the chup screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Chup screenshot va tra ve byte array (dung cho Allure attachment).
     */
    public static byte[] captureAsBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

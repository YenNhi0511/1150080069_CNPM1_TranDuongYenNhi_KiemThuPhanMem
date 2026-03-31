package framework.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * RetryAnalyzer - Tu dong chay lai test bi fail do loi mang/flakiness.
 * Mac dinh retry toi da 2 lan.
 * Su dung: @Test(retryAnalyzer = RetryAnalyzer.class)
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("Retry lan " + retryCount + " cho test: " + result.getName());
            return true;
        }
        return false;
    }
}

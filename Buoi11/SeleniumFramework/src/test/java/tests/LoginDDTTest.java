package tests;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.ExcelReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * LoginDDTTest - Data-Driven Login Test tu file Excel.
 * Bai 3 Lab 9: Doc du lieu tu login_data.xlsx voi 3 sheet.
 * Ten test method hien thi description tu cot Excel.
 */
@Epic("SauceDemo Tests")
@Feature("Login Data-Driven")
public class LoginDDTTest extends BaseTest {

    private static final String EXCEL_FILE = "testdata/login_data.xlsx";

    // =================== DATA PROVIDERS ===================

    @DataProvider(name = "smokeLoginData")
    public Object[][] smokeLoginData() {
        return ExcelReader.readExcelFromResources(EXCEL_FILE, "SmokeCases");
    }

    @DataProvider(name = "negativeLoginData")
    public Object[][] negativeLoginData() {
        return ExcelReader.readExcelFromResources(EXCEL_FILE, "NegativeCases");
    }

    @DataProvider(name = "boundaryLoginData")
    public Object[][] boundaryLoginData() {
        return ExcelReader.readExcelFromResources(EXCEL_FILE, "BoundaryCases");
    }

    // =================== SMOKE TESTS ===================

    @Test(dataProvider = "smokeLoginData", groups = {"smoke"})
    @Story("Smoke Login Tests")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Kiem tra dang nhap happy path tu Excel - SmokeCases sheet")
    public void testLoginFromExcel_Smoke(String username, String password,
                                          String expectedUrl, String description) {
        Allure.step("Test case: " + description);
        Allure.step("Dang nhap voi: " + username + " / " + password);

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login(username, password);

        Allure.step("Kiem tra URL sau dang nhap");
        Assert.assertTrue(getDriver().getCurrentUrl().contains(expectedUrl),
                description + " - URL khong dung. Expected chua: " + expectedUrl);
    }

    // =================== NEGATIVE TESTS ===================

    @Test(dataProvider = "negativeLoginData", groups = {"regression"})
    @Story("Negative Login Tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Kiem tra dang nhap that bai tu Excel - NegativeCases sheet")
    public void testLoginFromExcel_Negative(String username, String password,
                                              String expectedError, String description) {
        Allure.step("Test case: " + description);
        Allure.step("Dang nhap voi: " + username + " / " + password);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure(username, password);

        Allure.step("Kiem tra thong bao loi");
        Assert.assertTrue(loginPage.isErrorDisplayed(),
                description + " - Phai hien thi thong bao loi");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                description + " - Loi khong dung. Expected chua: " + expectedError);
    }

    // =================== BOUNDARY TESTS ===================

    @Test(dataProvider = "boundaryLoginData", groups = {"regression"})
    @Story("Boundary Login Tests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Kiem tra dang nhap voi du lieu bien tu Excel - BoundaryCases sheet")
    public void testLoginFromExcel_Boundary(String username, String password,
                                              String expectedError, String description) {
        Allure.step("Test case: " + description);
        Allure.step("Dang nhap voi du lieu bien: [" + username + "]");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure(username, password);

        Allure.step("Kiem tra thong bao loi");
        Assert.assertTrue(loginPage.isErrorDisplayed(),
                description + " - Phai hien thi thong bao loi");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                description + " - Loi khong dung. Expected chua: " + expectedError);
    }
}

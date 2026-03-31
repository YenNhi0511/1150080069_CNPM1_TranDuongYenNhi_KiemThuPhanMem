package tests;

import framework.base.BaseTest;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import framework.utils.RetryAnalyzer;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * LoginTest - Test dang nhap co ban cho SauceDemo.
 * Bai 2 Lab 9: Test login thanh cong va that bai.
 */
@Epic("SauceDemo Tests")
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    @Test(groups = {"smoke"}, retryAnalyzer = RetryAnalyzer.class)
    @Story("Dang nhap thanh cong")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Kiem tra dang nhap thanh cong voi tai khoan standard_user")
    public void testLoginSuccess() {
        Allure.step("Buoc 1: Mo trang Login");
        LoginPage loginPage = new LoginPage(getDriver());

        Allure.step("Buoc 2: Nhap username va password hop le");
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Allure.step("Buoc 3: Kiem tra chuyen den trang Products");
        Assert.assertTrue(inventoryPage.isPageDisplayed(),
                "Trang Inventory phai hien thi sau khi dang nhap thanh cong");
    }

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    @Story("Dang nhap that bai - sai password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Kiem tra thong bao loi khi nhap sai password")
    public void testLoginFailWrongPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Phai hien thi thong bao loi");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),
                "Thong bao loi phai chua noi dung phu hop");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    @Story("Dang nhap that bai - tai khoan bi khoa")
    @Severity(SeverityLevel.NORMAL)
    @Description("Kiem tra thong bao loi khi dung tai khoan locked_out_user")
    public void testLoginLockedOutUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Phai hien thi thong bao loi");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Thong bao loi phai bao tai khoan bi khoa");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    @Story("Dang nhap that bai - de trong username")
    @Severity(SeverityLevel.MINOR)
    @Description("Kiem tra thong bao loi khi de trong truong username")
    public void testLoginEmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Phai hien thi thong bao loi");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"),
                "Thong bao loi phai yeu cau nhap username");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    @Story("Dang nhap that bai - de trong password")
    @Severity(SeverityLevel.MINOR)
    @Description("Kiem tra thong bao loi khi de trong truong password")
    public void testLoginEmptyPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginExpectingFailure("standard_user", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Phai hien thi thong bao loi");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"),
                "Thong bao loi phai yeu cau nhap password");
    }
}

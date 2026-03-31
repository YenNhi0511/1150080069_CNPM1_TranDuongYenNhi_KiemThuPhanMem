package framework.pages;

import framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * LoginPage - Page Object cho trang dang nhap SauceDemo.
 * URL: https://www.saucedemo.com/
 */
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    @FindBy(css = "[data-test='error']")
    private WebElement lblError;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Dang nhap thanh cong => tra ve InventoryPage (Fluent Interface).
     */
    @Step("Dang nhap voi username: {username} va password: {password}")
    public InventoryPage login(String username, String password) {
        waitAndType(txtUsername, username);
        waitAndType(txtPassword, password);
        waitAndClick(btnLogin);
        return new InventoryPage(driver);
    }

    /**
     * Dang nhap voi du kien that bai => van o trang Login.
     */
    @Step("Dang nhap that bai voi username: {username}")
    public LoginPage loginExpectingFailure(String username, String password) {
        waitAndType(txtUsername, username);
        waitAndType(txtPassword, password);
        waitAndClick(btnLogin);
        return this;
    }

    /**
     * Lay thong bao loi khi dang nhap that bai.
     */
    @Step("Lay thong bao loi dang nhap")
    public String getErrorMessage() {
        return waitAndGetText(lblError);
    }

    /**
     * Kiem tra co hien thi loi khong.
     */
    public boolean isErrorDisplayed() {
        return isElementDisplayed(lblError);
    }
}

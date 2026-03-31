package tests;

import com.github.javafaker.Faker;
import framework.base.BaseTest;
import framework.pages.*;
import framework.utils.JsonReader;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * CheckoutTest - E2E Checkout voi JSON data + Java Faker.
 * Bai 4 Lab 9: Data-driven voi JSON va du lieu ngau nhien.
 */
@Epic("SauceDemo Tests")
@Feature("Checkout Feature")
public class CheckoutTest extends BaseTest {

    private final Faker faker = new Faker();

    @DataProvider(name = "jsonUserData")
    public Object[][] jsonUserData() {
        return JsonReader.toDataProviderArray("testdata/users.json");
    }

    // =================== E2E TEST WITH FAKER ===================

    @Test(groups = {"smoke"})
    @Story("E2E Checkout thanh cong voi Java Faker")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Kiem tra luong mua hang tu dau den cuoi voi du lieu ngau nhien tu Faker")
    public void testE2ECheckoutWithFaker() {
        // Sinh du lieu ngau nhien
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String zipCode = faker.address().zipCode();

        Allure.step("Du lieu khach hang: " + firstName + " " + lastName + ", Zip: " + zipCode);

        // Buoc 1: Dang nhap
        Allure.step("Buoc 1: Dang nhap voi standard_user");
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Phai o trang Inventory");

        // Buoc 2: Them san pham vao gio
        Allure.step("Buoc 2: Them 2 san pham vao gio hang");
        inventoryPage.addFirstItemToCart().addItemToCart(1);
        Assert.assertEquals(inventoryPage.getCartItemCount(), 2,
                "Gio hang phai co 2 san pham");

        // Buoc 3: Mo gio hang
        Allure.step("Buoc 3: Mo gio hang");
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isPageDisplayed(), "Phai o trang Cart");
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart phai co 2 item");

        // Buoc 4: Checkout
        Allure.step("Buoc 4: Tien hanh Checkout");
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.fillInformation(firstName, lastName, zipCode);

        // Buoc 5: Hoan tat
        Allure.step("Buoc 5: Hoan tat don hang");
        checkoutPage.finish();

        Assert.assertTrue(checkoutPage.isOrderComplete(),
                "Don hang phai hoan tat thanh cong");
    }

    // =================== E2E TEST WITH JSON ===================

    @Test(dataProvider = "jsonUserData", groups = {"regression"})
    @Story("E2E Checkout voi du lieu tu JSON")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Kiem tra luong mua hang voi du lieu khach hang tu file JSON")
    public void testE2ECheckoutWithJson(Map<String, String> userData) {
        String username = userData.get("username");
        String password = userData.get("password");
        String role = userData.get("role");
        boolean expectSuccess = Boolean.parseBoolean(userData.get("expectSuccess"));
        String description = userData.get("description");

        Allure.step("Test case: " + description);
        Allure.step("Dang nhap voi user: " + username + " (role: " + role + ")");

        LoginPage loginPage = new LoginPage(getDriver());

        if (expectSuccess) {
            InventoryPage inventoryPage = loginPage.login(username, password);
            Assert.assertTrue(inventoryPage.isPageDisplayed(),
                    description + " - Phai dang nhap thanh cong");

            // Them san pham va checkout voi Faker data
            inventoryPage.addFirstItemToCart();
            CartPage cartPage = inventoryPage.goToCart();
            CheckoutPage checkoutPage = cartPage.checkout();

            checkoutPage.fillInformation(
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.address().zipCode()
            );
            checkoutPage.finish();

            Assert.assertTrue(checkoutPage.isOrderComplete(),
                    description + " - Don hang phai hoan tat");
        } else {
            loginPage.loginExpectingFailure(username, password);
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    description + " - Phai hien thi loi dang nhap");
        }
    }
}

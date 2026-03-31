package tests;

import framework.base.BaseTest;
import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * CartTest - Test chuc nang gio hang.
 * Bai 2 Lab 9: POM cho CartPage.
 */
@Epic("SauceDemo Tests")
@Feature("Cart Feature")
public class CartTest extends BaseTest {

    @Test(groups = {"smoke"})
    @Story("Them san pham vao gio hang")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Kiem tra them san pham vao gio hang va so luong badge cap nhat")
    public void testAddItemToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Allure.step("Them san pham dau tien vao gio");
        inventoryPage.addFirstItemToCart();

        Assert.assertEquals(inventoryPage.getCartItemCount(), 1,
                "Badge gio hang phai hien thi 1");
    }

    @Test(groups = {"regression"})
    @Story("Them nhieu san pham vao gio hang")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Kiem tra them 3 san pham va badge cap nhat dung")
    public void testAddMultipleItemsToCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addItemToCart(0)
                     .addItemToCart(1)
                     .addItemToCart(2);

        Assert.assertEquals(inventoryPage.getCartItemCount(), 3,
                "Badge gio hang phai hien thi 3");
    }

    @Test(groups = {"regression"})
    @Story("Xoa san pham khoi gio hang")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Kiem tra xoa san pham khoi gio hang")
    public void testRemoveItemFromCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        // Them 2 san pham
        inventoryPage.addFirstItemToCart().addItemToCart(1);
        Assert.assertEquals(inventoryPage.getCartItemCount(), 2);

        // Mo Cart va xoa 1 san pham
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isPageDisplayed());

        cartPage.removeFirstItem();
        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Gio hang phai con 1 san pham sau khi xoa");
    }

    @Test(groups = {"regression"})
    @Story("Quay lai mua tiep tu gio hang")
    @Severity(SeverityLevel.NORMAL)
    @Description("Kiem tra nut Continue Shopping quay lai trang san pham")
    public void testContinueShopping() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addFirstItemToCart();
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isPageDisplayed());

        // Quay lai trang san pham
        InventoryPage returnPage = cartPage.continueShopping();
        Assert.assertTrue(returnPage.isPageDisplayed(),
                "Phai quay lai trang Products");
    }
}

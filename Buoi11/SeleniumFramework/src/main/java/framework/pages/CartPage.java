package framework.pages;

import framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * CartPage - Page Object cho trang gio hang SauceDemo.
 * URL: https://www.saucedemo.com/cart.html
 */
public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart_item .inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(css = ".cart_item .inventory_item_price")
    private List<WebElement> itemPrices;

    @FindBy(css = ".cart_button")
    private List<WebElement> removeButtons;

    @FindBy(id = "checkout")
    private WebElement btnCheckout;

    @FindBy(id = "continue-shopping")
    private WebElement btnContinueShopping;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Kiem tra trang Cart hien thi.
     */
    @Step("Kiem tra trang Cart hien thi")
    public boolean isPageDisplayed() {
        return isElementDisplayed(pageTitle) &&
               waitAndGetText(pageTitle).equals("Your Cart");
    }

    /**
     * Lay so luong san pham trong gio hang.
     */
    @Step("Lay so luong san pham trong gio")
    public int getCartItemCount() {
        return cartItems.size();
    }

    /**
     * Lay ten san pham dau tien trong gio.
     */
    public String getFirstItemName() {
        if (itemNames.isEmpty()) return "";
        return waitAndGetText(itemNames.get(0));
    }

    /**
     * Xoa san pham dau tien khoi gio hang.
     */
    @Step("Xoa san pham dau tien khoi gio")
    public CartPage removeFirstItem() {
        if (!removeButtons.isEmpty()) {
            waitAndClick(removeButtons.get(0));
        }
        return this;
    }

    /**
     * Click Checkout => chuyen den CheckoutPage.
     */
    @Step("Click nut Checkout")
    public CheckoutPage checkout() {
        waitAndClick(btnCheckout);
        return new CheckoutPage(driver);
    }

    /**
     * Quay lai trang san pham.
     */
    @Step("Quay lai trang san pham")
    public InventoryPage continueShopping() {
        waitAndClick(btnContinueShopping);
        return new InventoryPage(driver);
    }
}

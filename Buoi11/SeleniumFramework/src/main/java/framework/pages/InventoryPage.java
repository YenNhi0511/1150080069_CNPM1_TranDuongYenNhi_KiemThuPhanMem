package framework.pages;

import framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * InventoryPage - Page Object cho trang danh sach san pham SauceDemo.
 * URL: https://www.saucedemo.com/inventory.html
 */
public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(css = ".btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(css = ".inventory_item_price")
    private List<WebElement> itemPrices;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Kiem tra da dang nhap thanh cong (trang Inventory hien thi).
     */
    @Step("Kiem tra trang Inventory hien thi")
    public boolean isPageDisplayed() {
        return isElementDisplayed(pageTitle) &&
               waitAndGetText(pageTitle).equals("Products");
    }

    /**
     * Them san pham dau tien vao gio hang.
     * Tra ve this (Fluent Interface).
     */
    @Step("Them san pham dau tien vao gio hang")
    public InventoryPage addFirstItemToCart() {
        waitAndClick(addToCartButtons.get(0));
        return this;
    }

    /**
     * Them san pham theo index vao gio hang.
     */
    @Step("Them san pham thu {index} vao gio hang")
    public InventoryPage addItemToCart(int index) {
        if (index >= 0 && index < addToCartButtons.size()) {
            waitAndClick(addToCartButtons.get(index));
        }
        return this;
    }

    /**
     * Lay so luong item trong badge gio hang.
     * Tra ve 0 neu badge khong hien thi (gio hang rong).
     */
    @Step("Lay so luong item trong gio hang")
    public int getCartItemCount() {
        try {
            return Integer.parseInt(cartBadge.getText().trim());
        } catch (Exception e) {
            return 0; // Badge khong hien thi = gio hang rong
        }
    }

    /**
     * Click vao icon gio hang => chuyen den CartPage.
     */
    @Step("Mo gio hang")
    public CartPage goToCart() {
        waitAndClick(cartLink);
        return new CartPage(driver);
    }

    /**
     * Lay tong so san pham tren trang.
     */
    public int getProductCount() {
        return inventoryItems.size();
    }

    /**
     * Lay ten san pham dau tien.
     */
    public String getFirstItemName() {
        return waitAndGetText(itemNames.get(0));
    }
}

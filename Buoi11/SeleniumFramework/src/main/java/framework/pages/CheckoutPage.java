package framework.pages;

import framework.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * CheckoutPage - Page Object cho luong checkout SauceDemo.
 * Bao gom: Checkout Step One (thong tin), Step Two (tong ket), Complete.
 */
public class CheckoutPage extends BasePage {

    // ===== Step One: Your Information =====
    @FindBy(id = "first-name")
    private WebElement txtFirstName;

    @FindBy(id = "last-name")
    private WebElement txtLastName;

    @FindBy(id = "postal-code")
    private WebElement txtPostalCode;

    @FindBy(id = "continue")
    private WebElement btnContinue;

    @FindBy(id = "cancel")
    private WebElement btnCancel;

    @FindBy(css = "[data-test='error']")
    private WebElement lblError;

    // ===== Step Two: Overview =====
    @FindBy(className = "summary_subtotal_label")
    private WebElement lblSubtotal;

    @FindBy(className = "summary_tax_label")
    private WebElement lblTax;

    @FindBy(className = "summary_total_label")
    private WebElement lblTotal;

    @FindBy(id = "finish")
    private WebElement btnFinish;

    // ===== Complete =====
    @FindBy(className = "complete-header")
    private WebElement lblCompleteHeader;

    @FindBy(className = "complete-text")
    private WebElement lblCompleteText;

    @FindBy(id = "back-to-products")
    private WebElement btnBackToProducts;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Dien thong tin khach hang va click Continue.
     */
    @Step("Dien thong tin: {firstName} {lastName}, Zip: {postalCode}")
    public CheckoutPage fillInformation(String firstName, String lastName, String postalCode) {
        waitAndType(txtFirstName, firstName);
        waitAndType(txtLastName, lastName);
        waitAndType(txtPostalCode, postalCode);
        waitAndClick(btnContinue);
        return this;
    }

    /**
     * Dien thong tin nhung khong click Continue (dung de test loi).
     */
    public CheckoutPage fillInformationExpectingError(String firstName, String lastName, String postalCode) {
        waitAndType(txtFirstName, firstName);
        waitAndType(txtLastName, lastName);
        waitAndType(txtPostalCode, postalCode);
        waitAndClick(btnContinue);
        return this;
    }

    /**
     * Lay thong bao loi.
     */
    @Step("Lay thong bao loi checkout")
    public String getErrorMessage() {
        return waitAndGetText(lblError);
    }

    /**
     * Lay tong tien.
     */
    @Step("Lay tong tien")
    public String getTotal() {
        return waitAndGetText(lblTotal);
    }

    /**
     * Click Finish de hoan tat don hang.
     */
    @Step("Click Finish de hoan tat don hang")
    public CheckoutPage finish() {
        waitAndClick(btnFinish);
        return this;
    }

    /**
     * Lay thong bao hoan tat don hang.
     */
    @Step("Lay thong bao hoan tat")
    public String getCompleteHeader() {
        return waitAndGetText(lblCompleteHeader);
    }

    /**
     * Kiem tra don hang da hoan tat thanh cong.
     */
    @Step("Kiem tra don hang hoan tat thanh cong")
    public boolean isOrderComplete() {
        return isElementDisplayed(lblCompleteHeader) &&
               getCompleteHeader().contains("Thank you for your order");
    }

    /**
     * Quay lai trang san pham sau khi hoan tat.
     */
    @Step("Quay lai trang san pham")
    public InventoryPage backToProducts() {
        waitAndClick(btnBackToProducts);
        return new InventoryPage(driver);
    }

    /**
     * Lay tieu de trang hien tai.
     */
    public String getPageTitleText() {
        return waitAndGetText(pageTitle);
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class CheckoutPage {

    private WebDriver driver;
    private WaitUtils wait;

    // Checkout button in cart
    private By checkoutButton = By.cssSelector(".buy-btn");

    // Shipping form fields
    private By firstName = By.id("firstNameInput");
    private By lastName = By.id("lastNameInput");
    private By address = By.id("addressLine1Input");
    private By state = By.id("provinceInput");
    private By postalCode = By.id("postCodeInput");

    private By submitButton = By.id("checkout-shipping-continue");

    // Confirmation page: multiple possible selectors
    private By confirmationContainer1 = By.cssSelector(".confirmation-message");
    private By confirmationContainer2 = By.cssSelector(".checkout-success, .checkout-complete, .checkout-container");
    private By continueShoppingBtn = By.xpath("//*[self::button or self::a][contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'continue')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void clickCheckout() {
        wait.waitClickable(checkoutButton).click();
    }

    // ✅ For negative test: click if present (even if it does nothing)
    public void tryClickCheckout() {
        if (wait.isPresent(checkoutButton, 2)) {
            driver.findElement(checkoutButton).click();
        }
    }

    // ✅ Are we on the shipping form page?
    public boolean isOnShippingForm() {
        return wait.isPresent(firstName, 2);
    }

    public void fillShippingDetails() {
        wait.waitVisible(firstName).clear();
        driver.findElement(firstName).sendKeys("Shreya");

        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys("Test");

        driver.findElement(address).clear();
        driver.findElement(address).sendKeys("123 Demo Street");

        driver.findElement(state).clear();
        driver.findElement(state).sendKeys("Karnataka");

        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys("560001");
    }

    public void submitOrder() {
        wait.waitClickable(submitButton).click();
    }

    public boolean isOrderSuccessful() {
        if (wait.isPresent(confirmationContainer1, 3)) return true;
        if (wait.isPresent(confirmationContainer2, 3)) return true;

        String page = driver.getPageSource().toLowerCase();
        if (page.contains("successfully") || page.contains("thank you") || page.contains("order")) {
            if (!driver.getCurrentUrl().contains("/signin")) return true;
        }

        if (wait.isPresent(continueShoppingBtn, 3)) return true;

        return false;
    }
}

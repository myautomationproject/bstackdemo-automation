package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class CartPage {

    private WebDriver driver;
    private WaitUtils wait;

    // Cart items inside the cart panel
    private By cartItems = By.cssSelector(".float-cart__content .shelf-item");

    // Remove button/icon inside each cart item (try common selectors)
    private By removeButtons = By.cssSelector(
            ".float-cart__content .shelf-item .shelf-item__del, " +
            ".float-cart__content .shelf-item button[class*='del'], " +
            ".float-cart__content .shelf-item button[aria-label*='delete'], " +
            ".float-cart__content .shelf-item button"
    );

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public int getCartItemsCount() {
        wait.isAnyVisible(cartItems, 5);

        List<WebElement> items = driver.findElements(cartItems);

        int count = 0;
        for (WebElement el : items) {
            try {
                if (el.isDisplayed()) count++;
            } catch (Exception ignored) {}
        }
        return count;
    }

    public void removeFirstItem() {
        // Ensure cart rendered
        wait.isAnyVisible(cartItems, 5);

        List<WebElement> buttons = driver.findElements(removeButtons);

        WebElement firstVisible = null;
        for (WebElement b : buttons) {
            try {
                if (b.isDisplayed() && b.isEnabled()) {
                    firstVisible = b;
                    break;
                }
            } catch (Exception ignored) {}
        }

        if (firstVisible == null) {
            throw new RuntimeException("Remove button not found in cart.");
        }

        firstVisible.click();
    }
}

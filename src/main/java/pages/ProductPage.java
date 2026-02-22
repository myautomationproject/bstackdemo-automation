package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class ProductPage {

    private WebDriver driver;
    private WaitUtils wait;

    // ✅ REAL Add button (confirmed)
    private By addButtons = By.cssSelector("div.shelf-item__buy-btn");

    // Cart icon
    private By cartIcon = By.cssSelector(".bag");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void addItemsToCart(int howMany) {

        List<WebElement> buttons = wait.waitAllVisible(addButtons, 10);

        if (buttons.size() < howMany) {
            throw new RuntimeException("Not enough products to add. Found: " + buttons.size());
        }

        for (int i = 0; i < howMany; i++) {
            WebElement btn = buttons.get(i);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

            try {
                wait.waitClickable(btn).click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            }
        }
    }

    public void addFirstItemToCart() {
        addItemsToCart(1);
    }

    public void openCart() {
        wait.waitClickable(cartIcon).click();
    }
}

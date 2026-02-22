package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.WaitUtils;

public class AddToCartTest extends BaseTest {

    @Test
    public void TC_004_addSingleItemToCart() {

        // 1) Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("demouser", "testingisfun99");

        WaitUtils wait = new WaitUtils(driver);
        Assert.assertTrue(wait.waitUrlNotContains("/signin"), "Login failed: still on signin");

        // 2) Add first item
        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstItemToCart();

        // 3) Open cart and verify at least 1 item is present
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        int itemsCount = cartPage.getCartItemsCount();

        Assert.assertTrue(itemsCount >= 1, "No items found in cart after adding one item");
    }
}

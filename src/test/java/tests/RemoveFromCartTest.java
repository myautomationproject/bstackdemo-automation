package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.WaitUtils;

public class RemoveFromCartTest extends BaseTest {

    @Test
    public void TC_006_removeItemFromCart() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("demouser", "testingisfun99");

        WaitUtils wait = new WaitUtils(driver);
        Assert.assertTrue(wait.waitUrlNotContains("/signin"), "Login failed: still on signin");

        // Add 2 items
        ProductPage productPage = new ProductPage(driver);
        productPage.addItemsToCart(2);

        // Open cart
        productPage.openCart();
        CartPage cartPage = new CartPage(driver);

        // Verify 2 items
        int before = cartPage.getCartItemsCount();
        Assert.assertEquals(before, 2, "Cart should have 2 items before remove");

        // Remove 1 item
        cartPage.removeFirstItem();

        // Verify now 1 item
        int after = cartPage.getCartItemsCount();
        Assert.assertEquals(after, 1, "Cart should have 1 item after remove");
    }
}

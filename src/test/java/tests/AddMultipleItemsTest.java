package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.WaitUtils;

public class AddMultipleItemsTest extends BaseTest {

    @Test
    public void TC_005_addMultipleItemsToCart_verifyCount() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("demouser", "testingisfun99");

        WaitUtils wait = new WaitUtils(driver);
        Assert.assertTrue(wait.waitUrlNotContains("/signin"), "Login failed: still on signin");

        // Add 2 items (you can change 2 -> 3 later)
        ProductPage productPage = new ProductPage(driver);
        productPage.addItemsToCart(2);

        // Open cart and verify count
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        int count = cartPage.getCartItemsCount();

        Assert.assertEquals(count, 2, "Cart should contain exactly 2 items.");
    }
}

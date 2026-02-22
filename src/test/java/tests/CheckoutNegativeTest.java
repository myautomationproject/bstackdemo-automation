package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.WaitUtils;

public class CheckoutNegativeTest extends BaseTest {

    @Test
    public void TC_008_checkoutWithoutAddingItems() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("demouser", "testingisfun99");

        WaitUtils wait = new WaitUtils(driver);
        Assert.assertTrue(wait.waitUrlNotContains("/signin"), "Login failed");

        // Open cart WITHOUT adding any product
        ProductPage productPage = new ProductPage(driver);
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        int count = cartPage.getCartItemsCount();

        // Cart should be empty
        Assert.assertEquals(count, 0, "Cart should be empty when no items are added");

        // Try clicking Checkout, but verify we do NOT reach shipping form
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.tryClickCheckout();

        Assert.assertFalse(checkoutPage.isOnShippingForm(),
                "Negative test failed: Shipping form opened even though cart is empty");
    }
}

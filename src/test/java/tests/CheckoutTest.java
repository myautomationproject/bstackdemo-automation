package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductPage;
import utils.WaitUtils;

public class CheckoutTest extends BaseTest {

    @Test
    public void TC_007_placeOrderSuccessfully() {

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("demouser", "testingisfun99");

        WaitUtils wait = new WaitUtils(driver);
        Assert.assertTrue(wait.waitUrlNotContains("/signin"), "Login failed");

        // Add item
        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstItemToCart();

        // Open cart
        productPage.openCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartItemsCount() >= 1, "Cart is empty");

        // Checkout
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();

        // Fill form
        checkoutPage.fillShippingDetails();

        // Submit
        checkoutPage.submitOrder();

        // Verify success
        Assert.assertTrue(checkoutPage.isOrderSuccessful(),
                "Order confirmation message not found");
    }
}

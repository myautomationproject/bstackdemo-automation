package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.WaitUtils;

import org.openqa.selenium.By;

public class LoginTest extends BaseTest {

    @Test
    public void TC_001_loginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("demouser", "testingisfun99");

        WaitUtils wait = new WaitUtils(driver);

        // 1) Wait until signing page is gone (proof of navigation)
        boolean movedAwayFromSignin = wait.waitUrlNotContains("/signin");
        Assert.assertTrue(movedAwayFromSignin, "Login failed: still on /signin page");

        // 2) Verify products page loaded (product cards visible)
        By productsContainer = By.cssSelector(".shelf-container, .products, .shelf-item");
        boolean productsVisible = wait.isAnyVisible(productsContainer, 10);

        Assert.assertTrue(productsVisible, "Login maybe failed: products are not visible after login");
    }
}

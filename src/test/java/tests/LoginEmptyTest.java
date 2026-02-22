package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.WaitUtils;

public class LoginEmptyTest extends BaseTest {

    @Test
    public void TC_003_loginWithEmptyUsernamePassword() {

        LoginPage loginPage = new LoginPage(driver);
        WaitUtils wait = new WaitUtils(driver);

        // Go to signin page
        loginPage.clickSignIn();

        // Click Log In without choosing username/password
        loginPage.clickLogin();

        // Must still be on signin
        Assert.assertTrue(wait.waitUrlContains("/signin"),
                "Expected to stay on /signin when username/password is empty.");

        // Error should be visible
        Assert.assertTrue(loginPage.isErrorVisible(),
                "Expected an error message for empty username/password, but none was detected.");
    }
}

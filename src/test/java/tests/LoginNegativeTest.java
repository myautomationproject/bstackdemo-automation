package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.WaitUtils;

public class LoginNegativeTest extends BaseTest {

    @Test
    public void TC_002_loginWithInvalidCredentials() {

        LoginPage loginPage = new LoginPage(driver);

        // Try this invalid combination
        loginPage.login("fav_user", "testingisfun99");

        WaitUtils wait = new WaitUtils(driver);

        // Strict validation: must STILL be on /signin
        boolean stillOnSignin = wait.waitUrlContains("/signin");

        Assert.assertTrue(stillOnSignin,
                "Invalid login FAILED: User was able to log in.");
    }
}

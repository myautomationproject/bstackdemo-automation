package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils wait;

    private By signInBtn = By.id("signin");
    private By usernameDropdown = By.id("username");
    private By passwordDropdown = By.id("password");

    // Login button variants (bstackdemo uses "Log In")
    private By loginBtnById1 = By.id("login");
    private By loginBtnById2 = By.id("login-btn");
    private By loginBtnByText =
            By.xpath("//*[self::button or self::div][normalize-space()='Log In']");
    private By loginBtnByContains =
            By.xpath("//*[self::button or self::div][contains(normalize-space(),'Log In')]");

    // Error message
    private By errorMsg =
            By.cssSelector(".api-error, .error, .error-message, .Toastify__toast-body");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void clickSignIn() {
        wait.waitClickable(signInBtn).click();
        wait.waitUrlContains("/signin");
        wait.waitVisible(usernameDropdown);
    }

    public void selectUsername(String usernameText) {
        wait.waitClickable(usernameDropdown).click();
        wait.waitClickable(By.xpath("//div[text()='" + usernameText + "']")).click();
    }

    public void selectPassword(String passwordText) {
        wait.waitClickable(passwordDropdown).click();
        wait.waitClickable(By.xpath("//div[text()='" + passwordText + "']")).click();
    }

    private WebElement findLoginButton() {
        if (wait.isPresent(loginBtnById2, 2)) return driver.findElement(loginBtnById2);
        if (wait.isPresent(loginBtnById1, 1)) return driver.findElement(loginBtnById1);
        if (wait.isPresent(loginBtnByText, 2)) return driver.findElement(loginBtnByText);
        return wait.waitVisible(loginBtnByContains);
    }

    public void clickLogin() {
        WebElement loginBtn = findLoginButton();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginBtn);

        try {
            wait.waitClickable(loginBtn).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);
        }
    }

    public void login(String usernameText, String passwordText) {
        clickSignIn();
        selectUsername(usernameText);
        selectPassword(passwordText);
        clickLogin();
    }

    // For invalid login verification
    public boolean isErrorVisible() {
        return wait.isAnyVisible(errorMsg, 5);
    }

    public String getPageText() {
        return driver.getPageSource();
    }
}

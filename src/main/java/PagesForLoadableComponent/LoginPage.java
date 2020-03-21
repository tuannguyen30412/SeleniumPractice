package PagesForLoadableComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends LoadableComponent<LoginPage> {
    private String loginUrl = "https://www.n11.com/giris-yap";
    private WebDriver driver;
    private WebDriverWait wait;
    private BasePage basePage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        basePage = new BasePage(this.driver);
    }

    //Web Elements
    By usernameBy = By.id("email");
    By passwordBy = By.id("password");
    By loginButtonId = By.id("loginButton");
    By errorMessageUsernameBy = By.xpath("#loginForm .error:nth-of-type(1) .errorMessage");
    By errorMessagePasswordBy = By.xpath("#loginForm .error:nth-of-type(2) .errorText");

    /**Page Methods*/
    public LoginPage whenIloginToN11(String username, String password) {
       basePage.writeText(usernameBy, username);
       basePage.writeText(passwordBy, password);
        basePage.click(usernameBy);
       basePage.click(loginButtonId);
        return this;
    }

    public LoginPage thenIVerifyLoginUserName(String expectedText) {
        Assert.assertEquals(basePage.readText(errorMessageUsernameBy), expectedText);
        return this;
    }

    public LoginPage thenIVerifyLoginPassword(String expectedText) {
        Assert.assertEquals(basePage.readText(errorMessagePasswordBy), expectedText);
        return this;
    }

    @Override
    protected void load() {
        basePage.page.homePage().goToLoginPage();
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue(driver.getCurrentUrl().contains(loginUrl), "Loginpage is not loaded!");
    }
}

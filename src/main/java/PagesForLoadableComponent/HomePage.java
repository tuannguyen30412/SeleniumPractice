package PagesForLoadableComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends LoadableComponent<HomePage> {
    private String baseUrl = "https://www.n11.com";
    private WebDriver driver;
    private WebDriverWait wait;
    private BasePage basePage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        basePage = new BasePage(this.driver);
    }

    By signInButtonBy = By.className("btnSignIn");

    public LoginPage goToLoginPage() {
        basePage.click(signInButtonBy);
        return new LoginPage(this.driver);
    }

    @Override
    protected void load() {
        this.driver.get(baseUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertTrue( driver.getCurrentUrl().contains(baseUrl), "HomePage is not loaded!");
    }
}

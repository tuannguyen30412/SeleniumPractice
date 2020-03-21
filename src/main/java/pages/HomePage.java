package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    String baseUrl = "http://www.n11.com/";

    By searchButtonClass = By.className("btnSignIn");

    public HomePage goToUrl() {
        driver.get(baseUrl);
        return this;
    }

    public LoginPage goToLoginPage() {
        click(searchButtonClass);
        return new LoginPage(driver);
    }
}

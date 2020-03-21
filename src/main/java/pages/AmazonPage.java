package pages;

import org.openqa.selenium.WebDriver;

public class AmazonPage extends BasePage {

    public AmazonPage(WebDriver driver) {
        super(driver);
    }

    private String url = "https://www.amazon.com";

    public AmazonPage goToUrl() {
        driver.get(url);
        return this;
    }
}

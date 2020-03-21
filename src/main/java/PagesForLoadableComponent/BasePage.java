package PagesForLoadableComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {
    private WebDriver driver;
    public Page page;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        page = new Page();
    }

    //click method
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    //Write Text
    public void writeText (By elementLocation, String text) {
        driver.findElement(elementLocation).sendKeys(text);
    }

    //Read Text
    public String readText (By elementLocation) {
        return driver.findElement(elementLocation).getText();
    }

}

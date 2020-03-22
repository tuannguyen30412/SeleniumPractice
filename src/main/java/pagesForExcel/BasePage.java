package pagesForExcel;

import PagesForPOM.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {

    public BasePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
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

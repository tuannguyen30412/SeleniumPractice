package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //Click method
    public void click(By elementLocation) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).click();
    }
    //Write text
    public void writeText(By elementLocation, String text) {
        waitVisibility(elementLocation);
        driver.findElement(elementLocation).sendKeys(text);
    }
    //Read text
    public String readText(By elementLocation) {
        waitVisibility(elementLocation);
        return driver.findElement(elementLocation).getText();
    }
    //Wait
    public void waitVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}



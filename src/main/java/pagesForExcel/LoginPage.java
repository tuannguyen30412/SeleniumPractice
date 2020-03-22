package pagesForExcel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    //Web Elements
    By userNameId = By.id("email");
    By passwordId = By.id("password");
    By loginButtonId = By.id("loginButton");
    By errorMessageUsernameXpath = By.xpath("//*[@id=\"loginForm\"]/div[1]/div/div");
    By errorMessagePasswordXpath = By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div ");
    /**Page Methods*/
    public LoginPage loginToN11(XSSFRow row) {
        writeText(userNameId,row.getCell(1).toString());
        writeText(passwordId, row.getCell(2).toString());
        click(loginButtonId);
        return this;
    }

    public LoginPage verifyLoginUserName(String expectedText) {
        Assert.assertEquals(readText(errorMessageUsernameXpath), expectedText);
        return this;
    }

    public LoginPage verifyLoginPassword(String expectedText) {
        Assert.assertEquals(readText(errorMessagePasswordXpath), expectedText);
        return this;
    }
}

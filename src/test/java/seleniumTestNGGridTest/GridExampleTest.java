package seleniumTestNGGridTest;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridExampleTest {
    static WebDriver driver;

    @BeforeClass
    public static void setupTest() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities caps = new DesiredCapabilities();
        options.setCapability("platform", Platform.WINDOWS);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }
    @Test
    public void T01_FacebookLogin() {
        driver.navigate().to("https://www.facebook.com");
        driver.manage().window().maximize();

        WebElement loginButton = driver.findElement(By.id("loginbutton"));

        loginButton.click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

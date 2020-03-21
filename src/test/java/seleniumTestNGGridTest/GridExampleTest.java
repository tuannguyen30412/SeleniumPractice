package seleniumTestNGGridTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;

public class GridExampleTest {
    static WebDriver driver;

    @BeforeClass
    public static void setupTest() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        caps.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), firefoxOptions);
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

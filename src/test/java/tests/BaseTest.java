package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.AmazonPage;
import pages.HomePage;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public AmazonPage amazonPage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void classLevelSetup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage(driver);
        amazonPage = new AmazonPage(driver);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

package testsForPOM;

import PagesForPOM.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
//    public JavascriptExecutor js;
    public Page page;

    @BeforeMethod
    public void setup () {
        //Create a Chrome driver. All test classes use this.
        driver = new ChromeDriver();

        //Create a wait. All test classes use this.
        wait = new WebDriverWait(driver,15);

        //JSExecutor
//        js = (JavascriptExecutor) driver;

        //Maximize Window
        driver.manage().window().maximize();

        //Instantiate the Page Class
        page = new Page(driver,wait);
    }

    @AfterMethod
    public void teardown () {
        driver.quit();
    }
}

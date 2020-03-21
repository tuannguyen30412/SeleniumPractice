package testForLoadableComponent;

import PagesForLoadableComponent.Page;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilsForLoadableComponent.ThreadLocalDriver;

public class BaseTest {
    public WebDriverWait wait;
    public Page page;

    @BeforeMethod(description = "Class Level Setup")
    @Parameters(value = {"browsers"})
    public void globalSetup (@Optional String browser) {
        //Set ThreadLocal Driver
        ThreadLocalDriver.setDriver(browser);

        //Create a wait. All test classes use this.
        wait = new WebDriverWait(ThreadLocalDriver.getDriver(), 15);

        //Instantiate the Page Class
        page = new Page();
    }

    @AfterClass(description = "Class Level Teardown")
    public void globalTeardown () {
        ThreadLocalDriver.getDriver().quit();
    }
}

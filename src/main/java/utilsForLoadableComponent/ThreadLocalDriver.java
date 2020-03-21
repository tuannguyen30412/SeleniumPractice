package utilsForLoadableComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ThreadLocalDriver {

    //Declare a ThreadLocal AndroidDriver for Thread-Safe executions to run tests in parallel without any problem.
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    //set driver to tlDriver
    public synchronized static void setDriver(String browser) {
        if(browser.equals("firefox")) {
            tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(OptionsManager.getFirefoxOptions()));
        } else if(browser.equals("chrome")) {
            tlDriver = ThreadLocal.withInitial(() -> new ChromeDriver(OptionsManager.getChromeOptions()));
        }
    }

    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }

}

package utils.Waiters;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSWaiter {
    public WebDriverWait wait;

    public JSWaiter(WebDriverWait wait) {
        this.wait = wait;
    }

    public void waitJS() {
        //Wait for JS to load
        ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")
                .toString().equals("Complete");
        //JQuery wait
        ExpectedCondition<Boolean> jQueryLoad = webDriver -> ((Long)((JavascriptExecutor) webDriver)
                .executeScript("return jQuery.active") == 0);
        wait.until(jsLoad);
        wait.until(jQueryLoad);
    }
}

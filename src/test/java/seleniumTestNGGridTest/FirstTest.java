package seleniumTestNGGridTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void GOOGLE1() {
        System.out.println("Google1 Test Started" + "Thread ID: " + Thread.currentThread().getId());
        getDriver().navigate().to("http://www.google.com");
        System.out.println("Google1 Test Page tile is " + getDriver().getTitle()
                + "Thread ID: " + Thread.currentThread().getId());
        Assert.assertEquals(getDriver().getTitle(), "Google");
        System.out.println("Google1 Test Ended"  + "Thread ID: " + Thread.currentThread().getId());
    }

    @Test
    public void GOOGLE2() {
        System.out.println("Google2 Test Started" + "Thread ID: " + Thread.currentThread().getId());
        getDriver().navigate().to("http://www.google.com");
        System.out.println("Google2 Test Page tile is " + getDriver().getTitle()
                + "Thread ID: " + Thread.currentThread().getId());
        Assert.assertEquals(getDriver().getTitle(), "Google");
        System.out.println("Google2 Test Ended"  + "Thread ID: " + Thread.currentThread().getId());
    }

    @Test
    public void GOOGLE3() {
        System.out.println("Google3 Test Started" + "Thread ID: " + Thread.currentThread().getId());
        getDriver().navigate().to("http://www.google.com");
        System.out.println("Google3 Test Page tile is " + getDriver().getTitle()
                + "Thread ID: " + Thread.currentThread().getId());
        Assert.assertEquals(getDriver().getTitle(), "Google");
        System.out.println("Google3 Test Ended"  + "Thread ID: " + Thread.currentThread().getId());
    }
}

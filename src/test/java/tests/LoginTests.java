package tests;

import com.aventstack.extentreports.testng.listener.ExtentIReporterSuiteClassListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import java.lang.reflect.Method;

@Listeners({ExtentITestListenerClassAdapter.class, ExtentIReporterSuiteClassListenerAdapter.class})
public class LoginTests extends BaseTest {
    @Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.",
            groups = { "Extent Framework", "t:TestNG", "author:Tuan Nguyen", "device:Windows" })
    public void invalidLoginTest_InvalidUserNameAndPassword(Method method) {
//        ExtentTestManager_old.startTest(method.getName(), "Invalid Login Scenario with wrong username and password.");
        homePage.goToUrl().goToLoginPage().loginToN11("onur@swtestacademy.com", "11223344")
                .verifyLoginPassword("E-posta adresiniz veya şifreniz hatalı");
    }
    @Test(priority = 1, description = "Invalid Login Scenario with empty username and password.",
            groups = { "Extent Framework", "t:TestNG", "author:Tuan Nguyen", "device:Mac" })
    public void invalidLoginTest_EmptyUserNameAndPassword(Method method) {
//      ExtentTestManager_old.startTest(method.getName(), "Invalid Login Scenario with empty username and password.");
        homePage.goToUrl().goToLoginPage().loginToN11("", "")
                .verifyLoginUserName("Lütfen e-posta adresinizi girin.")
                .verifyLoginPassword("WRONG MESSAGE FOR FAILURE!");
    }
}

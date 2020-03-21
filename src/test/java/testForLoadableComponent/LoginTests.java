package testForLoadableComponent;

import PagesForPOM.HomePage;
import PagesForPOM.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private String wrongUsername = "onur@swtestacademy.com";
    private String wrongPassword = "11223344";

    @Test(description = "Invalid Login Scenario with wrong username and password.")
    public void invalidLoginTest_InvalidUserNameInvalidPassword (){
        page.loginPage().get()
                .whenIloginToN11(wrongUsername, wrongPassword)
                .thenIVerifyLoginPassword("E-posta adresiniz veya şifreniz hatalı");
    }

    @Test (description = "Invalid Login Scenario with empty username and password.")
    public void invalidLoginTest_EmptyUserEmptyPassword () throws InterruptedException {
        page.loginPage().get()
                .whenIloginToN11("","")
                .thenIVerifyLoginUserName("Lütfen e-posta adresinizi girin.")
                .thenIVerifyLoginPassword("Bu alanın doldurulması zorunludur.");
    }

}

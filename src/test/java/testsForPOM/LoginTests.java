package testsForPOM;

import PagesForPOM.HomePage;
import PagesForPOM.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test(priority = 0)
    public void invalidLoginTest_InvalidUserNameInvalidPassword () throws InterruptedException {

        //*************PAGE METHODS WITH JAVA GENERICS********************
        //Open N11 HomePage
        page.getInstance(HomePage.class).goToUrl();

        //Go to LoginPage
        page.getInstance(HomePage.class).goToLoginPage();

        //Login to N11
        page.getInstance(LoginPage.class).loginToN11("onur@swtestacademy.com", "11223344");

        //*************ASSERTIONS***********************
        //Thread.sleep(500); //It is better to use explicit wait here.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='E-posta adresiniz veya şifreniz hatalı']")));
        page.getInstance(LoginPage.class).verifyLoginPassword(("E-posta adresiniz veya şifreniz hatalı"));
    }

    @Test (priority = 1)
    public void invalidLoginTest_EmptyUserEmptyPassword () throws InterruptedException {
        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(driver,wait);
        LoginPage loginPage = new LoginPage(driver,wait);

        //*************PAGE METHODS********************
        homePage.goToUrl();
        homePage.goToLoginPage();
        loginPage.loginToN11("","");

        //*************ASSERTIONS***********************
        //Thread.sleep(500); //It is better to use explicit wait here.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Lütfen e-posta adresinizi girin.']")));
        page.getInstance(LoginPage.class).verifyLoginUserName("Lütfen e-posta adresinizi girin.");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Bu alanın doldurulması zorunludur.']")));
        page.getInstance(LoginPage.class).verifyLoginPassword("Bu alanın doldurulması zorunludur.");
    }

}

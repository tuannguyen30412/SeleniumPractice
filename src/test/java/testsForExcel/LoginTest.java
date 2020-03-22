package testsForExcel;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pagesForExcel.HomePage;
import pagesForExcel.LoginPage;
import utils.Excel.ExcelUtil;
import utils.Listeners.TestListeners_old;

import java.io.IOException;

@Listeners({ TestListeners_old.class })
public class LoginTest extends BaseTest {

    @BeforeTest
    public void setupTestData () throws IOException {
        //Set Test Data Excel and Sheet
        System.out.println("************Setup Test Level Data**********");
        ExcelUtil.setExcelFileSheet("LoginData");
    }

    @Test(priority = 0, description="Invalid Login Scenario with wrong username and password.")
    public void invalidLoginTest_InvalidUserNameInvalidPassword () throws InterruptedException {
        //extentreports Description
//        ExtentTestManager_old.getTest().setDescription("Invalid Login Scenario with wrong username and password.");

        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(driver,wait);
        LoginPage loginPage = new LoginPage(driver,wait);

        //*************PAGE METHODS********************
        //Open N11 HomePage
        homePage.goToUrl();

        //Go to LoginPage
        homePage.goToLoginPage();

        //Login to N11 with first row of the login data
        loginPage.loginToN11(ExcelUtil.getRowData(1));

        //Set test row number to 1
        ExcelUtil.setRowNumber(1);

        //Set Test Status Column number to 5
        ExcelUtil.setColumnNumber(5);

        //*************ASSERTIONS***********************
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div")));
        //Verify password message by using excel's 2st row, 5th column
        //Row and Column numbers starting from 0. Thus we need to write 1 and 4, instead of 2 and 5!
        loginPage.verifyLoginPassword(ExcelUtil.getCellData(1,4));
    }

    @Test (priority = 1, description="Invalid Login Scenario with empty username and password.")
    public void invalidLoginTest_EmptyUserEmptyPassword () throws InterruptedException {
        //extentreports Description
//        ExtentTestManager_old.getTest().setDescription("Invalid Login Scenario with empty username and password.");

        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(driver,wait);
        LoginPage loginPage = new LoginPage(driver,wait);

        //*************PAGE METHODS********************
        homePage.goToUrl();
        homePage.goToLoginPage();

        //Login to N11 with second row of the login data
        loginPage.loginToN11(ExcelUtil.getRowData(2));

        //Set test row number to 2
        ExcelUtil.setRowNumber(2);

        //Set Test Status column number to 5
        ExcelUtil.setColumnNumber(5);

        //*************ASSERTIONS***********************
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginForm\"]/div[1]/div/div")));
        //Verify by 3rd row and 4th column
        loginPage.verifyLoginUserName(ExcelUtil.getCellData(2,3));
        //Verify by 3rd row and 5th column
        loginPage.verifyLoginPassword(ExcelUtil.getCellData(2,4));
    }
}

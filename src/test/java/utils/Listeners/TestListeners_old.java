package utils.Listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import tests.BaseTest;
import utils.ExtentReports.ExtentManager;
import utils.ExtentReports.ExtentTestManager_old;

public class TestListeners_old extends BaseTest implements ITestListener{

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("I am in onTestStart method" + getTestMethodName(result) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("I am in onTestSuccess method" + getTestMethodName(result) + " succeed");
        ExtentTestManager_old.getTest().log(Status.PASS, "Test passed");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("I am in onTestFailure method" + getTestMethodName(result));
        //get driver from BaseTest and assign to local webDriver variable
        Object testClass = result.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
        //ExtentReport log and screenshot operations for failed tests
        ExtentTestManager_old.getTest().log(Status.FAIL, "Test failed",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("I am in onTestSkipped method" + getTestMethodName(result) + " skipped");
        ExtentTestManager_old.getTest().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but it is in defined success ratio" + getTestMethodName(result));
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("Test failed with Timeout" + getTestMethodName(result));
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("I am onStart method" + context.getName());
        context.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("I am onFInish method" + context.getName());
        ExtentTestManager_old.endTest();
        ExtentManager.getReporter().flush();
    }


}

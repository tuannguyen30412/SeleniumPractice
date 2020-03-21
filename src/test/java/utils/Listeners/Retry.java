package utils.Listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.ExtentReports.ExtentTestManager_old;

import java.io.IOException;

public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = 1; //Run the failed test 2 times

    @SneakyThrows
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(!iTestResult.isSuccess()) {
            if(count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                extentReportsFailOperations(iTestResult);
                return true;
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    public void extentReportsFailOperations(ITestResult iTestResult) throws IOException {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager_old.getTest().log(Status.FAIL, "Test failed",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }
}

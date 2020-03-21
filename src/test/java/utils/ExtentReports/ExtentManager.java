package utils.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

   public static synchronized  ExtentReports getReporter() {
        if(extent == null) {
            String workingDir = System.getProperty("user.dir");
            if(System.getProperty("os.name").toLowerCase().contains("win")) {
                extent = new ExtentReports();
            }
            else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                extent = new ExtentReports();
            }
        }
        return extent;
    }
}


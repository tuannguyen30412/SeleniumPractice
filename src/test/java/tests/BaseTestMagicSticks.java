package tests;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.im4java.process.StandardStream;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utils.Waiters.JSWaiter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BaseTestMagicSticks {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    JSWaiter jsWaiter;

    public String testName;
    public String testScreenShotDirectory;
    public String url = "http://www.kariyer.net";

    //Main Screenshot dir
    public String currentDir = System.getProperty("user.dir");
    public String parentScreenShotsLocation = currentDir + "\\MagickScreenshots\\";
    public String parentDifferencesLocation = currentDir + "\\Differences\\";

    //Element Screenshot paths
    public String baselineScreenShotPath;
    public String actualScreenShotPath;
    public String differenceScreenShotPath;

    //Image Files
    public File baselineImageFile;
    public File actualImageFile;
    public File differenceImageFile;
    public File differenceFileForParent;

    @BeforeClass
    public void setupTestClass() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor) driver;
        jsWaiter = new JSWaiter(wait);

        //Create screenshot and differences folders if they do not exist
        createFolder(parentDifferencesLocation);
        createFolder(parentScreenShotsLocation);

        //Clean Differences Root folder
        File differencesFolder = new File(parentDifferencesLocation);
        FileUtils.cleanDirectory(differencesFolder);

        //go to URL
        driver.navigate().to(url);

        //Add cookie for top banner
        addCookieForTopBanner();
    }

    @BeforeMethod
    public void setUpTestMethod(Method method) {
        //Get the test name to create a specific screenshot folder for each test
        testName = method.getName();
        System.out.println("Test Name: " + testName + "\n");
        //Create a specific directory for a test
        testScreenShotDirectory = parentScreenShotsLocation + testName + "\\";
        createFolder(testScreenShotDirectory);
        //Declare element screenshot paths
        //Concatenate with the test name
        declareScreenShotPaths(testName + "_Baseline.png", testName + "_Actual.png", testName + "_Diff.png");
    }

    //Add Cookie not to see top banner animation
    public void addCookieForTopBanner() {
        //Get Next Month Last Data for cookie expiration
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date nextMonthLastDay = calendar.getTime();

        //Create/Build a cookie
        Cookie topBannerCloseCookie = new Cookie.Builder("AA-kobiBannerClosed", "4") //Name & value pair of the cookie
                .domain("http://www.kariyer.net") //domain of cookie
                .path("/") //path of cookie
                .expiresOn(nextMonthLastDay) //expiration date
                .build();

        //add a cookie
        driver.manage().addCookie(topBannerCloseCookie);
    }

    //Create Folder method
    public void createFolder(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created.");
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists " + path);
        }
    }

    //Close popup if exists
    public void handlePopup(String selector) throws InterruptedException {
        jsWaiter.waitJS();
        List<WebElement> popup = driver.findElements(By.cssSelector(selector));
        if (!popup.isEmpty()) {
            popup.get(0).click();
            sleep(200);
        }
    }

    //Close banner
    public void closeBanner() {
        jsWaiter.waitJS();
        List<WebElement> banner = driver.findElements(By.cssSelector("body > div.kobi-head-banner > div > a"));
        if (!banner.isEmpty()) {
            banner.get(0).click();
            sleep(2000);
        }
    }

    //Unhide an element with JSExecutor
    public void unhideElement(String unhideJS) {
        js.executeScript(unhideJS);
        jsWaiter.waitJS();
        sleep(200);
    }

    //Move to Operation
    public void moveToElement(WebElement element) {
        jsWaiter.waitJS();
        Actions actions = new Actions(driver);
        jsWaiter.waitJS();
        sleep(200);
        actions.moveToElement(element).build().perform();
    }

    //Take Screenshot with Ashot
    public Screenshot takeScreenshot(WebElement element) {
        Screenshot elementScreenshot = new AShot().takeScreenshot(driver, element);
    /*    Screenshot elementScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, element);*/
        //print element sizes
        String size = "Height: " + elementScreenshot.getImage().getHeight() + "\n"
                + "Width: " + elementScreenshot.getImage().getWidth() + "\n";
        System.out.println("Size: " + size);
        return elementScreenshot;
    }

    //Write
    public void writeScreenshotToFolder(Screenshot screenshot, File imageFile) throws IOException {
        ImageIO.write(screenshot.getImage(), "PNG", imageFile);
    }

    //Screenshot paths
    public void declareScreenShotPaths(String baseline, String actual, String diff) {
        //Baseline, Actual, Difference Image paths:
        baselineScreenShotPath = testScreenShotDirectory + baseline;
        baselineScreenShotPath = testScreenShotDirectory + actual;
        baselineScreenShotPath = testScreenShotDirectory + diff;
        //Baseline, Actual Image Files
        baselineImageFile = new File(baselineScreenShotPath);
        actualImageFile = new File(actualScreenShotPath);
        differenceImageFile = new File(differenceScreenShotPath);
        //For copying difference to the parent Difference Folder
        differenceFileForParent = new File(parentDifferencesLocation + diff);
    }

    //ImageMagick Compare Method
    public void compareImagesWithImageMagick(String expected, String actual, String difference) throws Exception {
        // This class implements the processing of os-commands using a ProcessBuilder.
        // This is the core class of the im4java-library where all the magic takes place.
        ProcessStarter.setGlobalSearchPath("F:\\ImageMagick\\ImageMagick-7.0.10-Q16");
        //This instance wraps the compare command
        CompareCmd compare = new CompareCmd();
        //Set the ErrorConsumer for the stderr of the ProcessStarter
        compare.setErrorConsumer(StandardStream.STDERR);
        //Create ImageMagick Operation Object
        IMOperation cmpOp = new IMOperation();
        //Add option -fuzz to the ImageMagick commandline
        //With Fuzz we can ignore small changes
        cmpOp.fuzz(10.0);
        //The special "-metric" setting of 'AE' (short for "Absolute Error" count), will report (to standard error),
        //a count of the actual number of pixels that were masked, at the current fuzz factor.
        cmpOp.metric("AE");
        // Add the expected image
        cmpOp.addImage(expected);
        // Add the actual image
        cmpOp.addImage(actual);
        // This stores the difference
        cmpOp.addImage(difference);
        try {
            //Do the compare
            System.out.println("Comparison Started!");
            compare.run(cmpOp);
            System.out.println("Comparison Finished!");
        } catch (Exception ex) {
            System.out.print(ex);
            System.out.println("Comparison Failed!");
            //Put the difference image to the global differences folder
            Files.copy(differenceImageFile, differenceFileForParent);
            throw ex;
        }

    }

    //Compare Operation
    public void doComparison(Screenshot elementScreenshot) throws Exception {
        //Did we capture baseline image before?
        if(baselineImageFile.exists()) {
            //Compare screenshot with baseline
            System.out.println("Comparison method will be called!\n");
            System.out.println("Baseline: " + baselineScreenShotPath + "\n" +
                    "Actual: " + actualScreenShotPath + "\n" +
                    "Difference: " + differenceScreenShotPath);
            compareImagesWithImageMagick(baselineScreenShotPath, actualScreenShotPath, differenceScreenShotPath);
        } else {
            System.out.println("BaselineScreenshot does not exist! We put it into test screenshot folder.\n");
            //Put the screenshot to the specified folder
            ImageIO.write(elementScreenshot.getImage(), "PNG", baselineImageFile);
        }
    }

    //sleep function
    public void sleep(int millis) {
        Long milliseconds = (long) millis;
        try {
                Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

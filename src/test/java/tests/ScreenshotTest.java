package tests;

import io.cucumber.java.bs.A;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

public class ScreenshotTest extends BaseTest {

    private String currentDir = System.getProperty("user.dir");

    //GetScreenshot method directory and Image File
    private File getScreenShotMethodImageFile = new File(currentDir + "\\src\\test\\java\\utils\\Screenshot\\GetScreenshotMethods\\amazonScreenshot.png");

    //Element Screenshot directory and Image File
    private File webElementImageFile1 = new File(currentDir + "\\src\\test\\java\\utils\\Screenshot\\Ashot\\WebElement\\logo1.png");
    //Element Screenshot directory and Image File
    private File webElementImageFile2 = new File(currentDir + "\\src\\test\\java\\utils\\Screenshot\\Ashot\\WebElement\\logo2.png");
    //Entire page Screenshot directory and Image File
    private File entirePageImageFile = new File(currentDir + "\\src\\test\\java\\utils\\Screenshot\\Ashot\\EntirePage\\entirePage.png");

    @Test(priority = 0)
    public void screenshotGetScreensShotAs() throws IOException {
        amazonPage.goToUrl();
        //Take Screenshot of viewable area
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Write Screenshot to a file
        FileUtils.copyFile(srcFile, getScreenShotMethodImageFile);
    }

    @Test(priority = 1)
    public void screenshotWebElement1() throws IOException {
        amazonPage.goToUrl();
        //Find element
        WebElement logo = driver.findElement(By.cssSelector("[id=nav-logo] .nav-logo-link .nav-logo-base"));
        // Get viewable area's screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImage = ImageIO.read(screenshot);
        //Get the location of element on the page
        Point point = logo.getLocation();
        //Get width and height of the element
        int elementWidth = logo.getSize().getWidth();
        int elementHeight = logo.getSize().getHeight();
        //Crop element from viewable area's screenshot to get element's screenshot
        BufferedImage elementScreenshot = fullImage.getSubimage(point.getX(), point.getY(), elementWidth, elementHeight);
        ImageIO.write(elementScreenshot, "png", screenshot);
        //Write screenshot to a file
        FileUtils.copyFile(screenshot, webElementImageFile1);
    }
    @Test(priority = 1)
    public void screenshotWebElement2() {
        amazonPage.goToUrl();
        //Find element
        WebElement logo = driver.findElement(By.cssSelector(".nav-left #nav-logo"));
        Screenshot elementScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, logo);
        try{
            ImageIO.write(elementScreenshot.getImage(), "PNG", webElementImageFile2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void screenshotEntirePageAshot() throws IOException {
        amazonPage.goToUrl();
        //Take screenshot of entire page by Ashot
        Screenshot entirePageScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        //Write screenshot to a file
        ImageIO.write(entirePageScreenshot.getImage(), "PNG", entirePageImageFile);
    }
 }

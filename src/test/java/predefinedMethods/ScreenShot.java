package predefinedMethods;

import utilities.screenshotClass;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.cucumber.listener.Reporter;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ScreenShot {

    WebDriver driver;
    private screenshotClass util = new screenshotClass();
    private static final String exceptionmessage = "this is failed because of exception ";

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to take screen shot and save in ./Screenshots folder
     */
    public void takeScreenShot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Calendar cal = Calendar.getInstance();

        File currentDirFile = new File("Screenshots");

        String path = currentDirFile.getAbsolutePath();

        FileUtils.copyFile(scrFile, new File(path + "/screenshot" + dateFormat.format(cal.getTime()) + ".png"));
    }

    public void catchScreenshot(Exception e) throws IOException {
        Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
        driver.quit();
        Assert.fail(exceptionmessage + e.getMessage());
    }
}

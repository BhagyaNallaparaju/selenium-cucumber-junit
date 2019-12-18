package reuseability;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.cucumber.listener.Reporter;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ReusableMethods {

    static File locatorfile = new File("src/main/resources/configfiles/locator.Properties");
    static File inputdatafile = new File("src/main/resources/configfiles/inputdata.Properties");
    public static String browser = setValue("browser");
    public static String endpoint = setValue("endpoint");

    public static String readConfig(String key) {
        Properties prop = null;
        String path="src/main/resources/configfiles/";
        String propertiesfile = null;
        FileInputStream fis = null;
        try {
            if(System.getProperty("env") == null) {
                propertiesfile = "config.properties";
            }
            else if(System.getProperty("env").equalsIgnoreCase("it")) {
                propertiesfile=System.getProperty("env")+"_config.properties";
            }
            else{
               throw new Exception("Provide valid env such as prod/it/uat/dev");
            }
            String configPath=path+propertiesfile;
            File configfile = new File(configPath);
            fis = new FileInputStream(configfile);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        prop = new Properties();
        try {
            prop.load(fis);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return prop.getProperty(key);
    }

    public static String readlocatorConfig(String key) {
        Properties prop = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(locatorfile);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
        prop = new Properties();
        try {
            prop.load(fis);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return prop.getProperty(key);
    }

    public String readdataConfig(String key) {
        Properties prop = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(inputdatafile);
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        }
        prop = new Properties();
        try {
            prop.load(fis);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
        return prop.getProperty(key);
    }

    public static String setValue(String property) {
        return System.getProperty(property) == null ? readConfig(property) : System.getProperty(property);
    }
    public static RemoteWebDriver getDriver() {
        try {
            setEnvironmentVariables();
            System.out.println("browser "+browser);
            switch (browser.toLowerCase()) {
                case "firefox":
                    return new FirefoxDriver(firefoxOptions());
                case "ie":
                    return new InternetExplorerDriver(ieOptions());
                case "safari":
                    return new SafariDriver(safariOptions());
                default:
                    System.out.println("chrome ");
                    return new ChromeDriver(chromeOptions());
            }
        }
        catch (Throwable err) {
            Reporter.addStepLog("Driver is not initialized" + ": " + err);
            Assert.fail("ERROR : " + "Driver is not initialized" + err);
        }
        return null;
    }

    private static void setEnvironmentVariables() {

        endpoint = endpoint.toLowerCase().trim();
        if(endpoint==null || endpoint.equals("local"))
        {
            endpoint="local";
        }
        else {
            Assert.fail("Error, Please provide valid endpoint as local");
        }
        System.setProperty("BrowserWidth", readConfig(  "desktop_browser_width"));
        System.setProperty("BrowserHeight", readConfig(  "desktop_browser_height"));
    }

    private static FirefoxOptions firefoxOptions() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setCapability("marionette", true);
        ffOptions.addArguments("--width=" + Integer.parseInt(System.getProperty("BrowserWidth")));
        ffOptions.addArguments("--height=" + Integer.parseInt(System.getProperty("BrowserHeight")));
        return ffOptions;
    }

    private static ChromeOptions chromeOptions() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
            "window-size=" + Integer.parseInt(System.getProperty("BrowserWidth")) + "," + Integer
                .parseInt(System.getProperty("BrowserHeight")));
        return chromeOptions;
    }

    private static InternetExplorerOptions ieOptions() {
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        ieOptions.ignoreZoomSettings();
        ieOptions.requireWindowFocus();
        return ieOptions;
    }

    private static SafariOptions safariOptions() {
        SafariOptions safariOptions = new SafariOptions();
        return safariOptions;
    }
    public String newDateText() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        return sdf.format(new Date());
    }
    public WebDriverWait wait(WebDriver driver) {
        return new WebDriverWait(driver, 60);
    }
}

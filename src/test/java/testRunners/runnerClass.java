package testRunners;

import cucumber.api.CucumberOptions;
import reuseability.ReusableMethods;

import java.io.File;
import java.io.IOException;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(ExtendedCucumber.class)
@CucumberOptions(
    plugin = {"com.cucumber.listener.ExtentCucumberFormatter:",
        "pretty",
        "html:target/cucumber-html-report"},
    features = {"features"},
    glue = {"stepDefinition"},
    //tags = {"@TEST-Widgets-01"},
    dryRun = false,
    monochrome = true)
public class runnerClass {
  @BeforeClass
  public static void setup() throws IOException {
    System.out.println("entered class");
    ReusableMethods res = new ReusableMethods();
    String timeStamp = res.newDateText();
    String reportPath = res.readConfig("TestReport") + timeStamp + ".html";
    File file = new File(reportPath);
    if (!file.exists()) {
      FileUtils.touch(file);
    }
    ExtentProperties extentProperties = ExtentProperties.INSTANCE;
    extentProperties.setReportPath(reportPath);
  }

  @AfterClass
  public static void endTests() throws IOException {
    Reporter.loadXMLConfig(new File("extent-config.xml"));
    Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
    Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
    Reporter.setSystemInfo("Machine", System.getProperty("os.name"));
    Reporter.setSystemInfo("Browser", "Google Chrome");
    Reporter.setSystemInfo("Java Version", System.getProperty("java.runtime.version"));
    Reporter.setTestRunnerOutput("Chrome-Browser-Test-Report");
  }
}

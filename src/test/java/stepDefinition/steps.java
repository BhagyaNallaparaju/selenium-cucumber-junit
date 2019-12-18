package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import predefinedMethods.AssertionMethods;
import predefinedMethods.ClickElement;
import predefinedMethods.ExplicitWait;
import predefinedMethods.InputMethod;
import predefinedMethods.NavigateMethods;
import predefinedMethods.ScreenShot;
import reuseability.ReusableMethods;

import java.util.concurrent.TimeUnit;

import javax.swing.*;

import com.cucumber.listener.Reporter;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class steps {
  private RemoteWebDriver driver;
  private ReusableMethods res = new ReusableMethods();
  private ClickElement clickelementmethods = null;
  private ExplicitWait explicitWait = null;
  private InputMethod inputObj = null;
  private NavigateMethods navigationObj = null;
  private AssertionMethods assertionMethods = null;
  private ScreenShot screenShot = null;
  private final String type = ReusableMethods.readlocatorConfig("type").toString();

  @Before
  public void beforeScenario(Scenario scenario) {
    driver = ReusableMethods.getDriver();
    this.clickelementmethods = new ClickElement(this.driver);
    this.inputObj = new InputMethod(this.driver);
    this.explicitWait=new ExplicitWait(this.driver);
    this.navigationObj = new NavigateMethods(this.driver);
    this.assertionMethods = new AssertionMethods(this.driver);
    this.screenShot=new ScreenShot(this.driver);
  }
  @After
  public void afterScenario(Scenario scenario) throws InterruptedException {
    Thread.sleep(200);
    driver.quit();
    Thread.sleep(80);
  }

  @Given("^User navigate to \"([^\"]*)\"$")
  public void user_navigate_to(String link) throws Throwable {
    try {
        driver.get(res.readConfig(link.toLowerCase()));
        Reporter.addStepLog("User navigated to test URL: " + link);
    } catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
  }


  @Then("^User clicks on \"([^\"]*)\"$")
  public void user_clicks_on(String accessName) throws Throwable {
    try {
      String arg1 = res.readlocatorConfig(accessName.toLowerCase()).toString();
      clickelementmethods.click(type, arg1);
      Reporter.addStepLog("User clicked on option: " + accessName);
    }catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
    driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
    Thread.sleep(500);
  }

  @And("^Verify \"([^\"]*)\" should be visible on the browser$")
  public void verify_should_be_visible_on_the_browser(String locator) throws Throwable {
    try{
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(res.readlocatorConfig(locator.toLowerCase()))));
    }
    catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
  }

  @Then("^Verify attribute \"([^\"]*)\" for \"([^\"]*)\"$")
  public void verify_attribute_for(String attribute, String locator) throws Throwable {
    try{
      String accessName=res.readlocatorConfig(locator.toLowerCase());
      String getAttributeValue =driver.findElement(By.xpath(accessName)).getAttribute("class");
      if(getAttributeValue.contains(attribute))
      {
        Reporter.addStepLog("getAttributeValue"+getAttributeValue);
      }
      else {
        Assert.fail("Attribute is not selected");
      }
    }
    catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
  }

  @Then("^User wait for (\\d+) sec$")
  public void user_wait_for_sec(String time) throws Throwable {
    explicitWait.waitFor(time);
  }

  @Then("^Verify resizable options$")
  public void verify_resizable_options() throws Throwable {
    try{
      String accessName=res.readlocatorConfig("resize");
      WebElement resizable=driver.findElement(By.xpath(accessName));
      new Actions(driver).dragAndDropBy(resizable,200,10).build().perform();
    }
    catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
  }

  @Then("^Verify droppable options$")
  public void verify_droppable_options() throws Throwable {
    try{
      WebElement source=driver.findElement(By.xpath(res.readlocatorConfig("sourcelocation")));
      WebElement destination=driver.findElement(By.xpath(res.readlocatorConfig("destinationlocation")));
      new Actions(driver).dragAndDrop(source,destination).build().perform();
    }
    catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
  }

  @Then("^Verify \"([^\"]*)\" message from \"([^\"]*)\"$")
  public void verify_message_from(String expectedMessage, String locator) throws Throwable {
    try{
      WebElement accessName= driver.findElement(By.xpath(res.readlocatorConfig(locator.toLowerCase())));
      String actualMessage=accessName.getText();
      Assert.assertEquals("Message should be ",expectedMessage,actualMessage);
    }
    catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
  }

  @Then("^User select \"([^\"]*)\" optionby \"([^\"]*)\" from dropdown \"([^\"]*)\"$")
  public void user_select_optionby_from_dropdown(String optionvalue, String optionBy,String locator) throws Throwable {
    try {
      String accessName = res.readlocatorConfig(locator);
      System.out.println("optionvalue "+optionvalue);
      System.out.println("optionBy "+optionBy);
      System.out.println("accessName "+accessName);
      inputObj.selectOptionFromDropdown(type, optionBy, optionvalue, accessName);
      Reporter.addStepLog("Drop down option selected: " + optionvalue);
    } catch (Exception e) {
      screenShot.catchScreenshot(e);
    }
  }
}

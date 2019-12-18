package predefinedMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
    WebDriver driver;

    public ExplicitWait(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitFor(String time) throws NumberFormatException, InterruptedException {
        Thread.sleep(Integer.parseInt(time) * 200);
        Thread.sleep(700);
    }

    /**
     * Method to Explicitly wait for element to be displayed
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @param duration   : String : Time to wait for element to be displayed
     */
    public void waitForElementToDisplay(String accessType, String accessName, String duration) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        By byEle = selectelementbytype.getelementbytype(accessType, accessName);
        WebDriverWait wait = (new WebDriverWait(driver, Integer.parseInt(duration) * 20));
        wait.until(ExpectedConditions.presenceOfElementLocated(byEle));
    }

    /**
     * Method to Explicitly wait for element to be enabled=click
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @param duration   : String : Time to wait for element to be clickable
     */
    public void waitForElementToClick(String accessType, String accessName, String duration) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        By byEle = selectelementbytype.getelementbytype(accessType, accessName);
        WebDriverWait wait = (new WebDriverWait(driver, Integer.parseInt(duration) * 700));
        wait.until(ExpectedConditions.elementToBeClickable(byEle));
    }
}

package predefinedMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AlertMethod {

    WebDriver driver;

    public AlertMethod(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to handle alert
     *
     * @param decision : String : Accept or dismiss alert
     */
    public void handleAlert(String decision) {
        if (decision.equals("accept")) {
            driver.switchTo().alert().accept();
        } else {
            driver.switchTo().alert().dismiss();
        }
    }
}

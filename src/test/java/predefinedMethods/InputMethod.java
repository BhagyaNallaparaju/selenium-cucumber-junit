package predefinedMethods;

import java.util.List;

import com.cucumber.listener.Reporter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class InputMethod {

    private WebElement dropdown = null;
    private Select selectList = null;
    private final String selectbyindex = "selectByIndex";
    private final String value = "value";
    private final String text = "text";
    WebDriver driver;

    public InputMethod(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to enter text into text field
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param text       : String : Text value to enter in field
     * @param accessName : String : Locator value
     * @throws InterruptedException
     */
    public void enterText(String accessType, String text, String accessName) throws InterruptedException {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(selectelementbytype.getelementbytype(accessType, accessName))).click();
        driver.findElement(selectelementbytype.getelementbytype(accessType, accessName)).clear();
        driver.findElement(selectelementbytype.getelementbytype(accessType, accessName)).sendKeys(text);
        Reporter.addStepLog("Entered text : " + text);
    }

    public void enterText_click(String accessType, String text, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        driver.findElement(selectelementbytype.getelementbytype(accessType, accessName)).click();
        driver.findElement(selectelementbytype.getelementbytype(accessType, accessName)).clear();
        driver.findElement(selectelementbytype.getelementbytype(accessType, accessName)).sendKeys(text);
        Reporter.addStepLog("Entered text : " + text);
        driver.findElement(selectelementbytype.getelementbytype(accessType, accessName)).sendKeys(Keys.RETURN);
    }

    /**
     * Method to clear text of text field
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void clearText(String accessType, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        driver.findElement(selectelementbytype.getelementbytype(accessType, accessName)).clear();
    }

    /**
     * Method to select element from Dropdown by type
     *
     * @param select_list : Select : Select variable
     * @param bytype      : String : Name of by type
     * @param option      : String : Option to select
     */
    public void selectelementfromdropdownbytype(Select select_list, String bytype, String option) {
        if (bytype.equals(selectbyindex)) {
            int index = Integer.parseInt(option);
            select_list.selectByIndex(index - 1);
        } else if (bytype.equals(value)) {
            select_list.selectByValue(option);
        } else if (bytype.equals(text)) {
            select_list.selectByVisibleText(option);
        }
    }

    /**
     * Method to select option from dropdown list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param optionBy   : String : Name of by type
     * @param option     : String : Option to select
     * @param accessName : String : Locator value
     */
    public void selectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        dropdown = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);
System.out.println("se "+selectList);
        if (optionBy.equals(selectbyindex)) {
            selectList.selectByIndex(Integer.parseInt(option) - 1);
        } else if (optionBy.equals(value)) {
            selectList.selectByValue(option);
        } else if (optionBy.equals(text)) {
            System.out.println("select text");
            selectList.selectByVisibleText(option);
        }
    }

    /**
     * Method to unselect all option from dropdwon list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void unselectAllOptionFromMultiselectDropdown(String accessType, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        dropdown = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);
        selectList.deselectAll();
    }

    /**
     * Method to unselect option from dropdwon list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void deselectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        dropdown = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);

        if (optionBy.equals(selectbyindex)) {
            selectList.deselectByIndex(Integer.parseInt(option) - 1);
        } else if (optionBy.equals(value)) {
            selectList.deselectByValue(option);
        } else if (optionBy.equals(text)) {
            selectList.deselectByVisibleText(option);
        }
    }

    /**
     * Method to check check-box
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void checkCheckbox(String accessType, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        WebElement checkbox = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Method to uncheck check-box
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void uncheckCheckbox(String accessType, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        WebElement checkbox = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Method to toggle check-box status
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void toggleCheckbox(String accessType, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName))).click();
    }

    /**
     * Method to select radio button
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void selectRadioButton(String accessType, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        WebElement radioButton = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    /**
     * Method to select option from radio button group
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param by         : String : Name of by type
     * @param option     : String : Option to select
     * @param accessName : String : Locator value
     */
    public void selectOptionFromRadioButtonGroup(String accessType, String option, String by, String accessName) {
        SelectElementByType selectelementbytype = new SelectElementByType(driver);
        List<WebElement> radioButtonGroup = driver.findElements(selectelementbytype.getelementbytype(accessType, accessName));
        for (WebElement rb : radioButtonGroup) {
            if (by.equals(value)) {
                if (rb.getAttribute(value).equals(option) && !rb.isSelected()) {
                    rb.click();
                }
            } else if (by.equals(text)) {
                if (rb.getText().equals(option) && !rb.isSelected()) {
                    rb.click();
                }
            }
        }
    }
}
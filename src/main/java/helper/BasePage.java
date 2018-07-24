package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static helper.SingletonWebDriver.getDriver;

public class BasePage {

    public WebDriver driver = getDriver();

    public BasePage() {
    }

    public WebElement findElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }


    public List<WebElement> getWebElementsList(By locator) {
        findElement(locator);
        return driver.findElements(locator);
    }

    public void navigateTo(String URL) {
        driver.get(URL);
    }

    public void refresh() {
        driver.navigate().refresh();
    }

}

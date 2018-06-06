package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class BasePage {

    public WebDriver driver = SingletonWebDriver.getDriver();
    
     public BasePage() {
    }

    public  WebElement findElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }

    public List<WebElement> getWebElementsList(By locator){
         findElement(locator);
         return driver.findElements(locator);
    }

    public void navigateTo(String URL){
        driver.get(URL);
    }

}

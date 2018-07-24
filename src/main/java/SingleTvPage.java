import helper.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SingleTvPage extends BasePage {

    private static final By televisors = By.xpath(".//*[@class='n-snippet-card2__title']");
    private static final By charakteristic = By.xpath(".//*[@data-name='spec']");
    private int startBorder = -1;
    private static final By have3d = By.xpath(".//*[@class='n-product-spec-wrap__body']//*[text()='Поддержка 3D']");
    private static final String screenSizeLocator_1 = ".//*[@class='n-product-spec-wrap__body']//*[text()[contains(.,'";
    private static final String screenSizeLocator_2 = "\"')]]";
    private static By screenSizeLocator;



    public Boolean checkCharakteristics(){
        check(startBorder);
        return true;
    }

    private void check(int i){
        i++;
        List<WebElement> teleList = getWebElementsList(televisors);
        teleList.get(i).click();
        findElement(charakteristic).click();

        Assert.assertTrue(findElement(have3d).isDisplayed());

        screenSizeLocator = By.xpath(screenSizeLocator_1 + HomePage.expectedDiagonals.get(i) + screenSizeLocator_2);
        Assert.assertTrue(findElement(screenSizeLocator).isDisplayed());

        driver.navigate().back();
        driver.navigate().back();
        if(i < teleList.size() - 1)
            check(i);
    }


}

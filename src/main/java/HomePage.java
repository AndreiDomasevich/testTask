import helper.BasePage;
import helper.MyProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{

    private static final By market = By.xpath(".//*[@data-id='market']");
    private static final By electronic = By.xpath(".//*[@class='topmenu__item i-bem topmenu__item_js_inited']//*[text()='Электроника']");
    private static final By televisors = By.xpath(".//*[@data-department='Электроника']//*[@class='topmenu__sublist']//*[text()='Телевизоры']");
    private static final By priceTo = By.xpath(".//*[@id='glpriceto']");
    private static final By diagonal = By.xpath(".//*[@for='15069594_15069598']");
    private static final By ddd = By.xpath(".//*[@for='6138358_1']");
    private static final By description = By.xpath(".//*[@class='n-snippet-card2__content']");
    private static final By prices = By.xpath(".//div[@class='price']");
    private static final Double priceLimit = 5000.0;
    public static List<String> expectedDiagonals = new ArrayList<>();

    public void goToYandex(){
        navigateTo(MyProperties.getMyProperty("URL"));
    }

    public void clickMarket(){
        findElement(market).click();
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(electronic)).perform();
        findElement(televisors).click();
    }

    public void setParameters() {
        findElement(priceTo).sendKeys(Integer.toString(priceLimit.intValue()));
        findElement(diagonal).click();
        findElement(ddd).click();
        refresh();// пытался дождаться завершения всех аякс-запросов, но не вышло, сделал так
    }

    public void printAndCheckDescription()  {
        List<WebElement> elements;
        List<String> list = new ArrayList();
        elements = getWebElementsList(description);
        for (int i = 0; i < elements.size(); i++) {
            list.add(elements.get(i).getText());
            System.out.println(list.get(i));
            System.out.println("");
            Assert.assertTrue(list.get(i).contains("3D"));
            Boolean flag1 = false;
            for(int j = 50; j <= 55; j ++) {
                String diagonal = Integer.toString(j);
                if(list.get(i).contains(diagonal + "\"")) {
                    flag1 = true;
                    expectedDiagonals.add(diagonal);
                }
            }
            Assert.assertTrue(flag1);
        }

        List<WebElement> priceList;
        List<Double> list2 = new ArrayList<>();
        priceList = getWebElementsList(prices);
        for (int i = 0; i < priceList.size(); i++) {
            list2.add(Double.parseDouble(stripNonDigits(priceList.get(i).getText())) / 100);
        }
        Boolean flag2 = true;
        for (int i = 0; i < list2.size(); i++) {
            if(list2.get(i) > priceLimit)
                flag2 = false;
        }
        Assert.assertTrue(flag2);

        System.out.println(list2);
    }

    private String stripNonDigits(CharSequence input){
        StringBuilder sb = new StringBuilder(input.length());
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
